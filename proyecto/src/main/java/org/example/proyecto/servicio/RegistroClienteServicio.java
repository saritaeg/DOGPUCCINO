package org.example.proyecto.servicio;

import org.example.proyecto.dao.RegClienteDAO;
import org.example.proyecto.dao.UsuarioDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class RegistroClienteServicio {

    public static boolean registrarClienteYUsuario(
            String nombre, String apellido, String apellido2,
            LocalDate fechaNacimiento, String telefono, String tipoVia,
            String provincia, String correo, String contraseña, String confirmarContraseña
    ) throws SQLException {

        if (!contraseña.equals(confirmarContraseña)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }

        int idCliente = RegClienteDAO.registrarCliente(
                nombre, apellido, apellido2,
                Date.valueOf(fechaNacimiento),
                telefono, tipoVia, provincia, correo
        );

        if (idCliente > 0) {
            return UsuarioDAO.registrarUsuario(idCliente, contraseña);
        } else {
            return false;
        }
    }
}
