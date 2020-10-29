package by.minsk.perform.web;

import by.minsk.perform.model.Album;
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

    @Override
    public void init() throws ServletException {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        albumController = springContext.getBean(AlbumController.class);
    }

    @Override
    public void destroy() {
        albumController = null;
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Album album = new Album(
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("year")),
                Util.getTrackArray(req.getParameter("tracks"))
        );
        if (StringUtils.isEmpty(req.getParameter("id"))) {
            albumController.create(album);
        } else {
            albumController.update(album, getId(req));
        }
        resp.sendRedirect("albums");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                long id = getId(req);
                albumController.delete(id);
                resp.sendRedirect("albums");
                break;
            case "create":
            case "update":
                final Album album = "create".equals(action) ?
                        new Album("") :
                        albumController.get(getId(req));
                req.setAttribute("album", album);
                req.getRequestDispatcher("/albumForm.jsp").forward(req, resp);
                break;
            case "all":
            default:
                req.setAttribute("albums", albumController.getAll());
                req.getRequestDispatcher("/albums.jsp").forward(req, resp);
                break;
        }
    }

    private long getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
