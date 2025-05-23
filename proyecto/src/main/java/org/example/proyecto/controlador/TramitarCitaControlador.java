package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.proyecto.modelo.Cita;
import org.example.proyecto.modelo.Perro;
import org.example.proyecto.dao.CitasDAO;
import org.example.proyecto.modelo.Sesion;
import org.example.proyecto.modelo.Usuario;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

import static org.example.proyecto.utils.Alertas.mostrarAlerta;

public class TramitarCitaControlador {

    @FXML
    private Button btnAtras;
    @FXML
    private TextField emailTextField;
    @FXML
    private DatePicker datePickerFecha;
    @FXML
    private ChoiceBox<String> horaCitaChoiceBox;
    @FXML
    private TextArea txtComentarios;
    @FXML
    private Button donacion;
    @FXML
    private Button btnConfirmar;
    @FXML
    private ChoiceBox<String> donacionChoiceBox;

    private Perro perro;
    private String emailCliente;
    private Usuario usuario;

    public void setPerro(Perro perro) {
        this.perro = perro;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
        emailTextField.setText(emailCliente);
        emailTextField.setDisable(true);
    }

    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();
        donacionChoiceBox.getItems().addAll("3€", "5€", "10€", "20€", "50€");
        donacionChoiceBox.setValue("3€");
        for (int h = 8; h <= 20; h++) {
            horaCitaChoiceBox.getItems().add(h + "h - " + (h + 1) + "h");
        }
    }

    @FXML
    private void btnConfirmar(ActionEvent event) {

        if (datePickerFecha.getValue() == null) {
            mostrarAlerta("Error", "Debe seleccionar una fecha.");
            return;
        }

        if (horaCitaChoiceBox.getValue() == null) {
            mostrarAlerta("Error", "Debe seleccionar una hora.");
            return;
        }

        System.out.println("Email cliente: " + emailCliente);
        int clienteId = obtenerClienteIdPorEmail(emailCliente);
        System.out.println("Cliente ID obtenido: " + clienteId);

        if (clienteId == -1) {
            mostrarAlerta("Error", "No se encontró ningún cliente con ese correo.");
            return;
        }
        int citasExistentes = contarCitasClienteEnFecha(clienteId, datePickerFecha.getValue());
        if (citasExistentes >= 3) {
            mostrarAlerta("Límite alcanzado", "Ya tienes 3 citas registradas para esta fecha.");
            return;
        }

        Cita cita = new Cita();
        cita.setClienteId(clienteId);
        cita.setPerroId(perro.getId());
        cita.setFechaCita(datePickerFecha.getValue());
        cita.setHora(extraerHoraEntera(horaCitaChoiceBox.getValue()));
        cita.setEstado("Pendiente");
        cita.setDonacion(donacionChoiceBox.getValue());
        cita.setFechaAlta(LocalDate.now());
        cita.setFechaModificacion(LocalDate.now());

        boolean exito = CitasDAO.insertarCita(cita);

        if (exito) {
            mostrarAlerta("Cita solicitada", "Su cita ha sido registrada correctamente.");
            cerrarVentana();
        } else {
            mostrarAlerta("Error", "No se pudo registrar la cita, inténtelo de nuevo.");
        }
    }


    private int contarCitasClienteEnFecha(int clienteId, LocalDate fecha) {
        String sql = "SELECT COUNT(*) AS total FROM Reservan WHERE Cliente_ID = ? AND Fecha_cita = ?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.setDate(2, Date.valueOf(fecha));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int extraerHoraEntera(String textoHora) {
        try {
            return Integer.parseInt(textoHora.split("h")[0]);
        } catch (Exception e) {
            return 0;
        }
    }

    @FXML
    private void btnAtras(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerfilPerro.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnAtras.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cerrarVentana() {
        Stage stage = (Stage) btnConfirmar.getScene().getWindow();

    }

    private int obtenerClienteIdPorEmail(String email) {
        int clienteId = -1;
        String sql = "SELECT ID FROM Clientes WHERE Correo_Electronico = ?";
        try (Connection conn = ConexionBaseDatos.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                clienteId = rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienteId;
    }

}
