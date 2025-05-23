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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NotCliCancelacionControlador {
    @FXML private Button btnAtras;
    @FXML private Button btnPerros;
    @FXML private Button btnNotificaciones;
    @FXML private Button btnCitas;
    @FXML private Button btnSobreNosotros;
    @FXML private Button btnCancelacionCita;
    @FXML private Button btnCambioEstado;

    @FXML private TableView<Notificacion> notificacionesTable;
    @FXML private TableColumn<Notificacion, String> mensajeColumna;
    @FXML private TableColumn<Notificacion, LocalDate> fechaColumna;

    private Usuario usuario;

    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();

        mensajeColumna.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fechaEnvio"));

        cargarNotificacionesCancelacion();
    }

    private void cargarNotificacionesCancelacion() {
        ObservableList<Notificacion> lista = FXCollections.observableArrayList();

        String sql = "SELECT Fecha_envio, Mensaje FROM Notificaciones WHERE Usuario_id = ? AND Tipo = 'CancelacionCita' ORDER BY Fecha_envio DESC";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, usuario.getIdUsuario());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date fechaSql = rs.getDate("Fecha_envio");
                String texto = rs.getString("Mensaje");

                Notificacion noti = new Notificacion();
                noti.setMensaje(texto);

                if (fechaSql != null) {
                    noti.setFechaEnvio(fechaSql.toLocalDate());
                } else {
                    noti.setFechaEnvio(null);
                }

                lista.add(noti);
            }

            notificacionesTable.setItems(lista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
    private void btnAtras(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaInicio.fxml");
    }

    @FXML
    private void btnPerros(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaPerrosCli.fxml");
    }

    @FXML
    private void btnNotificaciones(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaNotCliCancelacion.fxml");
    }

    @FXML
    private void btnCitas(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaCitasCliPendientes.fxml");
    }

    @FXML
    private void btnSobreNosotros(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaSobreNosotrosCli.fxml");
    }

    @FXML
    private void btnCancelacionCita(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaNotCliCancelacion.fxml");
    }

    @FXML
    private void btnCambioEstado(ActionEvent event) {
        cambiarEscena(event, "/org/example/proyecto/VistaNotCliCambioestad.fxml");
    }

    private void cambiarEscena(ActionEvent event, String recursoFxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(recursoFxml));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
