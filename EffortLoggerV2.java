package EffortLoggerV2;

import javafx.application.Application;
import javafx.stage.Stage;

public class EffortLoggerV2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AppController appController = new AppController(primaryStage);
        appController.init();
    }
}

