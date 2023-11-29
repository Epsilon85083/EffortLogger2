package EffortLoggerV2;

import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class AppController {
    private Stage primaryStage;

    public AppController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void init() {
        primaryStage.setTitle("EffortLoggerV2 Application");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(1080);

        Scene scene = createTabPaneScene();
        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    private Scene createTabPaneScene() {
        TabPane tabPane = new BuildTabs(primaryStage).getTabPane();
        return new Scene(tabPane, 1200, 1080);
    }
}