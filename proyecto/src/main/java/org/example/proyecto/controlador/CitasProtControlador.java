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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.proyecto.modelo.Cita;
import org.example.proyecto.modelo.Usuario;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CitasProtControlador {

    @FXML
    private ToggleButton btnPerrosProtectora;
    @FXML
    private ToggleButton btnNotificacionesProtectora;
    @FXML
    private ToggleButton btnCitasProtectora;
    @FXML
    private ToggleButton btnSobreNosotrosProtectora;

    @FXML
    private TableView<Cita> tablaCitas;
    @FXML
    private TableColumn<Cita, Integer> colClienteId;
    @FXML
    private TableColumn<Cita, Integer> colPerroId;
    @FXML
    private TableColumn<Cita, Integer> colHora;
    @FXML
    private TableColumn<Cita, java.time.LocalDate> colFecha;
    @FXML
    private TableColumn<Cita, String> colDonacion;
    @FXML
    private TableColumn<Cita, String> colEstado;

    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;

    private Usuario usuario;


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        configurarTabla();
        cargarCitasDePerrosProtectora();
        configurarEventoTabla();
    }
    private void configurarEventoTabla() {
        tablaCitas.setOnMouseClicked((MouseEvent click) -> {
            if (click.getButton() == MouseButton.PRIMARY && click.getClickCount() == 2) {
                Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
                if (citaSeleccionada != null) {
                    mostrarDialogoConfirmacion(citaSeleccionada);
                }
            }
        });
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

    public void actualizarEstadoCita(Cita cita, String nuevoEstado) {
        String sql = "UPDATE Reservan SET Estado = ? WHERE Cliente_ID = ? AND Perro_ID = ? AND Fecha_cita = ? AND Hora = ?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, cita.getClienteId());
            ps.setInt(3, cita.getPerroId());
            ps.setDate(4, java.sql.Date.valueOf(cita.getFechaCita()));
            ps.setInt(5, cita.getHora());

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas > 0) {
                cita.setEstado(nuevoEstado);
                tablaCitas.refresh();
                System.out.println("Estado actualizado a " + nuevoEstado);
            } else {
                System.out.println("No se pudo actualizar la cita");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void mostrarDialogoConfirmacion(Cita cita) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modificar estado de cita");
        ButtonType btnAceptar = new ButtonType("Aceptar");
        ButtonType btnCancelar = new ButtonType("Cancelar");
        ButtonType btnCerrar = new ButtonType("Cerrar");
        alert.getButtonTypes().setAll(btnAceptar, btnCancelar, btnCerrar);
        Optional<ButtonType> resultado = alert.showAndWait();

        if (resultado.isPresent()) {
            if (resultado.get() == btnAceptar) {
                actualizarEstadoCita(cita, "Aceptada");
            } else if (resultado.get() == btnCancelar) {
                actualizarEstadoCita(cita, "Cancelada");
            }

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
    private void btnPerrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosProt.fxml"));
            Parent root = fxmlLoader.load();

            PerrosProtControlador controlador = fxmlLoader.getController();
            controlador.inicializarPerros(usuario);

            Stage stage = (Stage) btnPerrosProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNotificacionesProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotProtNuevacita.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificacionesProtectora.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnSobreNosotrosProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotrosPro.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnSobreNosotrosProtectora.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
