/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasemesterproject;

/**
 *
 * @author Num 1
 */
public class LessSensitiveDatabaseFactory extends AbstractDatabaseFactory {
    //override
    public DatabaseConnection getConnection(SecurityCredentials credentials) {
        return new LessSensitiveDatabaseConnection();
    }
}
