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
import java.util.List;


@WebServlet("/updateplayer")
public class UpdatePlayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        int newElo = Integer.parseInt(req.getParameter("elo"));

        PlayersDAO.updatePlayer(id, newElo);
        List<Player> playerList = PlayersDAO.getAllPlayers();

        for (Player player: playerList) {
            printWriter.write(player.printInHTMLForm());
        }

        printWriter.close();
    }


}
