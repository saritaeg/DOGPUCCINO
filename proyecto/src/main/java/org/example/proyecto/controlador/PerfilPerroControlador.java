package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyecto.modelo.Perro;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfilPerroControlador {
    @FXML
    private TextArea txtInf;
    @FXML
    private TextArea txtPatologias;
    @FXML
    private TextArea txtEstAdop;

    @FXML
    private Button btnMenu;
    @FXML
    private Button btnAdopcion;
    @FXML
    private Button btnCita;

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
    private void btnMenu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnMenu.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void btnAdopcion(ActionEvent event) {

    }
    @FXML
    private void btnCita(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaTramitarCita.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnMenu.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Perro perro;
    private String emailCliente;

    public void setPerro(Perro perro) {
        this.perro = perro;
        mostrarDatosPerro();
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    private String obtenerPatologiasPerro(int idPerro) {
        StringBuilder sb = new StringBuilder();

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()){
            String sql = """
            SELECT p.Nombre, pp.Descripcion
            FROM Patologias p
            JOIN Perros_Patologias pp ON p.ID = pp.ID_Patologia
            WHERE pp.ID_Perros = ?
        """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPerro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                sb.append("• ").append(nombre);
                if (descripcion != null && !descripcion.isBlank()) {
                    sb.append(" - ").append(descripcion);
                }
                sb.append("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            sb.append("Error al cargar patologías.");
        }

        return sb.length() > 0 ? sb.toString() : "Ninguna";
    }

    private void mostrarDatosPerro() {
        if (perro != null) {
            txtInf.setText("Nombre: " + perro.getNombre() + "\n"
                    + "Raza: " + perro.getRaza() + "\n"
                    + "Sexo: " + perro.getSexo() + "\n"
                    + "Fecha Nacimiento: " + perro.getFechaNacimiento());

            txtPatologias.setText(obtenerPatologiasPerro(perro.getId()));


            Perro.Adoptado adoptado = perro.getAdoptado();
            if (adoptado == Perro.Adoptado.S) {
                txtEstAdop.setText("Sí");
            } else if (adoptado == Perro.Adoptado.N) {
                txtEstAdop.setText("No");
            } else {
                txtEstAdop.setText("Desconocido");
            }


        }
    }

}
