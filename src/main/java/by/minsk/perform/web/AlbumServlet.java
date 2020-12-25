package by.minsk.perform.web;

import by.minsk.perform.model.Album;
import by.minsk.perform.util.ServletUtil;
import by.minsk.perform.util.Util;
import by.minsk.perform.web.album.AlbumController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 28 Oct, 2020
 */

public class AlbumServlet extends HttpServlet {

    private ConfigurableApplicationContext springContext;
    private AlbumController albumController;
    private ServletUtil servletUtil;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        albumController = springContext.getBean(AlbumController.class);
        servletUtil = springContext.getBean(ServletUtil.class);
    }

    @Override
    public void destroy() {
        servletUtil = null;
        albumController = null;
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        Album album = new Album(
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("year")),
                Util.getTrackArray(req.getParameter("tracks"))
        );
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            albumController.create(album);
        } else {
            albumController.update(album, servletUtil.getId(req));
        }
        resp.sendRedirect("albums");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                long id = servletUtil.getId(req);
                albumController.delete(id);
                resp.sendRedirect("albums");
                break;
            case "create":
            case "update":
                final Album album = "create".equals(action) ?
                        new Album("") :
                        albumController.get(servletUtil.getId(req));
                req.setAttribute("album", album);
                req.setAttribute("performerId", servletUtil.getPerformerId(req));
                req.getRequestDispatcher("/albumForm.jsp").forward(req, resp);
                break;
            case "addTrack":
                String albumId = Objects.requireNonNull(req.getParameter("albumId"));//
                req.setAttribute("album", albumController.get(Long.parseLong(albumId)));
                req.setAttribute("performerId", servletUtil.getPerformerId(req));
                req.getRequestDispatcher("/albumTracks.jsp").forward(req, resp);
                break;
            case "all":
            default:
                req.setAttribute("albums", albumController.getAll());
                req.getRequestDispatcher("/albums.jsp").forward(req, resp);
                break;
        }
    }
}
