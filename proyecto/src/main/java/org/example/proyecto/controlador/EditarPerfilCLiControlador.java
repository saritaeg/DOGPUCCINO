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
import org.example.proyecto.dao.EditarClienteDAO;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.modelo.Clientes;
import org.example.proyecto.utils.Alertas;

import java.io.IOException;

public class EditarPerfilCLiControlador {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtApellido2;
    @FXML
    private DatePicker txtFechaNacimiento;
    @FXML
    private TextField txtProvincia;
    @FXML
    private ComboBox txtTipoVia;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private PasswordField txtConfirmarContraseña;
    @FXML
    private Button btnGuardarCambios;
    @FXML
    private Button btnCancelar;

    private int idCliente;
    private String emailCliente;
    @FXML
    private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    public void inicializarDatos(String email) {
        this.emailCliente = email;

        Integer id = EditarClienteDAO.obtenerIdClientePorCorreo(emailCliente);
        if (id != null) {
            this.idCliente = id;
        } else {
            System.err.println("No se pudo obtener el idCliente para el correo: " + emailCliente);
        }

        cargarDatosDesdeBD();
    }

    private void cargarDatosDesdeBD() {
        Clientes cliente = EditarClienteDAO.obtenerClientePorEmail(emailCliente);
        if (cliente != null) {
            txtNombre.setText(cliente.getNombre());
            txtApellido.setText(cliente.getApellido1());
            txtApellido2.setText(cliente.getApellido2());
            txtFechaNacimiento.setValue(cliente.getFechaNacimiento());
            txtProvincia.setText(cliente.getProvincia());
            txtTipoVia.setValue(cliente.getCalle());
            txtTelefono.setText(cliente.getTelefono());
            txtCorreo.setText(cliente.getEmail());
        }
    }

    @FXML
    private void btnGuardarCambios(ActionEvent event) {
        String pass = txtContraseña.getText();
        String passConfirm = txtConfirmarContraseña.getText();

        if (!pass.isEmpty() || !passConfirm.isEmpty()) {
            if (!pass.equals(passConfirm)) {
                Alertas.mostrarAlerta("Error", "Las contraseñas no coinciden.");
                return;
            }
        }

        Clientes clienteActualizado = new Clientes();
        clienteActualizado.setEmail(emailCliente);
        clienteActualizado.setNombre(txtNombre.getText());
        clienteActualizado.setApellido1(txtApellido.getText());
        clienteActualizado.setApellido2(txtApellido2.getText());
        clienteActualizado.setFechaNacimiento(txtFechaNacimiento.getValue());
        clienteActualizado.setProvincia(txtProvincia.getText());
        clienteActualizado.setCalle((String) txtTipoVia.getValue());
        clienteActualizado.setTelefono(txtTelefono.getText());
        clienteActualizado.setEmail(txtCorreo.getText());

        boolean clienteActualizadoOK = EditarClienteDAO.actualizarCliente(clienteActualizado, emailCliente);

        boolean usuarioActualizadoOK = true;
        if (!pass.isEmpty()) {
            usuarioActualizadoOK = EditarClienteDAO.actualizarContrasenaPorId(idCliente, pass);
            System.out.println("Actualización contraseña OK? " + usuarioActualizadoOK);
        }

        if (clienteActualizadoOK && usuarioActualizadoOK) {
            Alertas.mostrarAlerta("Éxito", "Datos actualizados correctamente.");
            cargarDatosDesdeBD();
            txtContraseña.clear();
            txtConfirmarContraseña.clear();
        } else {
            Alertas.mostrarAlerta("Error", "No se pudieron actualizar los datos.");
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCancelar.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
