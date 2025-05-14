package org.example.proyecto.modelo;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import org.mindrot.jbcrypt.BCrypt;

public class HashUtils {

    public static String hashPassword(String password) {
        // BCrypt genera un "salt" automáticamente
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public static boolean checkPassword(String password, String hashedPassword) {
        // Comparar la contraseña ingresada con el hash almacenado
        return BCrypt.checkpw(password, hashedPassword);
    }
    public static String hashSHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Utilizamos SHA-256
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8)); // Convertimos la contraseña a bytes y la pasamos por SHA-256
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b)); // Convertimos a formato hexadecimal
            }

            return hexString.toString(); // Devolvemos el hash como un string
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el hash SHA-256", e); // En caso de error, lanzamos una excepción
        }
    }
}
