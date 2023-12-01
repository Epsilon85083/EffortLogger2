package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLController {

    private Stage stage;
    private Scene mainScene;

    public FXMLController(Stage stage) {
        this.stage = stage;
        
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        // Switch back to the main scene
        stage.setScene(mainScene);
    }
}

