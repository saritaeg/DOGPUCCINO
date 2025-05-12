module org.example.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;
    opens org.example.proyecto.controlador to javafx.fxml;

    opens org.example.proyecto to javafx.fxml;
    exports org.example.proyecto;
    opens org.example.proyecto.utils to javafx.fxml;
}