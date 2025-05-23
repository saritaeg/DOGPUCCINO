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
import javafx.stage.Modality;
import org.example.proyecto.dao.PerrosProtDAO;
import org.example.proyecto.modelo.Cita;
import org.example.proyecto.modelo.Sesion;
import org.example.proyecto.modelo.Usuario;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class CitasProtControlador {

    @FXML private ToggleButton btnPerrosProtectora;
    @FXML private ToggleButton btnNotificacionesProtectora;
    @FXML private ToggleButton btnCitasProtectora;
    @FXML private ToggleButton btnSobreNosotrosProtectora;

    @FXML private TableView<Cita> tablaCitas;
    @FXML private TableColumn<Cita, Integer> colClienteId;
    @FXML private TableColumn<Cita, Integer> colPerroId;
    @FXML private TableColumn<Cita, Integer> colHora;
    @FXML private TableColumn<Cita, LocalDate> colFecha;
    @FXML private TableColumn<Cita, String> colDonacion;
    @FXML private TableColumn<Cita, String> colEstado;

    private Usuario usuario;


    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        configurarTabla();
        cargarCitasDePerrosProtectora();
        manejarSeleccionCita();
    }

    private void configurarTabla() {
        colClienteId.setCellValueFactory(new PropertyValueFactory<>("clienteId"));
        colPerroId.setCellValueFactory(new PropertyValueFactory<>("perroId"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        colDonacion.setCellValueFactory(new PropertyValueFactory<>("donacion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void cargarCitasDePerrosProtectora() {
        ObservableList<Cita> citas = FXCollections.observableArrayList();
        String sql = "SELECT r.Cliente_ID, r.Perro_ID, r.Hora, r.Fecha_cita, r.Donacion, r.Estado " +
                "FROM Reservan r " +
                "JOIN Perros p ON r.Perro_ID = p.ID " +
                "WHERE p.CIF = ? " +
                "ORDER BY r.Fecha_cita";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getCifProtectora());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setClienteId(rs.getInt("Cliente_ID"));
                cita.setPerroId(rs.getInt("Perro_ID"));
                cita.setHora(rs.getInt("Hora"));
                cita.setFechaCita(rs.getDate("Fecha_cita").toLocalDate());
                cita.setDonacion(rs.getString("Donacion"));
                cita.setEstado(rs.getString("Estado"));
                citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tablaCitas.setItems(citas);
    }

    private void manejarSeleccionCita() {
        tablaCitas.setRowFactory(tv -> {
            TableRow<Cita> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getClickCount() == 2) {
                    Cita citaSeleccionada = row.getItem();
                    mostrarDialogoEstadoCita(citaSeleccionada);
                }
            });
            return row;
        });
    }

    private void mostrarDialogoEstadoCita(Cita cita) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Selecciona una opción:");
        ButtonType aceptar = new ButtonType("Aceptar");
        ButtonType cancelar = new ButtonType("Cancelar");
        ButtonType cerrar = new ButtonType("Cerrar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(aceptar, cancelar, cerrar);

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(tablaCitas.getScene().getWindow());
        alert.showAndWait().ifPresent(response -> {
            if (response == aceptar) {
                actualizarEstadoCita(cita, "Aceptada");
            } else if (response == cancelar) {
                actualizarEstadoCita(cita, "Cancelada");
            }
        });
    }

    public void actualizarEstadoCita(Cita cita, String nuevoEstado) {
        String sqlUpdate = "UPDATE Reservan SET Estado = ?, Fecha_modificacion = ? WHERE Cliente_ID = ? AND Perro_ID = ? AND Fecha_cita = ? AND Hora = ?";
        String sqlInsertNotificacion = "INSERT INTO Notificaciones (ID, Fecha, Texto, Tipo, Cliente_ID) VALUES (NOTIFICACIONES_SEQ.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
                 PreparedStatement psInsertNot = conn.prepareStatement(sqlInsertNotificacion)) {


                psUpdate.setString(1, nuevoEstado);
                psUpdate.setDate(2, Date.valueOf(LocalDate.now()));
                psUpdate.setInt(3, cita.getClienteId());
                psUpdate.setInt(4, cita.getPerroId());
                psUpdate.setDate(5, Date.valueOf(cita.getFechaCita()));
                psUpdate.setInt(6, cita.getHora());

                int filasActualizadas = psUpdate.executeUpdate();

                if (filasActualizadas > 0) {

                    if ("Cancelada".equalsIgnoreCase(nuevoEstado)) {
                        String textoNotificacion = "La protectora ha cancelado la cita para el perro con ID " + cita.getPerroId() +
                                " programada para el día " + cita.getFechaCita();

                        psInsertNot.setDate(1, Date.valueOf(LocalDate.now()));
                        psInsertNot.setString(2, textoNotificacion);
                        psInsertNot.setString(3, "CancelacionCita");
                        psInsertNot.setInt(4, cita.getClienteId());

                        psInsertNot.executeUpdate();
                    }

                    conn.commit();

                    cita.setEstado(nuevoEstado);
                    cita.setFechaModificacion(LocalDate.now());
                    tablaCitas.refresh();
                    System.out.println("Cita actualizada a: " + nuevoEstado);
                } else {
                    System.out.println("No se encontró la cita para actualizar.");
                    conn.rollback();
                }

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    @FXML private void btnPerrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();
            PerrosProtControlador controlador = fxmlLoader.getController();
            controlador.initialize();
            Stage stage = (Stage) btnPerrosProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void btnNotificacionesProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotProtNuevacita.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnNotificacionesProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void btnSobreNosotrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotrosPro.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) btnSobreNosotrosProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
