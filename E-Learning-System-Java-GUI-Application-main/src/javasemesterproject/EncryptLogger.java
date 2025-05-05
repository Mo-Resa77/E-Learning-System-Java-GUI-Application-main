/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javasemesterproject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * EncryptLogger - Encrypts and logs messages securely.
 */
public class EncryptLogger implements SecureLogger {
    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "your_secret_key"; // Replace with a strong key

    @Override
    public void log(String message) {
        try {
            // Generate a key from the secret key
            byte[] keyBytes = MessageDigest.getInstance("SHA-256").digest(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);

            // Create a cipher for encryption
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the message
            byte[] encryptedBytes = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encryptedMessage = Base64.getEncoder().encodeToString(encryptedBytes);

            // Write the encrypted message to a file
            try (FileWriter writer = new FileWriter("logs.txt", true)) { // Append mode
                writer.write(encryptedMessage + "\n");
            }

            System.out.println("Encrypted message logged: " + encryptedMessage);
        } catch (Exception e) {
            System.err.println("Failed to log message: " + e.getMessage());
        }
    }
}
