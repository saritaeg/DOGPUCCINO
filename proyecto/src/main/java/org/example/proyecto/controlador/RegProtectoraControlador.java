package org.example.proyecto.controlador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import java.nio.Buffer;
import javafx.scene.control.Button;
import org.example.proyecto.servicio.RegistroProtectoraServicio;
import org.example.proyecto.utils.Alertas;

import java.io.IOException;

public class RegProtectoraControlador {
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtCIF;
    @FXML
    private TextField txtCalle;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtRedes;
    @FXML
    private TextField txtContraseña;



    @FXML
    private void btnVolver(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio2.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolver.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @FXML
    private void btnRegistrar(ActionEvent event) {
        try{
            String nombre = txtNombre.getText();
            String ciudad = txtCiudad.getText();
            String cif = txtCIF.getText();
            String calle = txtCalle.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();
            String redes = txtRedes.getText();
            String contraseña = txtContraseña.getText();

            boolean exito = RegistroProtectoraServicio.registrarProtectorayUsuario(nombre,ciudad,cif,calle,correo,telefono,redes,contraseña);
            if(exito){
                Alertas.mostrarAlerta("Registro Conseguido", "La protectora se ha registrado con exito.");
            }else {
                Alertas.mostrarAlerta("ERROR", "La protectora no se puede registrar con exito.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
