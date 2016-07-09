package ru.lanwen.web.load;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lanwen (Merkushev Kirill)
 */
@WebServlet(urlPatterns = {"/ping"}, asyncSupported = true)
public class PingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            writer.print("pong");
            writer.flush();
        }
    }
}
