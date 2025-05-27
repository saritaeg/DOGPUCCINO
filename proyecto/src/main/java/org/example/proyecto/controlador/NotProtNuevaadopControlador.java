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
    @FXML private TableColumn<Notificacion, String> estadoColumna;

    private Usuario usuario;

    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();

        mensajeColumna.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fechaEnvio"));
        estadoColumna.setCellValueFactory(new PropertyValueFactory<>("estado"));

        cargarNotificaciones();
        // Listener para click en fila
        notificacionesTabla.setRowFactory(tv -> {
            TableRow<Notificacion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getClickCount() == 1) {
                    Notificacion clickedNoti = row.getItem();
                    mostrarDialogoAceptarDenegar(clickedNoti);
                }
            });
            return row;
        });
    }
    private void mostrarDialogoAceptarDenegar(Notificacion notificacion) {
        if (notificacion.getEstado().equalsIgnoreCase("Pendiente")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Solicitud");
            alert.setHeaderText(null);
            alert.setContentText("¿Quieres aceptar o denegar esta solicitud?");

            ButtonType aceptar = new ButtonType("Aceptar");
            ButtonType denegar = new ButtonType("Denegar");
            ButtonType cancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(aceptar, denegar, cancelar);

            alert.showAndWait().ifPresent(response -> {
                if (response == aceptar) {
                    actualizarEstado(notificacion, "Aceptada");
                } else if (response == denegar) {
                    actualizarEstado(notificacion, "Denegada");
                }
            });
        } else {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Información");
            info.setHeaderText(null);
            info.setContentText("Esta solicitud ya fue respondida.");
            info.showAndWait();
        }
    }
    private void actualizarEstado(Notificacion notificacion, String nuevoEstado) {
        String tipo = notificacion.getTipo();

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            String sql = "";
            PreparedStatement ps = null;

            if (tipo.equals("Solicitud Adopción")) {

                int clienteId = notificacion.getClienteId();
                int perroId = notificacion.getPerroId();

                sql = "UPDATE Solicitud_Adopcion SET Estado = ? WHERE Cliente_ID = ? AND Perro_ID = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, nuevoEstado);
                ps.setInt(2, clienteId);
                ps.setInt(3, perroId);

            } else if (tipo.equals("Solicitud Cita")) {

                int clienteId = notificacion.getClienteId();
                int perroId = notificacion.getPerroId();
                LocalDate fechaCita = notificacion.getFechaCita();

                sql = "UPDATE Reservan SET Estado = ? WHERE Cliente_ID = ? AND Perro_ID = ? AND Fecha_cita = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, nuevoEstado);
                ps.setInt(2, clienteId);
                ps.setInt(3, perroId);
                ps.setDate(4, Date.valueOf(fechaCita));
            }

            int filas = ps.executeUpdate();

            if (filas > 0) {

                notificacion.setEstado(nuevoEstado);
                notificacionesTabla.refresh();
                Alert exito = new Alert(Alert.AlertType.INFORMATION);
                exito.setTitle("Estado actualizado");
                exito.setHeaderText(null);
                exito.setContentText("El estado se ha actualizado correctamente.");
                exito.showAndWait();
            } else {
                throw new SQLException("No se encontró la solicitud para actualizar.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("No se pudo actualizar el estado");
            error.setContentText(e.getMessage());
            error.showAndWait();
        }
    }

    private void manejarClicEnNotificacion(Notificacion notificacion) {
        if (notificacion == null) return;

        String tipo = notificacion.getTipo();
        if (!tipo.equals("Solicitud Adopción") && !tipo.equals("Solicitud Cita")) {

            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Gestionar Solicitud");
        alert.setHeaderText(null);
        alert.setContentText("¿Desea aceptar o denegar la solicitud?");

        ButtonType btnAceptar = new ButtonType("Aceptar");
        ButtonType btnDenegar = new ButtonType("Denegar");
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnAceptar, btnDenegar, btnCancelar);

        alert.showAndWait().ifPresent(response -> {
            if (response == btnAceptar) {
                actualizarEstadoSolicitud(notificacion, "Aceptada");
            } else if (response == btnDenegar) {
                actualizarEstadoSolicitud(notificacion, "Denegada");
            }
        });
    }
    private void actualizarEstadoSolicitud(Notificacion notificacion, String nuevoEstado) {

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            String sql = null;

            if (notificacion.getTipo().equals("Solicitud Adopción")) {
                sql = "UPDATE Solicitud_Adopcion SET Estado = ? WHERE Cliente_ID = ? AND Perro_ID = ?";
            } else if (notificacion.getTipo().equals("Solicitud Cita")) {
                sql = "UPDATE Reservan SET Estado = ? WHERE Cliente_ID = ? AND Perro_ID = ? AND Fecha_cita = ?";
            }

            if (sql == null) return;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoEstado);

            int clienteId = obtenerClienteId(notificacion);
            int perroId = obtenerPerroId(notificacion);

            ps.setInt(2, clienteId);
            ps.setInt(3, perroId);

            if (notificacion.getTipo().equals("Solicitud Cita")) {
                java.sql.Date fechaCita = obtenerFechaCita(notificacion);
                ps.setDate(4, fechaCita);
            }

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {

                notificacion.setEstado(nuevoEstado);
                notificacionesTabla.refresh();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "No se pudo actualizar el estado en la base de datos.");
                errorAlert.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Error al actualizar el estado: " + e.getMessage());
            errorAlert.show();
        }
    }
    private int obtenerClienteId(Notificacion notificacion) {
        return 0;
    }
    private int obtenerPerroId(Notificacion notificacion) {
        return 0;
    }
    private java.sql.Date obtenerFechaCita(Notificacion notificacion) {
        return null;
    }
    private void cargarNotificaciones() {
        ObservableList<Notificacion> lista = FXCollections.observableArrayList();

        String sql =
                "(" +
                        "SELECT n.Fecha_envio, n.Mensaje, n.Tipo, sa.Estado " +
                        "FROM Notificaciones n " +
                        "JOIN Usuarios u ON n.Usuario_id = u.ID " +
                        "JOIN ProtectoraS p ON u.CIF_Protectoras = p.CIF " +
                        "JOIN Perros pe ON pe.CIF = p.CIF " +
                        "JOIN Solicitud_Adopcion sa ON sa.Perro_ID = pe.ID " +
                        "WHERE n.Tipo = 'Solicitud Adopción' AND p.CIF = ?" +
                        ") " +
                        "UNION ALL " +
                        "(" +
                        "SELECT n.Fecha_envio, n.Mensaje, n.Tipo, r.Estado " +
                        "FROM Notificaciones n " +
                        "JOIN Usuarios u ON n.Usuario_id = u.ID " +
                        "JOIN ProtectoraS p ON u.CIF_Protectoras = p.CIF " +
                        "JOIN Perros pe ON pe.CIF = p.CIF " +
                        "JOIN Reservan r ON r.Perro_ID = pe.ID " +
                        "WHERE n.Tipo = 'Solicitud Cita' AND p.CIF = ?" +
                        ") " +
                        "ORDER BY Fecha_envio DESC";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String cif = Sesion.getUsuario().getCifProtectora();
            ps.setString(1, cif); // para Solicitud Adopción
            ps.setString(2, cif); // para Solicitud Cita

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Notificacion noti = new Notificacion();
                noti.setMensaje(rs.getString("Mensaje"));
                Date fechaSql = rs.getDate("Fecha_envio");
                noti.setFechaEnvio(fechaSql != null ? fechaSql.toLocalDate() : null);
                noti.setTipo(rs.getString("Tipo"));
                noti.setEstado(rs.getString("Estado") != null ? rs.getString("Estado") : "Desconocido");

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
