/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasemesterproject;

/**
 *
 * @author Num 1
 */
public class SensitiveDatabaseFactory extends AbstractDatabaseFactory {
    //override
    public DatabaseConnection getConnection(SecurityCredentials credentials) {
        if ("admin".equals(credentials.getRole())) {
            return new SensitiveDatabaseConnection();
        }
        return new LessSensitiveDatabaseConnection();
    }
}
