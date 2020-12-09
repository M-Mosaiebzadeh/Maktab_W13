package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {

    private static final String url = "jdbc:mysql://localhost/college";
    private static final String username = "root";
    private static final String password = "soheil13741374";


    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url,username,password);
        return connection;
    }
}
