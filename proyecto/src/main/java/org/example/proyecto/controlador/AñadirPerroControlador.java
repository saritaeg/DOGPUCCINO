package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import java.io.File;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import org.example.proyecto.modelo.Sesion;
import org.example.proyecto.modelo.Usuario;

import java.io.IOException;
import java.util.UUID;



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
    @FXML
    private ImageView dogImageView;


    private File imagenSeleccionada;

    private Usuario usuario;

    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private final String DB_USER = "C##DOGPUCCINO";
    private final String DB_PASS = "123456";

    @FXML
    private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }


    @FXML
    private void seleccionarImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecciona una imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                dogImageView.setImage(image);

                imagenSeleccionada = selectedFile;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    private void btnVolverAtrasProtectora() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = loader.load();

            PerrosProtControlador controlador = loader.getController();
            String cif = String.valueOf(Sesion.getUsuario().getCifProtectora());  // Obtener el CIF desde la sesión
            controlador.inicializarPerros(usuario);

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

        Path rutaImagenDestino = null;
        if (imagenSeleccionada != null) {
            try {
                Path carpetaImagenes = Paths.get("imagenes");
                if (!Files.exists(carpetaImagenes)) {
                    Files.createDirectories(carpetaImagenes);
                }

                String nombreArchivo = UUID.randomUUID() + "_" + imagenSeleccionada.getName();
                rutaImagenDestino = carpetaImagenes.resolve(nombreArchivo);

                Files.copy(imagenSeleccionada.toPath(), rutaImagenDestino, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo guardar la imagen.");
                return;
            }
        }

        String rutaFoto = (rutaImagenDestino != null) ? rutaImagenDestino.toString() : null;
        String finalRutaFoto = rutaFoto;
        Task<Boolean> task = new Task<Boolean>() {
            @Override
            protected Boolean call() {
                System.out.println("Inicio inserción perro...");
                boolean resultado = insertarPerro(nombre, fechaNacimiento, sexo, raza, finalRutaFoto);
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


    private boolean insertarPerro(String nombre, LocalDate fechaNacimiento, String sexo, String raza, String foto) {
        String insertarPerroSQL = "INSERT INTO Perros (ID, Nombre, Fecha_Nacimiento, Sexo, Adoptado, Fecha_alta, Fecha_modificacion, Raza, CIF, Foto) " +
                "VALUES (perros_seq.nextval, ?, ?, ?, 'N', SYSDATE, SYSDATE, ?, ?, ?)";
        String obtenerIdPerroSQL = "SELECT perros_seq.currval FROM dual";
        String insertarPatologiaSQL = "MERGE INTO Patologias p USING (SELECT ? AS nombre FROM dual) src " +
                "ON (p.Nombre = src.nombre) WHEN NOT MATCHED THEN INSERT (ID, Nombre) VALUES (patologias_seq.nextval, src.nombre)";
        String obtenerIdPatologiaSQL = "SELECT ID FROM Patologias WHERE Nombre = ?";
        String insertarRelacionSQL = "INSERT INTO Perros_Patologias (ID_PERROS, ID_PATOLOGIA, DESCRIPCION) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            conn.setAutoCommit(false);  // Transacción manual

            int idPerro = -1;
            int idPatologia = -1;

            // 1. Insertar perro
            try (PreparedStatement ps = conn.prepareStatement(insertarPerroSQL)) {
                ps.setString(1, nombre);
                ps.setDate(2, Date.valueOf(fechaNacimiento));
                ps.setString(3, sexo);
                ps.setString(4, raza);
                ps.setString(5, usuario.getCifProtectora());
                ps.setString(6, foto);
                ps.executeUpdate();
            }

            // 2. Obtener ID del perro insertado
            try (PreparedStatement ps = conn.prepareStatement(obtenerIdPerroSQL);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idPerro = rs.getInt(1);
                }
            }

            // 3. Insertar patología si no existe
            String nombrePatologia = pathologyComboBox.getValue();
            String descripcionPatologia = pathologyDescriptionArea.getText();

            if (nombrePatologia != null && !nombrePatologia.isBlank()) {
                try (PreparedStatement ps = conn.prepareStatement(insertarPatologiaSQL)) {
                    ps.setString(1, nombrePatologia);
                    ps.executeUpdate();
                }

                // 4. Obtener ID de la patología
                try (PreparedStatement ps = conn.prepareStatement(obtenerIdPatologiaSQL)) {
                    ps.setString(1, nombrePatologia);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            idPatologia = rs.getInt("ID");
                        }
                    }
                }

                // 5. Insertar relación perro - patología con descripción
                if (idPerro != -1 && idPatologia != -1) {
                    try (PreparedStatement ps = conn.prepareStatement(insertarRelacionSQL)) {
                        ps.setInt(1, idPerro);
                        ps.setInt(2, idPatologia);
                        ps.setString(3, descripcionPatologia);
                        ps.executeUpdate();
                    }
                }
            }

            conn.commit();  // Confirmar transacción
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
