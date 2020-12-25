package by.minsk.perform.web;

import by.minsk.perform.model.Album;
import by.minsk.perform.util.ServletUtil;
import by.minsk.perform.web.album.AlbumController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Alena_Papruha
 * @version 1.1
 * @since 20 Dec, 2020
 */

public class TrackServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        final Album oldAlbumVersion = albumController.get(servletUtil.getId(req));
        String[] tracks = new String[0];

        switch (action == null ? "all" : action) {
            case "add":
                tracks = servletUtil.getNewTrackArray(req);
                break;
            case "delete":
                String[] sourceTracks = oldAlbumVersion.getTracks().toArray(new String[0]);
                tracks = servletUtil.getArrayAfterTrackDeleting(req, sourceTracks);
                break;
            default:
                break;
        }
        final Album album = new Album(oldAlbumVersion.getName(), oldAlbumVersion.getYear(), tracks);

        albumController.update(album, servletUtil.getId(req));

        req.setAttribute("album", album);
        req.setAttribute("performerId", servletUtil.getPerformerId(req));
        req.getRequestDispatcher("/albumForm.jsp").forward(req, resp);
    }
}
