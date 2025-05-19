package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PerfilPerroControlador {
    @FXML
    private TextArea txtInf;
    @FXML
    private TextArea txtPatologias;
    @FXML
    private TextArea txtEstAdop;

    @FXML
    private Button btnMenu;
    @FXML
    private Button btnAdopcion;
    @FXML
    private Button btnCita;

    @FXML
    private void btnMenu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnMenu.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAdopcion(ActionEvent event) {

    }
    @FXML
    private void btnCita(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaTramitarCita.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnMenu.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
