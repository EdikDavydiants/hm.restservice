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

    private final static String GET_ALL_PLAYERS_QUERY = "SELECT * FROM players";
    private final static String GET_PLAYER_QUERY = "SELECT * FROM players WHERE id = ?";
    private final static String CREATE_PLAYER_QUERY = "INSERT INTO players VALUES (?, ?, ?, ?)";
    private final static String UPDATE_PLAYER_QUERY = "UPDATE players SET elo = ? WHERE id = ?";
    private final static String DELETE_PLAYER_QUERY = "DELETE FROM players WHERE id = ?";


    public static List<Player> getAllPlayers() {
        List<Player> playerList = null;

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PLAYERS_QUERY)) {

            ResultSet rs = preparedStatement.executeQuery();

            playerList = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int elo = rs.getInt("elo");

                playerList.add(new Player(id, firstName, lastName, elo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerList;
    }


    public static Player getPlayer(int id) {
        List<Player> playerList = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PLAYER_QUERY)) {

            preparedStatement.setString(1, String.valueOf(id));
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_ = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int elo = rs.getInt("elo");

                playerList.add(new Player(id_, firstName, lastName, elo));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(playerList.size() != 1) {
            return null;
        } else {
            return playerList.get(0);
        }
    }


    public static void createPlayer(Player player) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PLAYER_QUERY)) {

            preparedStatement.setString(1, String.valueOf(player.getId()));
            preparedStatement.setString(2, player.getFirstName());
            preparedStatement.setString(3, player.getLastName());
            preparedStatement.setString(4, String.valueOf(player.getElo()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
