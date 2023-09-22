module com.example.inventorymanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.inventorymanagement to javafx.fxml;
    exports com.example.inventorymanagement;
    exports com.example.inventorymanagement.models;
}