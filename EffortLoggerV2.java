package EffortLoggerV2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class EffortLoggerV2 extends Application {
    private Stage primaryStage;
    private ScrollPane ActivityPageScrollPane;
    private VBox ActivityPageVBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("EffortLoggerV2 Application");

        primaryStage.setWidth(1200);
        primaryStage.setHeight(1080);

        Scene scene = createTabPaneScene();

        primaryStage.setScene(scene);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }

    private TabPane createTabPane() {
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("EffortLoggerV2");
        tab1.setStyle("-fx-font-size: 20px;");

        VBox tab1Content = createTab1Content();
        tab1.setContent(tab1Content);

        Tab tab2 = new Tab("Planning Poker");
        tab2.setStyle("-fx-font-size: 20px;");

        tabPane.getTabs().addAll(tab1, tab2);

        tabPane.setStyle("-fx-tab-min-width: 570px; -fx-tab-min-height: 50px;-fx-font-size: 20px;");

        return tabPane;
    }

    private VBox createTab1Content() {
        VBox buttonsVBox = new VBox();
        buttonsVBox.setAlignment(Pos.CENTER);

        Button activityPageButton = new Button("Log Daily Activities");
        activityPageButton.setStyle("-fx-font-size: 50px;");
        activityPageButton.setMinWidth(1200);
        activityPageButton.setMinHeight(196);
        activityPageButton.setOnAction(e -> openActivityPage());

        Button calendarPageButton = new Button("Edit Your Calendar");
        calendarPageButton.setStyle("-fx-font-size: 50px;");
        calendarPageButton.setMinWidth(1200);
        calendarPageButton.setMinHeight(196);
        calendarPageButton.setOnAction(e -> openCalendarPage());

        Button shareWorkPageButton = new Button("Share Your Work");
        shareWorkPageButton.setStyle("-fx-font-size: 50px;");
        shareWorkPageButton.setMinWidth(1200);
        shareWorkPageButton.setMinHeight(196);
        shareWorkPageButton.setOnAction(e -> openShareWorkPage());

        Button discussionPageButton = new Button("Discussion Page");
        discussionPageButton.setStyle("-fx-font-size: 50px;");
        discussionPageButton.setMinWidth(1200);
        discussionPageButton.setMinHeight(196);
        discussionPageButton.setOnAction(e -> openDiscussionPage());

        Button settingsPageButton = new Button("Change Your Settings");
        settingsPageButton.setStyle("-fx-font-size: 50px;");
        settingsPageButton.setMinWidth(1200);
        settingsPageButton.setMinHeight(196);
        settingsPageButton.setOnAction(e -> openSettingsPage());

        buttonsVBox.getChildren().addAll(activityPageButton, calendarPageButton, shareWorkPageButton, discussionPageButton, settingsPageButton);

        VBox tab1Content = new VBox(buttonsVBox);
        return tab1Content;
    }

    private Scene createDiscussionPageScene() {
        VBox discussionContent = new VBox();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> returnToTabPane());

        discussionContent.getChildren().addAll(backButton);

        return new Scene(discussionContent, 1200, 1080);
    }

    private Scene createSettingsPageScene() {
        VBox settingsContent = new VBox();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> returnToTabPane());

        settingsContent.getChildren().addAll(backButton);

        return new Scene(settingsContent, 1200, 1080);
    }

    private Scene createCalendarPageScene() {
        VBox calendarContent = new VBox();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> returnToTabPane());

        calendarContent.getChildren().addAll(backButton);

        return new Scene(calendarContent, 1200, 1080);
    }

    private Scene createShareWorkPageScene() {
        VBox shareWorkContent = new VBox();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> returnToTabPane());

        shareWorkContent.getChildren().addAll(backButton);

        return new Scene(shareWorkContent, 1200, 1080);
    }

    private void openCalendarPage() {
        primaryStage.setScene(createCalendarPageScene());
    }

    private void openShareWorkPage() {
        primaryStage.setScene(createShareWorkPageScene());
    }

    private void openDiscussionPage() {
        primaryStage.setScene(createDiscussionPageScene());
    }

    private void openSettingsPage() {
        primaryStage.setScene(createSettingsPageScene());
    }

    private void openActivityPage() {
        ActivityPageVBox = new VBox();

        // Create and add the first section
        addActivitySection(ActivityPageVBox, "  Select Activity 1:", new String[]{"Category A", "Category B", "Category C"}, "  Description 1:");

        // Create and add the second section
        addActivitySection(ActivityPageVBox, "  Select Activity 2:", new String[]{"Category X", "Category Y", "Category Z"}, "  Description 2:");

        // Create and add the third section
        addActivitySection(ActivityPageVBox, "  Select Activity 3:", new String[]{"Project 1", "Project 2", "Project 3"}, "  Description 3:");

        // Create and add the fourth section
        addActivitySection(ActivityPageVBox, "  Select Activity 4:", new String[]{"Task Alpha", "Task Beta", "Task Gamma"}, "  Description 4:");

        // Create and add the fifth section
        addActivitySection(ActivityPageVBox, "  Select Activity 5:", new String[]{"Job 1", "Job 2", "Job 3"}, "      Description 5:");

        Button backButton = new Button("Back");
        backButton.setMinSize(200, 50);
        backButton.setOnAction(e -> returnToTabPane());

        Button submissionButton = new Button("Submit");
        submissionButton.setMinSize(200, 50);
        submissionButton.setOnAction(e -> returnToTabPane());
        
        Label spacerLabel = new Label();
        spacerLabel.setMinWidth(700);

        HBox buttonBox = new HBox(backButton, spacerLabel, submissionButton);

        ActivityPageVBox.getChildren().addAll(buttonBox);

        ActivityPageScrollPane = new ScrollPane(ActivityPageVBox);

        Scene ActivityPageScene = new Scene(ActivityPageScrollPane, 1200, 1080);

        primaryStage.setScene(ActivityPageScene);
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

    private void returnToTabPane() {
        primaryStage.setScene(createTabPaneScene());
    }

    private Scene createTabPaneScene() {
        return new Scene(createTabPane(), 1200, 1080);
    }
}
