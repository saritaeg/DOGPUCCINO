module org.example.dogpuccino {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.dogpuccino to javafx.fxml;
    exports org.example.dogpuccino;
    exports org.example.dogpuccino.controlador;
    opens org.example.dogpuccino.controlador to javafx.fxml;
}