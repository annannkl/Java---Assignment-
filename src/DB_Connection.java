import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
    /**
     * Establishes a connection to the MySQL database.
     *
     * @return A Connection object representing the established connection, or null if an error occurs.
     * @throws SQLException  If there's an issue with the database connection (e.g., invalid credentials, unavailable server).
     * @throws ClassNotFoundException  If the MySQL JDBC driver cannot be found.
     */
    public static Connection getConnection() {
        ColoredUI coloredUI = new ColoredUI();
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the database  using connection string, username, and password
            Connection connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/project_java",
                    "root", "");
            return connection;
        } catch (SQLException e) {
            System.out.println(coloredUI.getRed() + "\nError: The query you have given is not valid!\n" + coloredUI.getRESET());
        } catch (ClassNotFoundException e) {
            System.out.println(coloredUI.getRed() + "\nError: The Database Driver is not found!\n" + coloredUI.getRESET());
        }
        return null;
    }
}

