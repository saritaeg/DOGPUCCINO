package org.example.proyecto.servicio;

import org.example.proyecto.dao.RegClienteDAO;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.utils.Alertas;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class RegistroClienteServicio {

    private static boolean validarEmail(String email) {
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(regex);
    }

    private static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("^\\d{9}$");
    }

    private static boolean validarContrasenia(String contrasenia) {
        return contrasenia != null && contrasenia.length() >= 8;
    }

    private static boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) return false;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }

    public static boolean registrarClienteYUsuario(
            String nombre, String apellido, String apellido2,
            LocalDate fechaNacimiento, String telefono, String tipoVia,
            String provincia, String correo, String contrasenia, String confirmarContraseña
    ) {
        try {
            if (!validarEmail(correo)) {
                throw new IllegalArgumentException("El correo electrónico no es válido.");
            }

            if (!validarTelefono(telefono)) {
                throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos.");
            }

            if (!validarContrasenia(contrasenia)) {
                throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
            }

            if (!contrasenia.equals(confirmarContraseña)) {
                throw new IllegalArgumentException("Las contraseñas no coinciden.");
            }

            if (!esMayorDeEdad(fechaNacimiento)) {
                throw new IllegalArgumentException("Debes ser mayor de edad para registrarte.");
            }

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
            } else {
                Alertas.mostrarAlerta("ERROR", "No se pudo registrar el cliente");
                return false;
            }

        } catch (IllegalArgumentException e) {
            Alertas.mostrarAlerta("Validación", e.getMessage());
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.mostrarAlerta("ERROR", "Error al registrar el cliente y usuario");
            return false;
        }
    }
}


