package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.proyecto.dao.PerrosCliDAO;
import org.example.proyecto.modelo.Clientes;
import org.example.proyecto.modelo.Perro;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PerrosCliControlador {
    @FXML private TextField txtNombreCli, txtRazaCli, txtSexoCli, txtFechaNacimientoCli, txtAdoptadoCli, txtProtectoraCli;
    @FXML private TextField txtNombreCli2, txtRazaCli2, txtSexoCli2, txtFechaNacimientoCli2, txtAdoptadoCli2, txtProtectoraCli2;

    @FXML private Button btnVolverInicio, btnEditarPerfilCliente, btnPerrosCliente, btnNotificacionesCliente;
    @FXML private Button btnCitasCliente, btnNosotrosCliente, btnMasPerrosCliente, btnMasInformacion, btnMasInformacion2, btnMasPerroCliente2, btnAnteriorPerrosCliente;

    @FXML
    private ImageView imagen;

    @FXML
    private ImageView imagenPerro1;

    @FXML
    private ImageView imagenPerro2;
    private List<Perro> perros;
    private int indice = 0;
    private String emailCliente;

    public void inicializarPerros(String emailCliente) {
        this.emailCliente = emailCliente;
        cargarPerros();
        mostrarPerros();
    }

    private void cargarPerros() {
        perros = PerrosCliDAO.obtenerPerrosCliente(emailCliente);
    }

    private void mostrarPerros() {
        mostrarPerroEnPosicion(indice, txtNombreCli, txtRazaCli, txtSexoCli, txtFechaNacimientoCli, txtAdoptadoCli, txtProtectoraCli, imagenPerro1);
        mostrarPerroEnPosicion(indice + 1, txtNombreCli2, txtRazaCli2, txtSexoCli2, txtFechaNacimientoCli2, txtAdoptadoCli2, txtProtectoraCli2, imagenPerro2);
    }
    public void actualizarListaPerros() {
        cargarPerros();
        indice = 0;
        mostrarPerros();
    }
    private boolean hayPerros() {
        return perros != null && !perros.isEmpty();
    }
    @FXML
    private void imagenClicada1() {
        abrirDetallePerro(indice);
    }

    @FXML
    private void imagenClicada2() {
        abrirDetallePerro(indice + 1);
    }
    private void mostrarMensajeNoMasPerros() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText("No hay más perros para mostrar.");
        alert.showAndWait();
    }
    private void cargarImagenSeguro(ImageView imageView, String rutaImagen) {
        try {
            Image img = new Image("file:" + rutaImagen);
            imageView.setImage(img);
        } catch (Exception e) {
            imageView.setImage(new Image("/resources/imagenes/fondo.png"));
        }
    }

    private void mostrarPerroEnPosicion(int pos, TextField nombre, TextField raza, TextField sexo,
                                        TextField fechaNacimiento, TextField adoptado, TextField protectora, ImageView imagen) {
        if (pos >= 0 && pos < perros.size()) {
            Perro p = perros.get(pos);
            nombre.setText(p.getNombre());
            raza.setText(p.getRaza());
            sexo.setText(p.getSexo() != null ? p.getSexo().name() : "");
            fechaNacimiento.setText(p.getFechaNacimiento());
            adoptado.setText(p.getAdoptado() != null ? p.getAdoptado().name() : "");
            protectora.setText(p.getCifProtectora() != null ? p.getCifProtectora() : "");

            try {
                Image img = new Image("file:" + p.getFoto());
                imagen.setImage(img);
            } catch (Exception e) {
                imagen.setImage(null);
            }
        } else {

            nombre.setText("");
            raza.setText("");
            sexo.setText("");
            fechaNacimiento.setText("");
            adoptado.setText("");
            protectora.setText("");
            imagen.setImage(null);
        }
    }

    @FXML
    private void btnMasInformacion(ActionEvent event) {
        abrirDetallePerro(indice);
    }

    @FXML
    private void btnMasInformacion2(ActionEvent event) {
        abrirDetallePerro(indice + 1);
    }

    private void abrirDetallePerro(int pos) {
        if (pos >= 0 && pos < perros.size()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerfilPerro.fxml"));
                Parent root = loader.load();

                PerfilPerroControlador controlador = loader.getController();
                controlador.setPerro(perros.get(pos));
                controlador.setEmailCliente(emailCliente);

                Stage stage = (Stage) btnMasInformacion.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnMasPerroCliente2(ActionEvent event) {
        if (indice + 2 < perros.size()) {
            indice += 2;
            mostrarPerros();
        }
    }

    @FXML
    private void btnMasPerrosCliente(ActionEvent event) {
        if (indice - 2 >= 0) {
            indice -= 2;
            mostrarPerros();
        }
    }

    @FXML
    private void btnAnteriorPerrosCliente(ActionEvent event) {
        if (indice - 2 >= 0) {
            indice -= 2;
            mostrarPerros();
        }
    }

    @FXML
    private void btnVolverInicio(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaInicio.fxml", btnVolverInicio);
    }

    @FXML
    private void btnEditarPerfilCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaEditarPerfilCliente.fxml"));
            Parent root = loader.load();

            EditarPerfilCLiControlador controlador = loader.getController();
            controlador.inicializarDatos(emailCliente);

            Stage stage = (Stage) btnEditarPerfilCliente.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnPerrosCliente(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaPerrosCli.fxml", btnPerrosCliente);
    }

    @FXML
    private void btnNotificacionesCliente(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaNotCliCancelacion.fxml", btnNotificacionesCliente);
    }

    @FXML
    private void btnCitasCliente(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaCitasCliPasadas.fxml", btnCitasCliente);
    }

    @FXML
    private void btnNosotrosCliente(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaSobreNosotrosCli.fxml", btnNosotrosCliente);
    }

    private void cargarVista(String rutaFXML, Button botonReferencia) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Stage stage = (Stage) botonReferencia.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
