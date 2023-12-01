package EffortLoggerV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShareWorkPage {
    private Scene shareWorkPageScene;
    private Stage primaryStage;

    public ShareWorkPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox shareWorkContent = new VBox();
        shareWorkContent.getChildren().addAll(createHeader(), createShareWorkSection(), createCategorySection(), createButtons());
        shareWorkPageScene = new Scene(shareWorkContent, 1200, 1080);
    }

    public Scene getShareWorkPageScene() {
        return shareWorkPageScene;
    }

    private VBox createHeader() {
        Label headerLabel = new Label("Share Your Work:");
        headerLabel.setStyle("-fx-font-size: 60px; -fx-font-weight: bold;");
        VBox headerBox = new VBox(headerLabel);
        headerBox.setAlignment(Pos.CENTER_LEFT);
        return headerBox;
    }

    private ScrollPane createShareWorkSection() {
        VBox shareWorkSection = new VBox();
        ScrollPane scrollPane = new ScrollPane(shareWorkSection);
        scrollPane.setMinViewportHeight(300);

        for (int i = 1; i <= 10; i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.setStyle("-fx-font-size: 50px;");

            Label nameLabel = new Label("Sample Name " + i);
            nameLabel.setStyle("-fx-font-size: 50px;");

            HBox entryBox = new HBox(checkBox, nameLabel);
            entryBox.setAlignment(Pos.CENTER_LEFT);
            entryBox.setSpacing(10);

            shareWorkSection.getChildren().add(entryBox);
        }

        return scrollPane;
    }

    private VBox createCategorySection() {
        VBox categorySection = new VBox();
        categorySection.setAlignment(Pos.CENTER_LEFT);

        for (int i = 1; i <= 5; i++) {
            CheckBox categoryCheckBox = new CheckBox("Category " + i);
            categoryCheckBox.setStyle("-fx-font-size: 50px;");
            categorySection.getChildren().add(categoryCheckBox);
        }

        return categorySection;
    }

    private HBox createButtons() {
        Button backButton = createButton("Back");
        backButton.setOnAction(e -> returnToTabPane(primaryStage));
        Button submitButton = createButton("Submit");
        submitButton.setOnAction(e -> handleSubmitButtonClick());

        HBox buttonBox = new HBox(backButton, submitButton);
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.setSpacing(20);

        return buttonBox;
    }
    
    private void returnToTabPane(Stage primaryStage) {
    	primaryStage.setScene(new BuildTabs(primaryStage).getTabPaneScene());
    }

    private Button createButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-font-size: 20px;");
        button.setMinWidth(150);
        return button;
    }

    private void handleSubmitButtonClick() {
        System.out.println("Submit button clicked");
    }
}
