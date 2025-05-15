package org.example.proyecto.utils;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

public class Contraseña {

    private static final PasswordEncoder passwordEncryptor = new BCryptPasswordEncoder();

    public static String encriptarContrasenia(String contraseniaTextoPlano) {
        if (contraseniaTextoPlano == null || contraseniaTextoPlano.isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser vacía o nula");
        }
        return passwordEncryptor.encode(contraseniaTextoPlano.trim());
    }



    public static boolean verificarContrasenia(String contraseniaTextoPlano, String contraseniaHasheada) {
        if (contraseniaTextoPlano == null || contraseniaTextoPlano.isEmpty() ||
                contraseniaHasheada == null || contraseniaHasheada.isEmpty()) {
            return false;
        }
        return passwordEncryptor.matches(contraseniaTextoPlano.trim(), contraseniaHasheada);
    }
}
