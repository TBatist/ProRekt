import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionManager {
    public static Connection getConnection() throws SQLException {
        if (connection == null)
            connection = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18069142", "18069142", "zuaWe4quai");
        return connection;
    }

    private static Connection connection;
}