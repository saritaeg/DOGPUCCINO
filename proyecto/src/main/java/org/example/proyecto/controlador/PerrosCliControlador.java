package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.proyecto.dao.PerrosCliDAO;
import org.example.proyecto.modelo.Perro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
    private ImageView imagenPerro1;
    @FXML
    private ImageView imagenPerro2;

    private int indice = 0;
    private List<Perro> perros;


    public void inicializarPerros(String emailCliente) {
        perros = PerrosCliDAO.obtenerPerrosCliente(emailCliente);
        mostrarPerros();
    }

    private void mostrarPerros() {
        if (perros == null || perros.isEmpty()) return;

        mostrarPerroEnPosicion(indice, txtNombreCli, txtRazaCli, txtSexoCli, txtFechaNacimientoCli, txtAdoptadoCli, txtProtectoraCli, imagenPerro1);
        mostrarPerroEnPosicion(indice + 1, txtNombreCli2, txtRazaCli2, txtSexoCli2, txtFechaNacimientoCli2, txtAdoptadoCli2, txtProtectoraCli2, imagenPerro2);
    }

    private void mostrarPerroEnPosicion(int pos, TextField nombre, TextField raza, TextField sexo,
                                        TextField nacimiento, TextField adoptado, TextField protectora, ImageView imagen) {
        if (pos < perros.size()) {
            Perro perro = perros.get(pos);
            nombre.setText(perro.getNombre());
            raza.setText(perro.getRaza());
            sexo.setText(perro.getSexo() != null ? perro.getSexo().name() : "");
            nacimiento.setText(perro.getFechaNacimiento());
            adoptado.setText(perro.getAdoptado() != null ? perro.getAdoptado().name() : "");
            protectora.setText(perro.getNombre() != null ? perro.getNombre() : "");

            try {
                Image img = new Image("file:" + perro.getFoto());
                imagen.setImage(img);
            } catch (Exception e) {
                imagen.setImage(null);
            }
        } else {
            limpiarCampos(nombre, raza, sexo, nacimiento, adoptado, protectora);
            imagen.setImage(null);
        }
    }

    private void limpiarCampos(TextField... campos) {
        for (TextField campo : campos) {
            campo.setText("");
        }
    }

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

            Stage stage = (Stage) btnCitasCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }


    }
    @FXML
    private void btnNosotrosCliente(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNosotrosCliente.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnMasInformacion(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerfilPerro.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnMasInformacion.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnMasInformacion2(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerfilPerro.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnMasInformacion2.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    private void btnMasPerroCliente2(ActionEvent event) {
        if (indice + 2 < perros.size()) {
            indice += 2;
            mostrarPerros();
        }
    }

    @FXML
    private void btnMasPerrosCliente(ActionEvent event) {
        btnMasPerrosCliente(event);

    }

}
