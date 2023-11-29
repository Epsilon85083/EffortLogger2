package EffortLoggerV2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActivityPage {
    private Scene activityPageScene;

    public ActivityPage(Stage primaryStage) {
        VBox ActivityPageVBox = new VBox();

        addActivitySection(ActivityPageVBox, "  Select Activity 1:", new String[]{"Category A", "Category B", "Category C"}, "  Description 1:");

        addActivitySection(ActivityPageVBox, "  Select Activity 2:", new String[]{"Category X", "Category Y", "Category Z"}, "  Description 2:");

        addActivitySection(ActivityPageVBox, "  Select Activity 3:", new String[]{"Project 1", "Project 2", "Project 3"}, "  Description 3:");

        addActivitySection(ActivityPageVBox, "  Select Activity 4:", new String[]{"Task Alpha", "Task Beta", "Task Gamma"}, "  Description 4:");

        addActivitySection(ActivityPageVBox, "  Select Activity 5:", new String[]{"Job 1", "Job 2", "Job 3"}, "  Description 5:");

        Button backButton = new Button("Back");
        backButton.setMinSize(200, 50);
        backButton.setOnAction(e -> returnToTabPane(primaryStage));

        Button submissionButton = new Button("Submit");
        submissionButton.setMinSize(200, 50);
        submissionButton.setOnAction(e -> returnToTabPane(primaryStage));

        Label spacerLabel = new Label();
        spacerLabel.setMinWidth(700);

        HBox buttonBox = new HBox(backButton, spacerLabel, submissionButton);

        ActivityPageVBox.getChildren().addAll(buttonBox);

        ScrollPane ActivityPageScrollPane = new ScrollPane(ActivityPageVBox);

        activityPageScene = new Scene(ActivityPageScrollPane, 1200, 1080);
    }

    public Scene getActivityPageScene() {
        return activityPageScene;
    }

    private void addActivitySection(VBox container, String selectActivityLabel, String[] comboBoxItems, String descriptionLabel) {
        VBox spacerVBox = new VBox();
        spacerVBox.setPrefHeight(10);

        HBox hbox1 = new HBox();
        Label label1 = new Label(selectActivityLabel);
        label1.setPrefWidth(125);
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.getItems().addAll(comboBoxItems);
        comboBox1.setPrefWidth(600);
        hbox1.getChildren().addAll(label1, comboBox1);

        Label label2 = new Label(descriptionLabel);
        label2.setPrefWidth(125);
        TextArea textArea1 = new TextArea();
        textArea1.setPrefWidth(600);

        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(label2, textArea1);

        container.getChildren().addAll(spacerVBox, hbox1, hbox2);
    }

    private void returnToTabPane(Stage primaryStage) {
        primaryStage.setScene(new BuildTabs(primaryStage).getTabPaneScene());
    }
}
