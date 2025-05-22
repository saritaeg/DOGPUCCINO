package org.example.proyecto.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.example.proyecto.dao.EditarProtectoraDAO;
import org.example.proyecto.dao.PerrosProtDAO;
import org.example.proyecto.modelo.Perro;
import org.example.proyecto.modelo.Protectoras;
import org.example.proyecto.modelo.Usuario;

import java.io.IOException;
import java.util.List;

public class PerrosProtControlador {

    @FXML private Button btnVolverAtras;
    @FXML private Button btnPerrosProtectora;
    @FXML private Button btnNotificacionesProtectora;
    @FXML private Button btnCitasProtectora;
    @FXML private Button btnSobreNosotrosProtectora;
    @FXML private Button btnEditarPerfilProtectora;
    @FXML private Button btnAñadirPerroProtectora;
    @FXML private Button btnVolverPerrosAtrasProtectora;
    @FXML private Button btnBotonAvanzarPerrosProtectora;
    @FXML private TextField txtNombrePro;
    @FXML private TextField txtRazaPro;
    @FXML private TextField txtSexoPro;
    @FXML private TextField txtFechaNacimientoPro;
    @FXML private TextField txtAdoptadoPro;
    @FXML private TextField txtProtectoraPro;
    @FXML private Button btnEditarPerroProtectora;
    @FXML private TextField txtNombrePro2;
    @FXML private TextField txtRazaPro2;
    @FXML private TextField txtSexoPro2;
    @FXML private TextField txtFechaNacimientoPro2;
    @FXML private TextField txtAdoptadoPro2;
    @FXML private TextField txtProtectoraPro2;
    @FXML private Button btnEditarPerroProtectora2;

    private List<Perro> perros;
    private int indice = 0;

    private String emailProtectora;
    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void inicializarPerros(Usuario usuario) {
        this.usuario = usuario;
        perros = PerrosProtDAO.obtenerPerrosProtectora(usuario.getCifProtectora());
        mostrarPerros();
    }

    private void mostrarPerros() {
        mostrarPerroEnPosicion(indice, txtNombrePro, txtRazaPro, txtSexoPro, txtFechaNacimientoPro, txtAdoptadoPro, txtProtectoraPro);
        mostrarPerroEnPosicion(indice + 1, txtNombrePro2, txtRazaPro2, txtSexoPro2, txtFechaNacimientoPro2, txtAdoptadoPro2, txtProtectoraPro2);
    }

    private void mostrarPerroEnPosicion(int pos, TextField nombre, TextField raza, TextField sexo,
                                        TextField fechaNacimiento, TextField adoptado, TextField protectora) {
        if (pos >= 0 && pos < perros.size()) {
            Perro p = perros.get(pos);
            nombre.setText(p.getNombre());
            raza.setText(p.getRaza());
            sexo.setText(p.getSexo() != null ? p.getSexo().name() : "");
            fechaNacimiento.setText(p.getFechaNacimiento());
            if (p.getAdoptado() != null) {
                adoptado.setText(p.getAdoptado() == Perro.Adoptado.S ? "Sí" : "No");
            } else {
                adoptado.setText("");
            }
            protectora.setText(p.getCifProtectora());
        } else {
            limpiarCampos(nombre, raza, sexo, fechaNacimiento, adoptado, protectora);
        }
    }

    private void limpiarCampos(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
    }

    @FXML
    private void btnVolverAtras(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaInicio.fxml", btnVolverAtras);
    }

    @FXML
    private void btnPerrosProtectora(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaPerrosProt.fxml", btnPerrosProtectora);
    }

    @FXML
    private void btnNotificacionesProtectora(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaNotProtNuevacita.fxml", btnNotificacionesProtectora);
    }

    @FXML
    private void btnCitasProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasProtec.fxml"));
            Parent root = fxmlLoader.load();

            // Obtener controlador y pasar usuario
            Object controlador = fxmlLoader.getController();
            if (controlador instanceof CitasProtControlador) {
                ((CitasProtControlador) controlador).setUsuario(usuario);
            }

            Stage stage = (Stage) btnCitasProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btnSobreNosotrosProtectora(ActionEvent event) {
        cargarVista("/org/example/proyecto/VistaSobreNosotrosPro.fxml", btnSobreNosotrosProtectora);
    }

    @FXML
    private void btnEditarPerfilProtectora(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaEditarPerfilProtectora.fxml"));
            Parent root = fxmlLoader.load();
            EditarProtectoraControlador controlador = fxmlLoader.getController();

            controlador.inicializarDatosPorCif(usuario.getCifProtectora());


            Stage stage = (Stage) btnEditarPerfilProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void btnAñadirPerroProtectora(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaAñadirPerro.fxml"));
            Parent root = loader.load();

            AñadirPerroControlador controlador = loader.getController();
            controlador.setUsuario(usuario);

            Stage stage = (Stage) btnAñadirPerroProtectora.getScene().getWindow();
            stage.setScene(new Scene(root));

            stage.setOnHiding(e -> {
                perros = PerrosProtDAO.obtenerPerrosProtectora(String.valueOf(usuario.getCifProtectora()));
                indice = 0;
                mostrarPerros();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnVolverPerrosAtrasProtectora(ActionEvent event) {
        if (indice - 2 >= 0) {
            indice -= 2;
            mostrarPerros();
        }
    }

    @FXML
    private void btnBotonAvanzarPerrosProtectora(ActionEvent event) {
        if (indice + 2 < perros.size()) {
            indice += 2;
            mostrarPerros();
        }
    }

    @FXML
    private void btnEditarPerroProtectora(ActionEvent event) {
        // Implementar edición para perro en índice 'indice'
    }

    @FXML
    private void btnEditarPerroProtectora2(ActionEvent event) {
        // Implementar edición para perro en índice 'indice + 1'
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