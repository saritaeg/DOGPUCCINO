package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.proyecto.dao.PerroDAO;
import org.example.proyecto.dao.PerrosPatologiaDAO;
import org.example.proyecto.modelo.Perro;

import java.io.File;

public class EditarPerroControlador {

    @FXML private TextField txtNombre;
    @FXML private TextField txtFechaNacimiento;
    @FXML private ComboBox<String> ComboBoxSexo;
    @FXML private ComboBox<String> ComboBoxRaza;
    @FXML private ComboBox<String> ComboBoxPatologias;
    @FXML private TextArea pathologyDescriptionArea;
    @FXML private ImageView dogImageView;
    @FXML private Label imageLabel;

    private Stage stage;
    private Perro perro;
    private String imagePath;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPerro(Perro perro) {
        this.perro = perro;
        cargarDatosPerro();
        cargarPatologiaYDescripcion();
    }

    @FXML
    public void initialize() {
        ComboBoxSexo.getItems().addAll("M", "H");
        ComboBoxRaza.getItems().addAll(PerroDAO.obtenerRazas());
        ComboBoxPatologias.getItems().addAll(PerrosPatologiaDAO.obtenerTodasPatologias()); // Asumiendo que tienes este método
    }

    private void cargarDatosPerro() {
        if (perro != null) {
            txtNombre.setText(perro.getNombre());
            txtFechaNacimiento.setText(perro.getFechaNacimiento());
            ComboBoxSexo.setValue(perro.getSexo().toString());
            ComboBoxRaza.setValue(perro.getRaza());

            if (perro.getFoto() != null && !perro.getFoto().isEmpty()) {
                Image image = new Image(perro.getFoto());
                dogImageView.setImage(image);
                imageLabel.setVisible(false);
            }
        }
    }

    private void cargarPatologiaYDescripcion() {
        if (perro != null) {
            String[] patologiaDescripcion = PerrosPatologiaDAO.obtenerPatologiaYDescripcionPorPerro(perro.getId());

            if (patologiaDescripcion[0] != null) {
                ComboBoxPatologias.setValue(patologiaDescripcion[0]);
            }
            if (patologiaDescripcion[1] != null) {
                pathologyDescriptionArea.setText(patologiaDescripcion[1]);
            }
        }
    }

    @FXML
    private void btnVolverAtrasProtectora() {
        if (stage != null) {
            stage.close();
        }
    }

    @FXML
    private void seleccionarImagen() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            imagePath = selectedFile.toURI().toString();
            Image image = new Image(imagePath);
            dogImageView.setImage(image);
            imageLabel.setVisible(false);
        }
    }

    @FXML
    private void btnActualizarPerro() {
        if (validarCampos()) {
            perro.setNombre(txtNombre.getText());
            perro.setFechaNacimiento(txtFechaNacimiento.getText());
            perro.setSexo(Perro.Sexo.valueOf(ComboBoxSexo.getValue()));
            perro.setRaza(ComboBoxRaza.getValue());

            if (imagePath != null) {
                perro.setFoto(imagePath);
            }

            // Actualizar datos en tabla Perros
            PerroDAO.actualizarPerro(perro);

            // Actualizar patología y descripción en tabla Perros_Patologias
            PerrosPatologiaDAO.actualizarPatologiaDescripcion(perro.getId(),
                    ComboBoxPatologias.getValue(),
                    pathologyDescriptionArea.getText());

            mostrarMensajeExito();
            stage.close();
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            return false;
        }
        if (txtFechaNacimiento.getText().isEmpty()) {
            mostrarError("La fecha de nacimiento es obligatoria");
            return false;
        }
        if (ComboBoxSexo.getValue() == null) {
            mostrarError("El sexo es obligatorio");
            return false;
        }
        if (ComboBoxRaza.getValue() == null) {
            mostrarError("La raza es obligatoria");
            return false;
        }
        if (ComboBoxPatologias.getValue() == null) {
            mostrarError("La patología es obligatoria");
            return false;
        }
        return true;
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensajeExito() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setHeaderText(null);
        alert.setContentText("Perro actualizado correctamente");
        alert.showAndWait();
    }
}