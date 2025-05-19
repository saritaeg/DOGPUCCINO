package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.example.proyecto.dao.PerrosProtDAO;
import org.example.proyecto.modelo.Perro;

import java.io.IOException;
import java.util.List;

public class PerrosProtControlador {

    @FXML private Button btnVolverAtras;
    @FXML private Button btnPerrosProtectora;
    @FXML private Button btnNotificacionesProtectora;
    @FXML private Button btnCitasProtectora;
    @FXML private Button btnSobreNosotrosProtectora;
    @FXML private Button btnEditarPerfilProtectora;
    @FXML private Button btnAñadirPerroProtectora;
    @FXML private Button btnVolverPerrosAtrasProtectora;
    @FXML private Button btnBotonAvanzarPerrosProtectora;
    @FXML private TextField txtNombrePro;
    @FXML private TextField txtRazaPro;
    @FXML private TextField txtSexoPro;
    @FXML private TextField txtFechaNacimientoPro;
    @FXML private TextField txtAdoptadoPro;
    @FXML private TextField txtProtectoraPro;
    @FXML private Button btnEditarPerroProtectora;
    @FXML private TextField txtNombrePro2;
    @FXML private TextField txtRazaPro2;
    @FXML private TextField txtSexoPro2;
    @FXML private TextField txtFechaNacimientoPro2;
    @FXML private TextField txtAdoptadoPro2;
    @FXML private TextField txtProtectoraPro2;
    @FXML private Button btnEditarPerroProtectora2;

    private List<Perro> perros;
    private String emailProtectora;

    @FXML
    private void btnVolverAtras(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnVolverAtras.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAñadirPerroProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaAñadirPerro.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnAñadirPerroProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPerrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnPerrosProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNotificacionesProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotProtNuevacita.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnNotificacionesProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCitasProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasProtec.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnCitasProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSobreNosotrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotrosPro.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnSobreNosotrosProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnEditarPerfilProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaEditarPerfilProtectora.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnEditarPerfilProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVolverPerrosAtrasProtectora(ActionEvent event) { }

    @FXML
    private void btnBotonAvanzarPerrosProtectora(ActionEvent event) { }

    @FXML
    private void btnEditarPerroProtectora(ActionEvent event) { }

    @FXML
    private void btnEditarPerroProtectora2(ActionEvent event) { }

    public void inicializarPerros(String emailProtectora) {
        this.emailProtectora = emailProtectora;
        perros = PerrosProtDAO.obtenerPerrosProtectora(emailProtectora);
        mostrarPerros();
    }

    private void mostrarPerros() {
        if (perros != null && !perros.isEmpty()) {
            Perro p1 = perros.get(0);
            txtNombrePro.setText(p1.getNombre());
            txtFechaNacimientoPro.setText(p1.getFechaNacimiento());
            txtRazaPro.setText(p1.getRaza());
            txtSexoPro.setText(String.valueOf(p1.getSexo()));
            txtAdoptadoPro.setText(String.valueOf(p1.getAdoptado()));
            txtProtectoraPro.setText(p1.getCifProtectora());

            if (perros.size() > 1) {
                Perro p2 = perros.get(1);
                txtNombrePro2.setText(p2.getNombre());
                txtFechaNacimientoPro2.setText(p2.getFechaNacimiento());
                txtRazaPro2.setText(p2.getRaza());
                txtSexoPro2.setText(String.valueOf(p2.getSexo()));
                txtAdoptadoPro2.setText(String.valueOf(p2.getAdoptado()));
                txtProtectoraPro2.setText(p2.getCifProtectora());
            }
        } else {
            System.out.println("No hay perros disponibles para esta protectora.");
        }
    }
}






