/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasemesterproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SensitiveDatabaseConnection implements DatabaseConnection {
    private DBConnection dbConnection;

    public SensitiveDatabaseConnection() {
        this.dbConnection = new DBConnection(); // Establish connection
    }

    @Override
    public void executeQuery(String query) {
        try {
            Connection connection = dbConnection.c;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // Process the result (example: print it)
                System.out.println("Result: " + resultSet.getString(1));
            }

            dbConnection.Close(); // Close the connection after use
        } catch (Exception e) {
            System.err.println("Error executing sensitive query: " + e.getMessage());
        }
    }
}



