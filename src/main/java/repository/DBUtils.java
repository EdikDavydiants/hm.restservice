package repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {


    public static Connection getConnection() {
        String url;
        String username;
        String password;

        Properties properties = new Properties();

        try(InputStream fis = DBUtils.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(fis);
            url = properties.getProperty("db.host");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }



}
