package servlets;

import repository.PlayersDAO;
import service.Player;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/getplayer")
public class GetPlayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));

        Player player = PlayersDAO.getPlayer(id);

        if (player == null) {
            printWriter.write("No such player.");
        } else {
            player.printInHTMLForm(printWriter);
        }

        printWriter.close();
    }


}