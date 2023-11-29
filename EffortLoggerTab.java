package EffortLoggerV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EffortLoggerTab {
    private Scene tab1Scene;
    private ScrollPane tab1Content;

    public EffortLoggerTab(Stage primaryStage) {
        VBox buttonsVBox = new VBox();
        buttonsVBox.setAlignment(Pos.CENTER);

        Button activityPageButton = new Button("Log Daily Activities");
        activityPageButton.setStyle("-fx-font-size: 50px;");
        activityPageButton.setMinWidth(1200);
        activityPageButton.setMinHeight(196);
        activityPageButton.setOnAction(e -> openActivityPage(primaryStage));

        Button calendarPageButton = new Button("Edit Your Calendar");
        calendarPageButton.setStyle("-fx-font-size: 50px;");
        calendarPageButton.setMinWidth(1200);
        calendarPageButton.setMinHeight(196);
        calendarPageButton.setOnAction(e -> openCalendarPage(primaryStage));

        Button shareWorkPageButton = new Button("Share Your Work");
        shareWorkPageButton.setStyle("-fx-font-size: 50px;");
        shareWorkPageButton.setMinWidth(1200);
        shareWorkPageButton.setMinHeight(196);
        shareWorkPageButton.setOnAction(e -> openShareWorkPage(primaryStage));

        Button discussionPageButton = new Button("Discussion Page");
        discussionPageButton.setStyle("-fx-font-size: 50px;");
        discussionPageButton.setMinWidth(1200);
        discussionPageButton.setMinHeight(196);
        discussionPageButton.setOnAction(e -> openDiscussionPage(primaryStage));

        Button settingsPageButton = new Button("Change Your Settings");
        settingsPageButton.setStyle("-fx-font-size: 50px;");
        settingsPageButton.setMinWidth(1200);
        settingsPageButton.setMinHeight(196);
        settingsPageButton.setOnAction(e -> openSettingsPage(primaryStage));

        buttonsVBox.getChildren().addAll(activityPageButton, calendarPageButton, shareWorkPageButton, discussionPageButton, settingsPageButton);


        tab1Content = new ScrollPane(buttonsVBox);
        tab1Scene = new Scene(tab1Content, 1200, 1080);
    }

    public VBox getTab1Content(Stage primaryStage) {
        VBox content = new VBox();
        content.getChildren().addAll(tab1Content);
        return content;
    }
    
    public Scene getTab1Scene() {
        return tab1Scene;
    }

    private void openActivityPage(Stage primaryStage) {
        primaryStage.setScene(new ActivityPage(primaryStage).getActivityPageScene());
    }
    
    private void openCalendarPage(Stage primaryStage) {
        primaryStage.setScene(new CalendarPage(primaryStage).getCalendarPageScene());
    }

    private void openShareWorkPage(Stage primaryStage) {
        primaryStage.setScene(new ShareWorkPage(primaryStage).getShareWorkPageScene());
    }

    private void openDiscussionPage(Stage primaryStage) {
        primaryStage.setScene(new DiscussionPage(primaryStage).getDiscussionPageScene());
    }

    private void openSettingsPage(Stage primaryStage) {
        primaryStage.setScene(new SettingsPage(primaryStage).getSettingsPageScene());
    }

}
