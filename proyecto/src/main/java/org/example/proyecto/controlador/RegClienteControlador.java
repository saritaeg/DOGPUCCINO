package tu.paquete;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import org.example.proyecto.dao.RegClienteDAO;
import org.example.proyecto.dao.RegClienteDAO;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegClienteControlador {

    @FXML private TextField nombreField;
    @FXML private TextField apellido1Field;
    @FXML private TextField apellido2Field;
    @FXML private TextField fechaNacField;
    @FXML private TextField provinciaField;
    @FXML private TextField tipoViaField;
    @FXML private TextField telefonoField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private void onRegistrar(ActionEvent event) {
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Las contraseñas no coinciden.");
            return;
        }

        try {
            Date fechaNacimiento = parsearFecha(fechaNacField.getText());
            boolean registrado = RegClienteDAO.registrarCliente(
                    nombreField.getText(),
                    apellido1Field.getText(),
                    apellido2Field.getText(),
                    fechaNacimiento,
                    telefonoField.getText(),
                    tipoViaField.getText(),
                    provinciaField.getText(),
                    emailField.getText()
            );

            if (registrado) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "Cliente registrado correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo registrar el cliente.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error de BD", e.getMessage());
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Formato de fecha inválido (dd/MM/yyyy).");
        }
    }

    private Date parsearFecha(String fechaTexto) {
        LocalDate localDate = LocalDate.parse(fechaTexto, formatter);
        return Date.valueOf(localDate);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        nombreField.clear();
        apellido1Field.clear();
        apellido2Field.clear();
        fechaNacField.clear();
        provinciaField.clear();
        tipoViaField.clear();
        telefonoField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    @FXML
    private void onVolver(ActionEvent event) {
        // Acción de volver si corresponde
    }
}


