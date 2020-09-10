module com.mycompany.tramites.cliente {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.tramites.cliente to javafx.fxml;
    exports com.mycompany.tramites.cliente;
}
