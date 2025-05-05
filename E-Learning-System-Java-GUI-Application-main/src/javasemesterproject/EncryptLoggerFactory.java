/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasemesterproject;

/**
 * Factory for creating EncryptLogger instances.
 */
public class EncryptLoggerFactory extends SecureLoggerFactory {
    @Override
    public SecureLogger getLogger() {
        return new EncryptLogger();
    }
}
