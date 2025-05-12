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

public class RegProtectoraControlador {
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnVolver;

    @FXML
    private void btnVolver(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio2.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnRegistrar(ActionEvent event) {}
}
