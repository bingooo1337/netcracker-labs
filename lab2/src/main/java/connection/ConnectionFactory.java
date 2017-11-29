package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;

public class ConnectionFactory {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "volodymyr";
    private static final String PASS = "Steavi1337";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new OracleDriver());
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connection OK");
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }

    public static void main(String[] args) {
        Connection connection = ConnectionFactory.getConnection();
    }
}
