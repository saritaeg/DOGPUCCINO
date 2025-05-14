package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SobreNosotrosControlador {
    @FXML
    private Button btnVolverInicio;
    @FXML
    private Button btnPerros;
    @FXML
    private Button btnNotificaciones;
    @FXML
    private Button btnCitas;
    @FXML
    private Button btnSobreNosotros;

    @FXML
    private void btnVolverInicio(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolverInicio.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnPerros(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnPerros.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnNotificaciones (ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotCliCancelacion.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificaciones.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnCitas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPasadas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCitas.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnSobreNosotros(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotros.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnSobreNosotros.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
