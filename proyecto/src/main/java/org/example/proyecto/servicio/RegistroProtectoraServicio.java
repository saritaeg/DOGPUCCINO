package org.example.proyecto.servicio;

import org.example.proyecto.dao.RegProtectoraDAO;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.utils.Alertas;

import java.sql.SQLException;
import java.time.LocalDate;

public class RegistroProtectoraServicio {
    public static boolean registrarProtectorayUsuario(
            String nombre, String ciudad, String cif, String calle,
            String correo, String telefono, String redes, String contraseña
    ) {
        try {
            boolean Protectora = RegProtectoraDAO.registrarProtectora(cif, nombre, calle, ciudad, correo, telefono, redes);
            if (Protectora) {
                Boolean usuarioRegistrado = UsuarioDAO.registrarUsuarioParaProtectora(cif, contraseña);
                if (usuarioRegistrado) {
                    return true;
                } else
                    Alertas.mostrarAlerta("ERROR", "No se pudo registrar el usuario");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlerta("ERROR", "Error al registrar el usuario");
            return false;
        }
        return false;
    }
}
