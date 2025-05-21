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
    private static boolean validarCIF(String cif) {
        return cif != null && cif.matches("^\\d{8}[a-zA-Z]$");
    }

    private static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("^\\d{9}$");
    }

    private static boolean validarContrasenia(String contrasenia) {
        return contrasenia != null && contrasenia.length() >= 8;
    }


    public static boolean registrarProtectorayUsuario(
            String nombre, String ciudad, String cif, String calle,
            String correo, String telefono, String redes,
            String contrasenia, String repetirContrasenia
    ) throws IllegalArgumentException, SQLException {

        if (!validarEmail(correo)) {
            throw new IllegalArgumentException("El correo electrónico tiene un formato inválido.");
        }

        if (!validarCIF(cif)) {
            throw new IllegalArgumentException("El CIF debe tener 8 números y una letra al final.");
        }

        if (!validarTelefono(telefono)) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos.");
        }

        if (!validarContrasenia(contrasenia)) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }

        if (!contrasenia.equals(repetirContrasenia)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }

        if (RegProtectoraDAO.existeProtectora(cif)) {
            throw new IllegalArgumentException("Ya existe una protectora con ese CIF.");
        }

        boolean protectora = RegProtectoraDAO.registrarProtectora(cif, nombre, calle, ciudad, correo, telefono, redes);
        if (!protectora) {
            throw new SQLException("No se pudo registrar la protectora.");
        }

        boolean usuarioRegistrado = UsuarioDAO.registrarUsuarioParaProtectora(cif, contrasenia);
        if (!usuarioRegistrado) {
            throw new SQLException("No se pudo registrar el usuario.");
        }

        return true;
    }

}


