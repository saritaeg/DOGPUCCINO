package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import org.example.proyecto.modelo.Sesion;

import java.io.IOException;

public class AñadirPerroControlador {

    @FXML private Button btnVolverAtrasProtectora;
    @FXML private Label etiquetaTitulo;
    @FXML private Button botonAñadirPerroSuperior;
    @FXML private StackPane contenedorImagen;
    @FXML private ImageView imagenPerro;
    @FXML private Label etiquetaImagen;

    @FXML private TextField nameField;
    @FXML private TextField birthDateField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private ComboBox<String> breedComboBox;
    @FXML private ComboBox<String> pathologyComboBox;
    @FXML private TextArea pathologyDescriptionArea;
    @FXML private Button botonEnviar;

    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String DB_USER = "C##DOGPUCCINO";
    private final String DB_PASS = "123456";

    @FXML
    private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void btnVolverAtrasProtectora() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnVolverAtrasProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        botonEnviar.setOnAction(this::handleAñadirPerro);
    }
    @FXML
    private void handleAñadirPerro(ActionEvent event) {
        System.out.println("Botón pulsado");

        String nombre = nameField.getText();
        String fechaNacimientoStr = birthDateField.getText();
        String sexo = genderComboBox.getValue();
        String raza = breedComboBox.getValue();

        if (nombre == null || nombre.isEmpty() ||
                fechaNacimientoStr == null || fechaNacimientoStr.isEmpty() ||
                sexo == null || sexo.isEmpty() ||
                raza == null || raza.isEmpty()) {
            mostrarAlerta("Error", "Rellena todos los campos obligatorios.");
            return;
        }

        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            mostrarAlerta("Error", "Fecha inválida. Usa formato YYYY-MM-DD.");
            return;
        }

        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() {
                System.out.println("Inicio inserción perro...");
                boolean resultado = insertarPerro(nombre, fechaNacimiento, sexo, raza);
                System.out.println("Inserción perro terminada con resultado: " + resultado);
                return resultado;
            }
        };

        task.setOnSucceeded(e -> {
            System.out.println("Operación finalizada con éxito");
            boolean exito = task.getValue();
            if (exito) {
                mostrarAlerta("Éxito", "Perro añadido correctamente.");
                limpiarCampos();
            } else {
                mostrarAlerta("Error", "No se pudo añadir el perro.");
            }
        });

        task.setOnFailed(e -> {
            System.out.println("Error en la operación");
            Throwable ex = task.getException();
            if (ex != null) ex.printStackTrace();
            mostrarAlerta("Error BD", "Error al insertar el perro: " + (ex != null ? ex.getMessage() : "Error desconocido"));
        });

        new Thread(task).start();
    }

    private boolean insertarPerro(String nombre, LocalDate fechaNacimiento, String sexo, String raza) {
        String sql = "INSERT INTO Perros (ID, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Raza, CIF) " +
                "VALUES (perros_seq.nextval, ?, ?, ?, 'N', SYSDATE, SYSDATE, ?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("Conectado a la BD, preparando statement...");
            pstmt.setString(1, nombre);
            pstmt.setDate(2, Date.valueOf(fechaNacimiento));
            pstmt.setString(3, sexo);
            pstmt.setString(4, raza);
            pstmt.setString(5, Sesion.getCifProtectora());

            int filas = pstmt.executeUpdate();
            System.out.println("Inserción ejecutada, filas afectadas: " + filas);
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCampos() {
        nameField.clear();
        birthDateField.clear();
        genderComboBox.getSelectionModel().clearSelection();
        breedComboBox.getSelectionModel().clearSelection();
        pathologyComboBox.getSelectionModel().clearSelection();
        pathologyDescriptionArea.clear();
    }
}
