package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PerrosProtControlador {

    @FXML
    private Button btnVolverAtras;
    @FXML
    private Button btnPerrosProtectora;
    @FXML
    private Button btnNotificacionesProtectora;
    @FXML
    private Button btnCitasProtectora;
    @FXML
    private Button btnSobreNosotrosProtectora;
    @FXML
    private Button btnEditarPerfilProtectora;
    @FXML
    private Button btnAñadirPerroProtectora;
    @FXML
    private Button btnVolverPerrosAtrasProtectora;
    @FXML
    private Button btnBotonAvanzarPerrosProtectora;
    @FXML
    private TextField txtNombrePro;
    @FXML
    private TextField txtRazaPro;
    @FXML
    private TextField txtSexoPro;
    @FXML
    private TextField txtFechaNacimientoPro;
    @FXML
    private TextField txtAdoptadoPro;
    @FXML
    private TextField txtProtectoraPro;
    @FXML
    private Button btnEditarPerroProtectora;
    @FXML
    private TextField txtNombrePro2;
    @FXML
    private TextField txtRazaPro2;
    @FXML
    private TextField txtSexoPro2;
    @FXML
    private TextField txtFechaNacimientoPro2;
    @FXML
    private TextField txtAdoptadoPro2;
    @FXML
    private TextField txtProtectoraPro2;
    @FXML
    private Button btnEditarPerroProtectora2;

    @FXML
    private void btnVolverAtras(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolverAtras.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAñadirPerroProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaAñadirPerro.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnAñadirPerroProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btnPerrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnPerrosProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNotificacionesProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotProtNuevacita.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificacionesProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnCitasProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotProtNuevadop.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCitasProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSobreNosotrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnSobreNosotrosProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEditarPerfilProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaEditarPerfilProtectora.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnEditarPerfilProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVolverPerrosAtrasProtectora(ActionEvent event) {

    }

    @FXML
    private void btnBotonAvanzarPerrosProtectora(ActionEvent event) {
    }

    @FXML
    private void btnEditarPerroProtectora(ActionEvent event) {
    }

    @FXML
    private void btnEditarPerroProtectora2(ActionEvent event) {
    }

}


