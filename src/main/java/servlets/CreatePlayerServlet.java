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

@WebServlet("/createplayer")
public class CreatePlayerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        int elo = Integer.parseInt(req.getParameter("elo"));

        if (PlayersDAO.createPlayer(new Player(id, firstName, lastName, elo))) {
            List<Player> playerList = PlayersDAO.getAllPlayers();
            for (Player player: playerList) {
                printWriter.write(player.printInHTMLForm());
            }
        } else {
            printWriter.write("Error. Id can be invalid.");
        }

        printWriter.close();
    }


}
