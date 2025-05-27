package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.proyecto.servicio.RegistroClienteServicio;
import org.example.proyecto.utils.Alertas;

import java.io.IOException;
import java.time.LocalDate;

public class RegClienteControlador {

    @FXML
    private Button btnRegistrarCliente;
    @FXML
    private Button btnVolverCliente;
    @FXML
    private Button btnMinimizar;
    @FXML
    private Button btnMaximizar;
    @FXML
    private Button btnCerrar;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtApellido2;
    @FXML
    private DatePicker txtFechaNacimiento;
    @FXML
    private TextField txtProvincia;
    @FXML
    private ComboBox<String> txtTipoVia;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private PasswordField txtConfirmarContraseña;

    private double originalWidth;
    private double originalHeight;
    private double originalX;
    private double originalY;
    private boolean maximized = false;

    @FXML
    public void initialize() {
        txtTipoVia.getItems().addAll("Calle", "Avenida");

        Platform.runLater(() -> {
            Stage stage = (Stage) btnRegistrarCliente.getScene().getWindow();
            originalWidth = stage.getWidth();
            originalHeight = stage.getHeight();
            originalX = stage.getX();
            originalY = stage.getY();
        });
    }

    @FXML
    private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnMaximizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        if (!maximized) {
            originalWidth = stage.getWidth();
            originalHeight = stage.getHeight();
            originalX = stage.getX();
            originalY = stage.getY();

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(screenBounds.getMinX());
            stage.setY(screenBounds.getMinY());
            stage.setWidth(screenBounds.getWidth());
            stage.setHeight(screenBounds.getHeight());

            maximized = true;
            btnMaximizar.setText("❐");
        } else {
            stage.setX(originalX);
            stage.setY(originalY);
            stage.setWidth(originalWidth);
            stage.setHeight(originalHeight);

            maximized = false;
            btnMaximizar.setText("⬜");
        }
    }

    @FXML
    private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void btnVolverCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio2.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolverCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnRegistrarCliente(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String apellido2 = txtApellido2.getText();
            LocalDate fechaNacimiento = txtFechaNacimiento.getValue();
            String provincia = txtProvincia.getText();
            String tipoVia = txtTipoVia.getValue();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            String contraseña = txtContraseña.getText();
            String confirmarContraseña = txtConfirmarContraseña.getText();

            boolean exito = RegistroClienteServicio.registrarClienteYUsuario(
                    nombre, apellido, apellido2, fechaNacimiento, telefono,
                    tipoVia, provincia, correo, contraseña, confirmarContraseña
            );

            if (exito) {
                Alertas.mostrarAlerta("Éxito", "Cliente y usuario registrados correctamente.");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicioSesion.fxml"));
                Parent root = fxmlLoader.load();

                Stage stage = (Stage) btnRegistrarCliente.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } else {
                Alertas.mostrarAlerta("Error", "No se pudo registrar el cliente.");
            }

        } catch (IllegalArgumentException e) {
            Alertas.mostrarAlerta("Error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Alertas.mostrarAlerta("Error", "Ocurrió un error al registrar.");
        }
    }
}







