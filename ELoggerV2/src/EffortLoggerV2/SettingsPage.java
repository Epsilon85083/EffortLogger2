package EffortLoggerV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsPage {
    private Scene settingsPageScene;
    private Stage primaryStage;

    public SettingsPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox settingsContent = new VBox();
        settingsContent.setAlignment(Pos.CENTER);

        Button changeResolutionButton = createButton("Change Resolution");
        changeResolutionButton.setOnAction(e -> handleChangeResolution());

        Button saveSettingsButton = createButton("Save Settings");
        saveSettingsButton.setOnAction(e -> handleSaveSettings());

        Button backButton = createButton("Back");
        backButton.setOnAction(e -> returnToTabPane(primaryStage));

        settingsContent.getChildren().addAll(changeResolutionButton, saveSettingsButton, backButton);

        settingsPageScene = new Scene(settingsContent, 1200, 1080);
    }

    public Scene getSettingsPageScene() {
        return settingsPageScene;
    }

    private Button createButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-font-size: 20px;");
        button.setMinWidth(550);
        button.setMinHeight(200); 
        return button;
    }

    private void handleChangeResolution() {
        System.out.println("Change Resolution button clicked");
    }

    private void handleSaveSettings() {
        System.out.println("Save Settings button clicked");
    }

    private void returnToTabPane(Stage primaryStage) {
        primaryStage.setScene(new BuildTabs(primaryStage).getTabPaneScene());
    }
}
