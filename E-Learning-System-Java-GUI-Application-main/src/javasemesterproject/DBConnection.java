package javasemesterproject;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    public Connection c;
    public Statement s;
    private final SecureLogger logger;

    public DBConnection() {
        // Initialize logger
        logger = SecureLoggerFactory.getInstance().getLogger();

        try {
            // Register JDBC Driver with Class's Static method
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///ELearningSystem", "root", "1234567");
            s = c.createStatement();

            // Log successful connection
            logger.log("Database connected successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            // Log connection failure
            logger.log("Database connection failed: " + e.getMessage());
            System.err.println(e);
        }
    }

    public void Close() {
        try {
            c.close();
            logger.log("Database connection closed.");
        } catch (SQLException ex) {
            logger.log("Failed to close database connection: " + ex.getMessage());
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

