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

public class InicioControlador {
    @FXML
    private Button btnRegistrarse;
    @FXML
    private Button btnIniciarSesion;

    @FXML
    private void btnRegistrarse(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio2.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) btnRegistrarse.getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnIniciarSesion(ActionEvent event) {
        try{
            System.out.println("presionado");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicioSesion.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
