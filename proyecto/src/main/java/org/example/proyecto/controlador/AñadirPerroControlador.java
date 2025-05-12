package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class AñadirPerroControlador {

    @FXML
    private Button botonVolver;

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
