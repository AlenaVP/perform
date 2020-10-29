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

public class PerformerServlet extends HttpServlet {

    private static final Logger log = getLogger(PerformerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("forward to performers");
        req.getRequestDispatcher("/performers.jsp").forward(req, resp);
    }
}
