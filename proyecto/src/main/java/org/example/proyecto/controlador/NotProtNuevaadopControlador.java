package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.proyecto.modelo.Notificacion;
import org.example.proyecto.modelo.Sesion;
import org.example.proyecto.modelo.Usuario;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class NotProtNuevaadopControlador {

    @FXML private Button btnPerrosProtectora;
    @FXML private Button btnNotificacionesProtectora;
    @FXML private Button btnCitasProtectora;
    @FXML private Button btnSobreNosotrosProtectora;
    @FXML private ToggleButton btnSolicitudAdopcionProtectora;

    @FXML private TableView<Notificacion> notificacionesTabla;
    @FXML private TableColumn<Notificacion, String> mensajeColumna;
    @FXML private TableColumn<Notificacion, LocalDate> fechaColumna;

    private Usuario usuario;

    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();

        mensajeColumna.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fechaEnvio"));

        cargarNotificaciones("NuevaAdopcion");
    }

    private void cargarNotificaciones(String tipo) {
        ObservableList<Notificacion> lista = FXCollections.observableArrayList();
        String sql = "SELECT Fecha_envio, Mensaje FROM Notificaciones WHERE Usuario_id = ? AND Tipo = ? ORDER BY Fecha_envio DESC";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, tipo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notificacion noti = new Notificacion();
                noti.setMensaje(rs.getString("Mensaje"));
                Date fechaSql = rs.getDate("Fecha_envio");
                noti.setFechaEnvio(fechaSql != null ? fechaSql.toLocalDate() : null);
                lista.add(noti);
            }

            notificacionesTabla.setItems(lista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML private void btnMinimizar(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    @FXML private void btnPerrosProtectora(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaPerrosProt.fxml");
    }

    @FXML private void btnNotificacionesProtectora(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaNotProtNuevacita.fxml");
    }

    @FXML private void btnCitasProtectora(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaCitasProtec.fxml");
    }

    @FXML private void btnSobreNosotrosProtectora(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaSobreNosotrosPro.fxml");
    }

    @FXML private void btnSolicitudAdopcionProtectora(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaNotProtectora.fxml");
    }

    private void cambiarEscena(ActionEvent event, String rutaFXML) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
