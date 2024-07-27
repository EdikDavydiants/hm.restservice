import repository.PlayersDAO;
import service.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main {


    //CREATE TABLE players (id INT, first_name VARCHAR(30), last_name VARCHAR(30), elo INT)

    public static void main(String[] args) throws SQLException {



        List<Player> playerList = PlayersDAO.getAllPlayers();


        System.out.println(playerList);


//        try (Connection connection = DBUtils.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("")) {
//
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }



    }


    


}
