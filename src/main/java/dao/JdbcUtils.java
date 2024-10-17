package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
    private static Connection con;


    static {
        String url = "jdbc:mysql://localhost:3306/pelicula";
        String user ="root";
        String pass = "pasword";
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getCon(){ return con; }
}
