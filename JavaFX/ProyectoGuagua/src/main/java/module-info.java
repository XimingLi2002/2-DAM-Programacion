module main.proyectoguagua {
    requires javafx.controls;
    requires javafx.fxml;


    opens FirstPane to javafx.fxml;
    exports FirstPane;
}