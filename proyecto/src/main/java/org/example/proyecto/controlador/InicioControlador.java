package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.*;
import javafx.scene.control.TextField;
import org.example.proyecto.utils.ConexionBaseDatos;
import org.mindrot.jbcrypt.BCrypt;
import org.example.proyecto.dao.UsuarioDAO;



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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnIniciarSesion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicioSesion.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnIniciarSesion.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
