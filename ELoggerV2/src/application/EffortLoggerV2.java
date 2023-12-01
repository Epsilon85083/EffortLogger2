//Author: Adam Colyar
package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import login.LoginViewController;
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

//        primaryStage.setWidth(1000);
//        primaryStage.setHeight(800);

        Scene mainScene = createTabPaneScene();
//
//        primaryStage.setScene(scene);
//        primaryStage.setFullScreenExitHint("");
//        primaryStage.show();
        
//        try {
//			Parent root = FXMLLoader.load(getClass().getResource("../login/LoginPage.fxml")); //opens login page
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Login");
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
    	
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
        activityPageButton.setMinWidth(120);
        activityPageButton.setMinHeight(19);
        activityPageButton.setOnAction(e -> openActivityPage());

        Button calendarPageButton = new Button("Edit Your Calendar");
        calendarPageButton.setStyle("-fx-font-size: 50px;");
        calendarPageButton.setMinWidth(120);
        calendarPageButton.setMinHeight(19);
        calendarPageButton.setOnAction(e -> openCalendarPage());

        Button shareWorkPageButton = new Button("Share Your Work");
        shareWorkPageButton.setStyle("-fx-font-size: 50px;");
        shareWorkPageButton.setMinWidth(120);
        shareWorkPageButton.setMinHeight(19);
        shareWorkPageButton.setOnAction(e -> openShareWorkPage());

        Button discussionPageButton = new Button("Discussion Page");
        discussionPageButton.setStyle("-fx-font-size: 50px;");
        discussionPageButton.setMinWidth(120);
        discussionPageButton.setMinHeight(19);
        discussionPageButton.setOnAction(e -> openDiscussionPage());

        Button settingsPageButton = new Button("Change Your Settings");
        settingsPageButton.setStyle("-fx-font-size: 50px;");
        settingsPageButton.setMinWidth(120);
        settingsPageButton.setMinHeight(19);
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
//    	Parent root = null;
//		try {
//			root = FXMLLoader.load(getClass().getResource("/EffortLoggerConsole.fxml"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Scene scene = new Scene(root);
//		
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EffortLoggerConsole.fxml"));
            // Pass the stage or main scene to the controller
            Scene mainScene = createTabPaneScene();
            EffortConsoleController controller = new EffortConsoleController(primaryStage, mainScene);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	

//        primaryStage.setScene(scene);
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