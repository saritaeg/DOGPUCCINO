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
}
