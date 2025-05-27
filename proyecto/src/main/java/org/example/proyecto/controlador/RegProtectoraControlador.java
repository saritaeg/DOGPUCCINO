package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import org.example.proyecto.servicio.RegistroProtectoraServicio;
import org.example.proyecto.utils.Alertas;

import java.io.IOException;

public class RegProtectoraControlador {

    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnMinimizar;
    @FXML
    private Button btnMaximizar; // nuevo botón maximizar
    @FXML
    private Button btnCerrar;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtCIF;
    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtRedes;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtConfirmarContraseña;

    // Variables para maximizar/restaurar
    private double x, y, width, height;
    private boolean maximized = false;

    @FXML
    private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void btnVolver(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio2.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnMaximizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        if (!maximized) {
            // Guardamos tamaño y posición actuales para restaurar después
            x = stage.getX();
            y = stage.getY();
            width = stage.getWidth();
            height = stage.getHeight();

            // Maximizar a pantalla completa (menos barra tareas)
            stage.setX(bounds.getMinX());
            stage.setY(bounds.getMinY());
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            maximized = true;
            btnMaximizar.setText("🗗"); // Cambiar icono a restaurar (puedes cambiarlo si quieres)
        } else {
            // Restaurar tamaño y posición
            stage.setX(x);
            stage.setY(y);
            stage.setWidth(width);
            stage.setHeight(height);
            maximized = false;
            btnMaximizar.setText("⬜"); // Cambiar icono a maximizar
        }
    }

    @FXML
    private void btnRegistrar(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            String ciudad = txtCiudad.getText();
            String cif = txtCIF.getText();
            String calle = txtCalle.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();
            String redes = txtRedes.getText();
            String contraseña = txtContraseña.getText();
            String repetirContraseña = txtConfirmarContraseña.getText();

            boolean exito = RegistroProtectoraServicio.registrarProtectorayUsuario(
                    nombre, ciudad, cif, calle, correo, telefono, redes, contraseña, repetirContraseña
            );

            if (exito) {
                Alertas.mostrarAlerta("Registro Conseguido", "La protectora se ha registrado con éxito.");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicioSesion.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = (Stage) btnRegistrar.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } else {
                Alertas.mostrarAlerta("ERROR", "La protectora no se pudo registrar con éxito.");
            }

        } catch (IllegalArgumentException e) {
            Alertas.mostrarAlerta("Error de Validación", e.getMessage());

        } catch (Exception e) {
            Alertas.mostrarAlerta("Error", "Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
