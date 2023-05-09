module com.example.librarymanagment {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    //requires java.graphics;

    requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.mail;


//    opens com.example.librarymanagment to javafx.fxml;
//    exports com.example.librarymanagment;
    exports com.example.librarymanagment.controls;
    opens com.example.librarymanagment.controls to javafx.fxml;
    exports com.example.librarymanagment.model;
    opens com.example.librarymanagment.model to javafx.fxml;
}