package repository;

import service.Player;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PlayersDAO {
    public final static String id_DBStr = "id";
    public final static String first_name_DBStr = "first_name";
    public final static String last_name_DBStr = "last_name";
    public final static String elo_DBStr = "elo";
    private final static String GET_ALL_PLAYERS_QUERY = "SELECT * FROM players";
    private final static String GET_PLAYER_QUERY = "SELECT * FROM players WHERE " + id_DBStr + " = ?";
    private final static String CREATE_PLAYER_QUERY = "INSERT INTO players VALUES (?, ?, ?, ?)";
    private final static String UPDATE_PLAYER_QUERY = "UPDATE players SET " + elo_DBStr + " = ? WHERE " + id_DBStr + " = ?";
    private final static String DELETE_PLAYER_QUERY = "DELETE FROM players WHERE " + id_DBStr + " = ?";


    public static List<Player> getAllPlayers() {
        List<Player> playerList = null;

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PLAYERS_QUERY)) {

            ResultSet rs = preparedStatement.executeQuery();

            playerList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt(id_DBStr);
                String firstName = rs.getString(first_name_DBStr);
                String lastName = rs.getString(last_name_DBStr);
                int elo = rs.getInt(elo_DBStr);

                playerList.add(new Player(id, firstName, lastName, elo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerList;
    }


    public static Player getPlayer(int id) {
        Player player = null;

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PLAYER_QUERY)) {

            preparedStatement.setString(1, String.valueOf(id));
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()) {
                int id_ = rs.getInt(id_DBStr);
                String firstName = rs.getString(first_name_DBStr);
                String lastName = rs.getString(last_name_DBStr);
                int elo = rs.getInt(elo_DBStr);

                player = new Player(id_, firstName, lastName, elo);
                if (rs.next()) { player = null; }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return player;
    }


    public static boolean createPlayer(Player player) {
        try (Connection connection = DBUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PLAYER_QUERY)) {
                if (player.getId() < 0) { return false; }
                preparedStatement.setString(1, String.valueOf(player.getId()));
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()) { return false; }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PLAYER_QUERY)) {
                preparedStatement.setString(1, String.valueOf(player.getId()));
                preparedStatement.setString(2, player.getFirstName());
                preparedStatement.setString(3, player.getLastName());
                preparedStatement.setString(4, String.valueOf(player.getElo()));
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void updatePlayer(int id, int newElo) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PLAYER_QUERY)) {

            preparedStatement.setString(1, String.valueOf(newElo));
            preparedStatement.setString(2, String.valueOf(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deletePlayer(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PLAYER_QUERY)) {

            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
