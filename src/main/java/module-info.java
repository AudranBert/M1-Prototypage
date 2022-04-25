module com.example.m1prototyopage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens prototyopage to javafx.fxml;
    exports prototyopage;
    exports prototyopage.Controllers;
    opens prototyopage.Controllers to javafx.fxml;

    exports prototyopage.Controllers.ComponentControllers;
    opens prototyopage.Controllers.ComponentControllers to javafx.fxml;
}