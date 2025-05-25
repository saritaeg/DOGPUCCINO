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
import javafx.stage.Stage;
import org.example.proyecto.dao.PerfilPerroDAO;
import org.example.proyecto.dao.SolicitudAdopcionDAO;
import org.example.proyecto.modelo.Clientes;
import org.example.proyecto.modelo.Perro;
import org.example.proyecto.modelo.Sesion;
import org.example.proyecto.modelo.Usuario;
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
    private Usuario usuario;

    private Clientes clientes;

    public void setCliente(Clientes cliente) {
        this.clientes = cliente;
    }

    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();

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
    private boolean btnAdopcion(ActionEvent event) {
        int idCliente = clientes.getIdCliente();
        int idPerro = perro.getId();

        boolean exito = SolicitudAdopcionDAO.registrarSolicitudAdopcion(idCliente, idPerro);

        if (exito) {
            System.out.println("Solicitud registrada correctamente.");
        } else {
            System.out.println("Error al registrar la solicitud.");
        }

        return exito;
    }
    @FXML
    private void btnCita(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaTramitarCita.fxml"));
            Parent root = loader.load();

            TramitarCitaControlador controlador = loader.getController();
            controlador.setPerro(perro);
            controlador.setEmailCliente(emailCliente);

            Stage stage = (Stage) btnMenu.getScene().getWindow();
            stage.setScene(new Scene(root));
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

    private void mostrarDatosPerro() {
        if (perro != null) {
            System.out.println("ID del perro: " + perro.getId());
            txtInf.setText("Nombre: " + perro.getNombre() + "\n"
                    + "Raza: " + perro.getRaza() + "\n"
                    + "Sexo: " + perro.getSexo() + "\n"
                    + "Fecha Nacimiento: " + perro.getFechaNacimiento());

            txtPatologias.setText(PerfilPerroDAO.obtenerPatologiasPerro(perro.getId()));


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
