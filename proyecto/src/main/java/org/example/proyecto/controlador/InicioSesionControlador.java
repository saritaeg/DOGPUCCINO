package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.utils.ConexionBaseDatos;
import org.example.proyecto.utils.Contraseña;

import java.io.IOException;
import java.sql.*;

import static org.example.proyecto.utils.Alertas.mostrarAlerta;

public class InicioSesionControlador {
    @FXML
    private Button btnAcceso;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnAtras;


    @FXML
    private void btnAtras(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnAtras.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void btnAcceso(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese correo y contraseña.");
            return;
        }

        try {
            if (!correoExiste(email)) {
                mostrarAlerta("Error", "El correo no está registrado.");
                return;
            }

            String contraseniaHasheada = obtenerContraseniaHasheada(email);

            if (contraseniaHasheada == null) {
                mostrarAlerta("Error", "No se pudo obtener la contraseña del usuario.");
                return;
            }

            boolean contraseniaValida = Contraseña.verificarContrasenia(password, contraseniaHasheada);

            if (contraseniaValida) {
                String rol = obtenerRol(email);
                String nombre = obtenerNombre(email);

                if ("CLIENTE".equalsIgnoreCase(rol)) {
                    cargarVista("/org/example/proyecto/VistaPerrosCli.fxml",email);
                    mostrarBienvenida(nombre, "Cliente");
                } else if ("PROTECTORA".equalsIgnoreCase(rol)) {
                    cargarVista("/org/example/proyecto/VistaPerrosProt.fxml",email);
                    mostrarBienvenida(nombre, "Protectora");
                } else {
                    mostrarAlerta("Error", "El usuario no tiene un rol válido.");
                }
            } else {
                mostrarAlerta("Error", "Contraseña incorrecta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Error en la base de datos: " + e.getMessage());
        }
    }

    private boolean correoExiste(String correo) throws SQLException {
        return UsuarioDAO.correoExiste(correo);
    }

    private String obtenerContraseniaHasheada(String email) throws SQLException {
        String sql = """
            SELECT u.Contrasenia
            FROM Usuarios u
            LEFT JOIN Clientes c ON u.ID_Clientes = c.ID
            LEFT JOIN Protectoras p ON u.CIF_Protectoras = p.CIF
            WHERE c.Correo_Electronico = ? OR p.Correo_Electronico = ?
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Contrasenia");
                }
            }
        }
        return null;
    }

    private String obtenerNombre(String email) {
        String sql = """
            SELECT c.Nombre
            FROM Clientes c
            WHERE c.Correo_Electronico = ?
            UNION
            SELECT p.Nombre
            FROM Protectoras p
            WHERE p.Correo_Electronico = ?
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Usuario";
    }

    private String obtenerRol(String email) {
        String sql = """
            SELECT u.Rol
            FROM Usuarios u
            LEFT JOIN Clientes c ON u.ID_Clientes = c.ID
            LEFT JOIN Protectoras p ON u.CIF_Protectoras = p.CIF
            WHERE c.Correo_Electronico = ? OR p.Correo_Electronico = ?
        """;

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Rol");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void cargarVista(String rutaFXML, String email) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnAcceso.getScene().getWindow();
            stage.setScene(new Scene(root));

            PerrosCliControlador controlador = fxmlLoader.getController();
            controlador.inicializarPerros(email);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista.");
        }
    }

    private void mostrarBienvenida(String nombre, String rol) {
        System.out.println("Bienvenido, " + nombre + " (" + rol + ")");
    }
}
