package org.example.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.proyecto.utils.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class Main extends Application   {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/proyecto/VistaInicio.fxml"));
        Scene scene = new Scene((Parent) FXMLLoader.load(fxmlLoader.getLocation()));
        stage.setTitle("DOPUCCINOO");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
        try {
            ConexionBaseDatos conexionBD = ConexionBaseDatos.getInstance();
            Connection conexion = conexionBD.getConnection();

            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conexión exitosa a la base de datos.");
                conexion.close();
            } else {
                System.out.println("La conexión no se pudo establecer.");
            }
        } catch (Exception e) {
            System.err.println(" Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
    }

