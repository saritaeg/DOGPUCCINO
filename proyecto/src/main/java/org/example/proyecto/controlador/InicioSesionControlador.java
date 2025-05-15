package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyecto.dao.UsuarioDAO;

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
    private void btnAcceso(ActionEvent event) throws SQLException {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (!correoExiste(email)) {
            mostrarAlerta("El correo no está registrado.");
            return;
        }

        boolean contraseniaValida = UsuarioDAO.verificarContrasenia(email, password);

        if (contraseniaValida) {
            String rol = obtenerRol(email);
            String nombre = obtenerNombre(email);

            if ("CLIENTE".equalsIgnoreCase(rol)) {
                cargarVista("/org/example/proyecto/VistaPerrosCli.fxml");
                mostrarBienvenida(nombre, "Cliente");
            } else if ("PROTECTORA".equalsIgnoreCase(rol)) {
                cargarVista("/org/example/proyecto/VistaPerrosProt.fxml");
                mostrarBienvenida(nombre, "Protectora");
            } else {
                mostrarAlerta("El usuario no tiene un rol válido.");
            }
        } else {
            mostrarAlerta("Contraseña incorrecta.");
        }
    }



    public static boolean correoExiste(String correo) throws SQLException {
        return UsuarioDAO.correoExiste(correo);
    }




    private String obtenerNombre(String email) {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##DOGPUCCINO", "123456")) {
            String sql = """
            SELECT c.Nombre 
            FROM Clientes c
            WHERE c.Correo_Electronico = ? 
            UNION 
            SELECT p.Nombre 
            FROM Protectoras p
            WHERE p.Correo_Electronico = ?
        """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Nombre");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Usuario";
    }

    private void mostrarBienvenida(String nombre, String rol) {
        System.out.println("Bienvenido, " + nombre + " (" + rol + ")");
    }




    private String obtenerRol(String email) {
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "C##DOGPUCCINO", "123456")) {
            String sql = """
            SELECT u.Rol 
            FROM Usuarios u
            LEFT JOIN Clientes c ON u.ID_Clientes = c.ID
            LEFT JOIN Protectoras p ON u.CIF_Protectoras = p.CIF
            WHERE c.Correo_Electronico = ? OR p.Correo_Electronico = ?
        """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Rol");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void cargarVista(String rutaFXML) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnAcceso.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("No se pudo cargar la vista.");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
