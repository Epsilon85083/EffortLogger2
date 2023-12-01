package EffortLoggerV2;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import login.LoginViewController;

public class AppController {
    private Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {
//        primaryStage.setTitle("EffortLoggerV2 Application");
//        primaryStage.setWidth(1200);
//        primaryStage.setHeight(1080);
//
        Scene mainScene = createTabPaneScene();
//        primaryStage.setScene(scene);
//        primaryStage.setFullScreenExitHint("");
//        primaryStage.show();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/LoginPage.fxml"));
            // Pass the stage or main scene to the controller
//            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//            Scene mainScene = createTabPaneScene();
            LoginViewController controller = new LoginViewController(primaryStage, mainScene);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Scene createTabPaneScene() {
        TabPane tabPane = new BuildTabs(primaryStage).getTabPane();
        return new Scene(tabPane, 1200, 1080);
    }
}