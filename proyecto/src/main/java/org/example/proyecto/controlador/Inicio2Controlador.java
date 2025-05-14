package org.example.proyecto.controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import javafx.event.ActionEvent;
import java.nio.Buffer;
import javafx.scene.control.Button;
import java.io.IOException;

public class Inicio2Controlador {
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnProtectora;
    @FXML
    private Button btnAtras;

    @FXML
    private void btnClientes(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaregCliente.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnClientes.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaRegProtectora.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnAtras(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnAtras.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
