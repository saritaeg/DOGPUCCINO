package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AñadirPerroControlador {

    @FXML
    private Button btnVolverAtrasProtectora;

    @FXML
    private Label etiquetaTitulo;

    @FXML
    private Button botonAñadirPerroSuperior;

    @FXML
    private StackPane contenedorImagen;

    @FXML
    private ImageView imagenPerro;

    @FXML
    private Label etiquetaImagen;

    @FXML
    private TextField campoNombre;

    @FXML
    private TextField campoFechaNacimiento;

    @FXML
    private TextField campoSexo;

    @FXML
    private TextField campoRaza;

    @FXML
    private TextField campoPatologia;

    @FXML
    private TextArea areaDescripcionPatologia;

    @FXML
    private Button botonEnviar;


    @FXML
    private void btnVolverAtrasProtectora() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnVolverAtrasProtectora.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void initialize() {
        etiquetaTitulo.setText("Añadir nuevo perro");
    }

    @FXML
    private void volverAlMenu() {
        System.out.println("Volver al menú anterior");
    }

    @FXML
    private void botonAñadirPerro() {
        System.out.println("Perro añadido");
    }

    @FXML
    private void añadirFormulario() {
        String nombre = campoNombre.getText();
        String fechaNacimiento = campoFechaNacimiento.getText();
        String sexo = campoSexo.getText();
        String raza = campoRaza.getText();
        String patologia = campoPatologia.getText();
        String descripcion = areaDescripcionPatologia.getText();

        System.out.println("Perro añadido:");
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha de nacimiento: " + fechaNacimiento);
        System.out.println("Sexo: " + sexo);
        System.out.println("Raza: " + raza);
        System.out.println("Patología: " + patologia);
        System.out.println("Descripción de patología: " + descripcion);
    }
}
