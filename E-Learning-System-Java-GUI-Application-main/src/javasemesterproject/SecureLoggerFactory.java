/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasemesterproject;

/**
 * Factory to create and manage logger instances.
 */
public abstract class SecureLoggerFactory {
    private static SecureLoggerFactory instance;

    
    // Ensure a default instance is created
    static {
        instance = new EncryptLoggerFactory(); // Default to EncryptLoggerFactory
    }
    

    /**
     * Abstract method to get a logger.
     * @return A SecureLogger instance.
     */
    public abstract SecureLogger getLogger();

    /**
     * Gets the current factory instance.
     * @return The current factory instance.
     */
    public static SecureLoggerFactory getInstance() {
        return instance;
    }

    /**
     * Sets the factory instance.
     * @param newFactory The new factory instance to set.
     */
    public static void setInstance(SecureLoggerFactory newFactory) {
        instance = newFactory;
    }
}
