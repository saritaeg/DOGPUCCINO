package org.example.proyecto.controlador;

import javafx.fxml.FXML;

public class InicioSesionControlador {


    @FXML
    private void iniciarSesion() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        System.out.println("Intentando iniciar sesión con: " + usuario);
    }
}
