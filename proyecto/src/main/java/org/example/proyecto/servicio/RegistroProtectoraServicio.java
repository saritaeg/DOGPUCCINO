package org.example.proyecto.servicio;

import org.example.proyecto.dao.RegProtectoraDAO;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.utils.Alertas;

import java.sql.SQLException;

public class RegistroProtectoraServicio {

    private static boolean validarEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(regex);
    }

    public static boolean registrarProtectorayUsuario(
            String nombre, String ciudad, String cif, String calle,
            String correo, String telefono, String redes, String contrasenia
    ) {
        try {
            if (!validarEmail(correo)) {
                Alertas.mostrarAlerta("ERROR", "El correo electrónico tiene un formato inválido.");
                return false;
            }

            if (RegProtectoraDAO.existeProtectora(cif)) {
                Alertas.mostrarAlerta("ERROR", "Ya existe una protectora con ese CIF.");
                return false;
            }

            boolean protectora = RegProtectoraDAO.registrarProtectora(cif, nombre, calle, ciudad, correo, telefono, redes);
            if (protectora) {
                boolean usuarioRegistrado = UsuarioDAO.registrarUsuarioParaProtectora(cif, contrasenia);
                if (usuarioRegistrado) {
                    return true;
                } else {
                    Alertas.mostrarAlerta("ERROR", "No se pudo registrar el usuario");
                    return false;
                }
            } else {
                Alertas.mostrarAlerta("ERROR", "No se pudo registrar la protectora");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlerta("ERROR", "Error al registrar la protectora y el usuario");
            return false;
        }
    }
}


