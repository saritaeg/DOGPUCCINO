package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.proyecto.modelo.Cita;
import org.example.proyecto.modelo.Sesion;
import org.example.proyecto.modelo.Usuario;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CitasCliPasadasControlador {

    @FXML
    private Button btnAtras, btnPerros, btnNotificaciones, btnCitas, btnSobreNosotros, btnCitasPasadas, btnCitasPendientes;

    @FXML
    private TableView<Cita> citasTable;
    @FXML
    private TableColumn<Cita, String> perro;
    @FXML
    private TableColumn<Cita, Integer> hora;
    @FXML
    private TableColumn<Cita, LocalDate> fechaCita;
    @FXML
    private TableColumn<Cita, String> donacion;

    private Usuario usuario;

    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();
        configurarTabla();
        cargarCitasPasadas();
    }

    private void configurarTabla() {
        perro.setCellValueFactory(new PropertyValueFactory<>("nombrePerro"));
        hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        fechaCita.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        donacion.setCellValueFactory(new PropertyValueFactory<>("donacion"));
    }

    private void cargarCitasPasadas() {
        ObservableList<Cita> citas = FXCollections.observableArrayList();

        String sql = "SELECT r.Cliente_ID, r.Perro_ID, p.Nombre AS perro, r.Hora, r.Fecha_cita, " +
                "r.Donacion, r.Fecha_alta, r.Fecha_modificacion " +
                "FROM Reservan r JOIN Perros p ON r.Perro_ID = p.ID " +
                "WHERE r.Cliente_ID = ? AND r.Fecha_cita < TRUNC(SYSDATE) ORDER BY r.Fecha_cita DESC";

        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, usuario.getIdClientes());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cita cita = new Cita();
                cita.setClienteId(rs.getInt("Cliente_ID"));
                cita.setPerroId(rs.getInt("Perro_ID"));
                cita.setNombrePerro(rs.getString("perro"));
                cita.setHora(rs.getInt("Hora"));
                cita.setFechaCita(rs.getDate("Fecha_cita").toLocalDate());
                cita.setDonacion(rs.getString("Donacion"));
                cita.setFechaAlta(rs.getDate("Fecha_alta").toLocalDate());
                cita.setFechaModificacion(rs.getDate("Fecha_modificacion").toLocalDate());

                citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        citasTable.setItems(citas);
    }

    @FXML private void btnMinimizar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML private void btnCerrar(ActionEvent event) {
        Platform.exit();
    }

    private void cambiarVista(String rutaFXML, Button boton) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) boton.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML private void btnAtras(ActionEvent event) {
        cambiarVista("/org/example/proyecto/VistaInicio.fxml", btnAtras);
    }

    @FXML private void btnPerros(ActionEvent event) {
        cambiarVista("/org/example/proyecto/VistaPerrosCli.fxml", btnPerros);
    }

    @FXML private void btnNotificaciones(ActionEvent event) {
        cambiarVista("/org/example/proyecto/VistaNotCliCancelacion.fxml", btnNotificaciones);
    }

    @FXML private void btnCitas(ActionEvent event) {
        cambiarVista("/org/example/proyecto/VistaCitasCliPendientes.fxml", btnCitas);
    }

    @FXML private void btnSobreNosotros(ActionEvent event) {
        cambiarVista("/org/example/proyecto/VistaSobreNosotrosCli.fxml", btnSobreNosotros);
    }

    @FXML private void btnCitasPasadas(ActionEvent event) {
        cambiarVista("/org/example/proyecto/VistaCitasCliPasadas.fxml", btnCitasPasadas);
    }

    @FXML private void btnCitasPendientes(ActionEvent event) {
        cambiarVista("/org/example/proyecto/VistaCitasCliPendientes.fxml", btnCitasPendientes);
    }
}
