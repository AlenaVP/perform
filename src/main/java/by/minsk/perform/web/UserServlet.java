package by.minsk.perform.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Alena_Papruha
 * @version 1.0
 * @since 26 Oct, 2020
 */

public class UserServlet extends HttpServlet {

    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to performers");
        long userId = Long.parseLong(req.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        resp.sendRedirect("performers");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("forward to users");
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }
}
