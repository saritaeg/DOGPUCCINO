package org.example.proyecto.servicio;

import org.example.proyecto.utils.Alertas;

public class EditarPerfilProtectoraServicio {

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

    public static boolean validarDatosProtectora(
            String cif,
            String nombre,
            String telefono,
            String correo,
            String calle,
            String ciudad,
            String redesSociales,
            String contrasenia,
            String confirmarContrasenia
    ) {
        try {
            if (cif == null || cif.isBlank()) {
                throw new IllegalArgumentException("El CIF no puede estar vacío.");
            }

            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío.");
            }

            if (!validarEmail(correo)) {
                throw new IllegalArgumentException("El correo electrónico no es válido.");
            }

            if (!validarTelefono(telefono)) {
                throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos.");
            }

            if (calle == null || calle.isBlank()) {
                throw new IllegalArgumentException("La calle no puede estar vacía.");
            }

            if (ciudad == null || ciudad.isBlank()) {
                throw new IllegalArgumentException("La ciudad no puede estar vacía.");
            }

            if (!contrasenia.isEmpty()) {
                if (!validarContrasenia(contrasenia)) {
                    throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
                }

                if (!contrasenia.equals(confirmarContrasenia)) {
                    throw new IllegalArgumentException("Las contraseñas no coinciden.");
                }
            }

            return true;

        } catch (IllegalArgumentException e) {
            Alertas.mostrarAlerta("Validación", e.getMessage());
            return false;
        }
    }
}

