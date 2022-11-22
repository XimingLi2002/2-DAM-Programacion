package FormularioPrincipal;

import InterfazHotel.MainApplication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FormularioPrincipalController {


    @FXML
    protected void openInterfazHotel(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Interfaz Hotel");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void closeApp(ActionEvent event){
        
    }


}
