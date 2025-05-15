package org.example.proyecto.servicio;

import org.example.proyecto.dao.RegClienteDAO;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.utils.Alertas;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistroClienteServicio {

    public static boolean registrarClienteYUsuario(
            String nombre, String apellido, String apellido2,
            LocalDate fechaNacimiento, String telefono, String tipoVia,
            String provincia, String correo, String contrasenia
    ) {
        try {
            int idCliente = RegClienteDAO.registrarCliente(
                    nombre, apellido, apellido2,
                    Date.valueOf(fechaNacimiento),
                    telefono, tipoVia, provincia, correo
            );

            if (idCliente > 0) {
                boolean usuarioRegistrado = UsuarioDAO.registrarUsuario(idCliente, contrasenia);
                if (usuarioRegistrado) {
                    return true;
                } else {
                    Alertas.mostrarAlerta("ERROR", "No se pudo registrar el usuario");
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlerta("ERROR", "Error al registrar el usuario");
            return false;
        }
        return false;
    }
}
