package org.example.proyecto.controlador;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.proyecto.dao.PerrosProtDAO;
import org.example.proyecto.modelo.Usuario;

import java.io.IOException;

public class CitasCliPasadasControlador {
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnPerros;
    @FXML
    private Button btnNotificaciones;
    @FXML
    private Button btnCitas;
    @FXML
    private Button btnSobreNosotros;
    @FXML
    private Button btnCitasPasadas;
    @FXML
    private Button btnCitasPendientes;

    private Usuario usuario;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void inicializarCitas(Usuario usuario) {
        this.usuario = usuario;

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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaInicio.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnAtras.getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnPerros(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaPerrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnPerros.getScene().getWindow();

            Object controlador = fxmlLoader.getController();
            if (controlador instanceof PerrosCliControlador) {
                ((PerrosCliControlador) controlador).setUsuario(usuario);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnNotificaciones(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaNotCliCancelacion.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnNotificaciones.getScene().getWindow();

            Object controlador = fxmlLoader.getController();
            if (controlador instanceof NotCliCancelacionControlador) {
                ((NotCliCancelacionControlador) controlador).setUsuario(usuario);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnCitas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPendientes.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCitas.getScene().getWindow();

            Object controlador = fxmlLoader.getController();
            if (controlador instanceof CitasCliPendientesControlador) {
                ((CitasCliPendientesControlador) controlador).setUsuario(usuario);
            }


            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnSobreNosotros(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaSobreNosotrosCli.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnSobreNosotros.getScene().getWindow();

            Object controlador = fxmlLoader.getController();
            if (controlador instanceof SobreNosotrosControlador) {
                ((SobreNosotrosControlador) controlador).setUsuario(usuario);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnCitasPasadas(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPasadas.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCitasPasadas.getScene().getWindow();

            Object controlador = fxmlLoader.getController();
            if (controlador instanceof CitasCliPasadasControlador) {
                ((CitasCliPasadasControlador) controlador).setUsuario(usuario);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void btnCitasPendientes(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/proyecto/VistaCitasCliPendientes.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = (Stage) btnCitasPendientes.getScene().getWindow();

            Object controlador = fxmlLoader.getController();
            if (controlador instanceof CitasCliPendientesControlador) {
                ((CitasCliPendientesControlador) controlador).setUsuario(usuario);
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
