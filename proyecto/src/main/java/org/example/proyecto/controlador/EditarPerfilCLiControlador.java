package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import org.example.proyecto.dao.EditarClienteDAO;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.modelo.Clientes;
import org.example.proyecto.servicio.EditarPerfilClienteServicio;
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
    private ComboBox<String> txtTipoVia;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtContraseña;
    @FXML
    private TextField txtConfirmarContraseña;
    @FXML
    private Button btnGuardarCambios;
    @FXML
    private Button btnCancelar;

    private int idCliente;  // Variable para almacenar el idCliente obtenido
    private String emailCliente;

    public void inicializarDatos(String email) {
        this.emailCliente = email;

        // Obtener idCliente desde UsuarioDAO o EditarClienteDAO (según donde implementes el método)
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
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String apellido2 = txtApellido2.getText();
        var fechaNacimiento = txtFechaNacimiento.getValue();
        String provincia = txtProvincia.getText();
        String tipoVia = txtTipoVia.getValue();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String contraseña = txtContraseña.getText();
        String confirmarContraseña = txtConfirmarContraseña.getText();


        boolean exito = EditarPerfilClienteServicio.registrarClienteYUsuario(
                nombre, apellido, apellido2,
                fechaNacimiento, telefono,
                tipoVia, provincia, correo,
                contraseña, confirmarContraseña
        );

        if (exito) {
            Clientes clienteActualizado = new Clientes();
            clienteActualizado.setEmail(emailCliente);
            clienteActualizado.setNombre(nombre);
            clienteActualizado.setApellido1(apellido);
            clienteActualizado.setApellido2(apellido2);
            clienteActualizado.setFechaNacimiento(fechaNacimiento);
            clienteActualizado.setProvincia(provincia);
            clienteActualizado.setCalle(tipoVia);
            clienteActualizado.setTelefono(telefono);
            clienteActualizado.setEmail(correo);

            boolean clienteActualizadoOK = EditarClienteDAO.actualizarCliente(clienteActualizado, emailCliente);

            boolean usuarioActualizadoOK = true;
            if (!contraseña.isEmpty()) {
                usuarioActualizadoOK = UsuarioDAO.actualizarContraseña(emailCliente, contraseña);
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

<<<<<<< HEAD
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
            // Aquí usas el idCliente que obtuviste para actualizar la contraseña
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
=======
>>>>>>> 465ad5fdda1c99679d2edaeb134df90ff7b9800c
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

