package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.proyecto.modelo.Perro;
import java.io.File;

/* public class EditarPerroControlador {
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFechaNacimiento;
    @FXML
    private ComboBox<String> ComboBoxSexo;
    @FXML
    private ComboBox<String> ComboBoxRaza;
    @FXML
    private ComboBox<String> ComboBoxPatologias;
    @FXML
    private TextArea pathologyDescriptionArea;
    @FXML
    private ImageView dogImageView;
    @FXML
    private Label imageLabel;
    @FXML
    private StackPane imageContainer;

    private Stage stage;
    private Perro perro;
    private String imagePath;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setPerro(Perro perro) {
        this.perro = perro;
        cargarDatosPerro();
    }

    private void cargarDatosPerro() {
        if (perro != null) {
            txtNombre.setText(perro.getNombre());
            txtFechaNacimiento.setText(perro.getFechaNacimiento());
            ComboBoxSexo.setValue(perro.getSexo().toString());
            ComboBoxRaza.setValue(perro.getRaza());
            ComboBoxPatologias.setValue(perro.getPatologia());
            pathologyDescriptionArea.setText(perro.getDescripcionPatologia());

            if (perro.getFoto() != null && !perro.getFoto().isEmpty()) {
                Image image = new Image(perro.getFoto());
                dogImageView.setImage(image);
                imageLabel.setVisible(false);
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
    private void btnMinimizar() {
        if (stage != null) {
            stage.setIconified(true);
        }
    }

    @FXML
    private void btnCerrar() {
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
            perro.setNombre(nameField.getText());
            perro.setFechaNacimiento(birthDateField.getText());
            perro.setSexo(genderComboBox.getValue());
            perro.setRaza(breedComboBox.getValue());
            perro.setPatologia(pathologyComboBox.getValue());
            perro.setDescripcionPatologia(pathologyDescriptionArea.getText());

            if (imagePath != null) {
                perro.setImagenUrl(imagePath);
            }


            mostrarMensajeExito();
            stage.close();
        }
    }

    private boolean validarCampos() {
        if (nameField.getText().isEmpty()) {
            mostrarError("El nombre es obligatorio");
            return false;
        }
        if (birthDateField.getText().isEmpty()) {
            mostrarError("La fecha de nacimiento es obligatoria");
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
*/