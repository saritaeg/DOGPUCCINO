package org.example.proyecto.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.proyecto.dao.PerrosCliDAO;
import org.example.proyecto.modelo.Clientes;
import org.example.proyecto.modelo.Perro;
import org.example.proyecto.modelo.Sesion;
import org.example.proyecto.modelo.Usuario;

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

    private Usuario usuario;
    private Clientes clientes;

    public void setCliente(Clientes cliente) {
        this.clientes = cliente;
    }


    @FXML
    public void initialize() {
        this.usuario = Sesion.getUsuario();
        if (usuario != null) {
            if (usuario.getCliente() != null) {
                this.emailCliente = usuario.getCliente().getEmail();
            }
            cargarPerros();
            mostrarPerros();
        }
    }



    private void cargarPerros() {

        perros = PerrosCliDAO.obtenerPerrosCliente(emailCliente);
    }

    private void mostrarPerros() {
        mostrarPerroEnPosicion(indice, txtNombreCli, txtRazaCli, txtSexoCli, txtFechaNacimientoCli, txtAdoptadoCli, txtProtectoraCli, imagenPerro1);
        mostrarPerroEnPosicion(indice + 1, txtNombreCli2, txtRazaCli2, txtSexoCli2, txtFechaNacimientoCli2, txtAdoptadoCli2, txtProtectoraCli2, imagenPerro2);
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
                controlador.setCliente(usuario.getCliente());
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
    private void btnVolverInicio(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnVolverInicio.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnPerrosCliente.getScene().getWindow();

            Object controlador = fxmlLoader.getController();
            usuario = Sesion.getUsuario();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNotificacionesCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotCliCitas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificacionesCliente.getScene().getWindow();


            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCitasCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPasadas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCitasCliente.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnNosotrosCliente(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNosotrosCliente.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



