module com.example.interfazhotel {
    requires javafx.controls;
    requires javafx.fxml;

    opens InterfazHotel to javafx.fxml;
    exports InterfazHotel;

    opens FormularioPrincipal to javafx.fxml;
    exports FormularioPrincipal;
}
