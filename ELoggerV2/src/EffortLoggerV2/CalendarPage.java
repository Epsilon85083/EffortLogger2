package EffortLoggerV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CalendarPage {
    private Scene calendarPageScene;
    private Stage primaryStage;

    public CalendarPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox calendarContent = new VBox();
        calendarContent.getChildren().addAll(createHeader(), createCalendarGrid(), createButtons());
        calendarPageScene = new Scene(calendarContent, 1200, 1080);
    }

    public Scene getCalendarPageScene() {
        return calendarPageScene;
    }

    private VBox createHeader() {
        Text headerText = new Text("Today's Date: November 19th 2023");
        headerText.setStyle("-fx-font-size: 20px;");
        VBox headerBox = new VBox(headerText);
        headerBox.setAlignment(Pos.CENTER);
        return headerBox;
    }

    private GridPane createCalendarGrid() {
        GridPane calendarGrid = new GridPane();
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setHgap(5);
        calendarGrid.setVgap(5);

        int dayNumber = 1;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 7; col++) {
                Rectangle daySquare = new Rectangle(100, 100);
                daySquare.setFill(Color.WHITE);
                daySquare.setStroke(Color.BLACK);

                Text dayNumberText = new Text(Integer.toString(dayNumber));
                dayNumberText.setStyle("-fx-font-size: 16px;");

                VBox squareContent = new VBox(dayNumberText, daySquare);
                squareContent.setAlignment(Pos.CENTER);

                daySquare.setOnMouseClicked(event -> toggleColor(daySquare));

                calendarGrid.add(squareContent, col, row);
                dayNumber++;
            }
        }

        return calendarGrid;
    }

    private HBox createButtons() {
        Button backButton = createButton("Back");
        Button confirmationButton = createButton("Confirm");
        confirmationButton.setStyle("-fx-font-size: 18px;");

        HBox buttonBox = new HBox(backButton, confirmationButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setSpacing(20);

        return buttonBox;
    }

    private Button createButton(String buttonText) {
        Button button = new Button(buttonText);
        button.setStyle("-fx-font-size: 20px;");
        button.setMinWidth(150);
        button.setOnAction(e -> handleButtonClick(buttonText));
        return button;
    }

    private void toggleColor(Rectangle daySquare) {
        if (daySquare.getFill().equals(Color.WHITE)) {
            daySquare.setFill(Color.GREEN);
        } else {
            daySquare.setFill(Color.WHITE);
        }
    }

    private void handleButtonClick(String buttonText) {
        if (buttonText.equals("Back")) {
            returnToTabPane();
        } else if (buttonText.equals("Confirm")) {
            System.out.println("Confirmation button clicked");
        }
    }

    private void returnToTabPane() {
        primaryStage.setScene(new BuildTabs(primaryStage).getTabPaneScene());
    }
}
