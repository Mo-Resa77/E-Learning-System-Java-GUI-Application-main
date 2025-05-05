/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasemesterproject;

/**
 *
 * @author Num 1
 */
public abstract class AbstractDatabaseFactory {
    private static AbstractDatabaseFactory defaultInstance;

    public static AbstractDatabaseFactory getInstance() {
        if (defaultInstance == null) {
            defaultInstance = new LessSensitiveDatabaseFactory(); // Default factory
        }
        return defaultInstance;
    }

    public static void setInstance(AbstractDatabaseFactory newInstance) {
        defaultInstance = newInstance;
    }

    public abstract DatabaseConnection getConnection(SecurityCredentials credentials);
}
