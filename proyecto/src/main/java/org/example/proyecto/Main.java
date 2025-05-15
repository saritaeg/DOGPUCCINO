package org.example.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.proyecto.dao.UsuarioDAO;
import org.example.proyecto.utils.ConexionBaseDatos;

<<<<<<< HEAD


=======
>>>>>>> 0b2186c1a55b995a81f14d6e70090042470f41e9
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Main extends Application   {
    @Override
    public void start( Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/proyecto/VistaInicio.fxml"));
        Scene scene = new Scene((Parent) FXMLLoader.load(fxmlLoader.getLocation()));
        stage.setTitle("DOPUCCINOO");
        stage.setScene(scene);
        stage.show();

    }



}

