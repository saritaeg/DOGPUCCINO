package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PerrosCliControlador {
    @FXML
    private TextField txtNombreCli;
    @FXML
    private TextField txtRazaCli;
    @FXML
    private TextField txtSexoCli;
    @FXML
    private TextField txtFechaNacimientoCli;
    @FXML
    private TextField txtAdoptadoCli;
    @FXML
    private TextField txtProtectoraCli;
    @FXML
    private TextField txtNombreCli2;
    @FXML
    private TextField txtRazaCli2;
    @FXML
    private TextField txtSexoCli2;
    @FXML
    private TextField txtFechaNacimientoCli2;
    @FXML
    private TextField txtAdoptadoCli2;
    @FXML
    private TextField txtProtectoraCli2;
    @FXML
    private Button btnVolverInicio;
    @FXML
    private Button btnEditarPerfilCliente;
    @FXML
    private Button btnPerrosCliente;
    @FXML
    private Button btnNotificacionesCliente;
    @FXML
    private Button btnCitasCliente;
    @FXML
    private Button btnNosotrosCliente;
    @FXML
    private Button btnMasPerrosCliente;
    @FXML
    private Button btnMasInformacion;
    @FXML
    private Button btnMasInformacion2;
    @FXML
    private Button btnMasPerroCliente2;

    @FXML
    private void btnVolverInicio(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolverInicio.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void btnEditarPerfilCliente(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaEditarPerfilCliente.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnEditarPerfilCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnPerrosCliente(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnPerrosCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnNotificacionesCliente(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotCliCancelacion.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificacionesCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    @FXML
    private void btnCitasCliente(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPasadas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificacionesCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    @FXML
    private void btnNosotrosCliente(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPasadas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificacionesCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnMasPerrosCliente(ActionEvent event) {


    }
    @FXML
    private void btnMasInformacion(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPasadas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificacionesCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnMasInformacion2(ActionEvent event) {

    }
    @FXML
    private void btnMasPerroCliente2(ActionEvent event) {

    }

}
