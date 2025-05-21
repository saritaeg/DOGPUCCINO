package org.example.proyecto.controlador;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.example.proyecto.dao.EditarProtectoraDAO;
import org.example.proyecto.modelo.Protectoras;
import org.example.proyecto.utils.Alertas;

import java.io.IOException;
import java.time.LocalDate;

public class EditarProtectoraControlador {
    @FXML private TextField txtCIF;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreoElectronico;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtCalle;
    @FXML private TextField txtRedesSociales;

    @FXML private PasswordField txtNuevaContrasena;
    @FXML private PasswordField txtConfirmarContrasena;

    @FXML private Button btnGuardarCambios;
    @FXML private Button btnCancelar;
    @FXML
    private  Button btnCerrar;

    @FXML
    private Button btnMinimizar;

    @FXML
    private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    private String emailProtectora;
    private String cifProtectora;

    public void inicializarDatos(String email) {
        this.emailProtectora = email;

        String cif = EditarProtectoraDAO.obtenerCifProtectoraPorCorreo(emailProtectora);
        if (cif != null) {
            this.cifProtectora = cif;
        } else {
            System.err.println("No se pudo obtener el idProtectora para el correo: " + emailProtectora);
        }

        cargarDatosDesdeBD();
    }


    private void cargarDatosDesdeBD() {
        Protectoras protectora = EditarProtectoraDAO.obtenerProtectoraPorEmail(emailProtectora);
        if (protectora != null) {
            this.cifProtectora = protectora.getCIF();
            txtCIF.setText(protectora.getCIF());
            txtNombre.setText(protectora.getNombre());
            txtCorreoElectronico.setText(protectora.getCorreoElectronico());
            txtTelefono.setText(protectora.getTelefono());
            txtCiudad.setText(protectora.getCiudad());
            txtCalle.setText(protectora.getCalle());
            txtRedesSociales.setText(protectora.getRedesSociales());
        }
    }

    @FXML
    private void btnGuardarCambios(ActionEvent event) {
        String pass = txtNuevaContrasena.getText();
        String passConfirm = txtConfirmarContrasena.getText();

        if (!pass.isEmpty() || !passConfirm.isEmpty()) {
            if (!pass.equals(passConfirm)) {
                Alertas.mostrarAlerta("Error", "Las contraseñas no coinciden.");
                return;
            }
        }

        Protectoras protectoraActualizada = new Protectoras(
                cifProtectora,
                txtNombre.getText(),
                txtTelefono.getText(),
                txtCorreoElectronico.getText(),
                txtCalle.getText(),
                txtCiudad.getText(),
                txtRedesSociales.getText(),
                LocalDate.now(),
                null
        );

        boolean protectoraActualizadaOK = EditarProtectoraDAO.actualizarProtectora(protectoraActualizada, emailProtectora);

        boolean usuarioActualizadoOK = true;
        if (!pass.isEmpty()) {
            usuarioActualizadoOK = EditarProtectoraDAO.actualizarContrasenaPorCIF(cifProtectora, pass);
            System.out.println("Actualización contraseña OK? " + usuarioActualizadoOK);
        }

        if (protectoraActualizadaOK && usuarioActualizadoOK) {
            Alertas.mostrarAlerta("Éxito", "Datos actualizados correctamente.");
            cargarDatosDesdeBD();
            txtNuevaContrasena.clear();
            txtConfirmarContrasena.clear();
        } else {
            Alertas.mostrarAlerta("Error", "No se pudieron actualizar los datos.");
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCancelar.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}