package InterfazHotel;

import FormularioPrincipal.FormularioPrincipal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    @FXML
    private Label labelAvisoFumador;

    @FXML
    private CheckBox checkBoxFumador;

    @FXML
    private ComboBox comboBoxTipoHabitacion;

    @FXML
    protected void setCheckBoxFumador() {
        labelAvisoFumador.setVisible(checkBoxFumador.isSelected());
    }

    @FXML
    protected void backToFormularioPrincipal(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(FormularioPrincipal.class.getResource("formulario-principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Formulario Principal");
        stage.setScene(scene);
        stage.show();
    }

    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxTipoHabitacion.getItems().addAll("Doble de uso individual","Doble","Junior Suite","Suite");
    }

     */
}