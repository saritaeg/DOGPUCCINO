package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import org.example.proyecto.dao.EditarProtectoraDAO;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.modelo.Protectoras;
import org.example.proyecto.servicio.EditarPerfilProtectoraServicio;
import org.example.proyecto.utils.Alertas;

import java.io.IOException;
import java.time.LocalDate;

public class EditarPerfilProControlador {

    @FXML private TextField txtCIF;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtCalle;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtRedes;
    @FXML private TextField txtContraseña;
    @FXML private TextField txtConfirmarContraseña;
    @FXML private Button btnGuardarCambios;
    @FXML private Button btnCancelar;

    private String correoOriginal;

    public void inicializarDatos(String correo) {
        this.correoOriginal = correo;
        cargarDatosDesdeBD();
    }

    private void cargarDatosDesdeBD() {
        Protectoras protectora = EditarProtectoraDAO.obtenerProtectoraPorEmail(correoOriginal);
        if (protectora != null) {
            txtCIF.setText(protectora.getCIF());
            txtNombre.setText(protectora.getNombre());
            txtTelefono.setText(protectora.getTelefono());
            txtCorreo.setText(protectora.getCorreoElectronico());
            txtCalle.setText(protectora.getCalle());
            txtCiudad.setText(protectora.getCiudad());
            txtRedes.setText(protectora.getRedesSociales());
        }
    }

    @FXML
    private void btnGuardarCambios(ActionEvent event) {
        String cif = txtCIF.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String calle = txtCalle.getText();
        String ciudad = txtCiudad.getText();
        String redesSociales = txtRedes.getText();
        String contraseña = txtContraseña.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();

        boolean datosValidos = EditarPerfilProtectoraServicio.validarDatosProtectora(
                cif, nombre, telefono, correo, calle, ciudad, redesSociales,
                contraseña, confirmarContraseña
        );

        if (datosValidos) {
            Protectoras protectora = new Protectoras(
                    cif, nombre, telefono, correo,
                    calle, ciudad, redesSociales,
                    LocalDate.now(), null
            );

            boolean actualizacionOK = EditarProtectoraDAO.actualizarProtectora(protectora, correoOriginal);

            boolean actualizarUsuarioOK = true;
            if (!contraseña.isEmpty()) {
                actualizarUsuarioOK = UsuarioDAO.actualizarContraseña(correoOriginal, contraseña);
            }

            if (actualizacionOK && actualizarUsuarioOK) {
                Alertas.mostrarAlerta("Éxito", "Datos actualizados correctamente.");
                cargarDatosDesdeBD();
                txtContraseña.clear();
                txtConfirmarContraseña.clear();
            } else {
                Alertas.mostrarAlerta("Error", "No se pudieron actualizar los datos.");
            }
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

