package EffortLoggerV2;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlanningPokerTab {
    private Scene planningPokerScene;
    private ScrollPane planningPokerContent;

    public PlanningPokerTab(Stage primaryStage) {
        VBox planningPokerVBox = new VBox();
        planningPokerVBox.setAlignment(Pos.CENTER);
        planningPokerVBox.setSpacing(20);

        HBox projectTitleBlock = createProjectTitleBlock();
        planningPokerVBox.getChildren().add(projectTitleBlock);

        HBox leftRightSeparation = createLeftRightSeparation();
        planningPokerVBox.getChildren().add(leftRightSeparation);

        planningPokerContent = new ScrollPane(planningPokerVBox);
        planningPokerScene = new Scene(planningPokerContent, 1200, 1080);
    }

    public VBox getTab2Content(Stage primaryStage) {
        VBox content = new VBox();
        content.getChildren().addAll(planningPokerContent);
        return content;
    }

    public Scene getPlanningPokerScene() {
        return planningPokerScene;
    }

    private HBox createProjectTitleBlock() {
        Label projectTitleLabel = new Label("Project Title");
        projectTitleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        
        Rectangle box = new Rectangle(1100, 200);
        box.setFill(Color.TRANSPARENT);
        box.setStroke(Color.BLACK);

        StackPane projectTitleStackPane = new StackPane(box, projectTitleLabel);
        HBox projectTitleBlock = new HBox(projectTitleStackPane);
        projectTitleBlock.setAlignment(Pos.CENTER);
        projectTitleBlock.setPadding(new Insets(20));
        return projectTitleBlock;
    }

    private HBox createLeftRightSeparation() {
        StackPane storyTitleStackPane = createStoryTitleStackPane();

        VBox rightSide = createHistoryBoxes();

        HBox leftRightSeparation = new HBox(storyTitleStackPane, rightSide);
        leftRightSeparation.setAlignment(Pos.CENTER);
        leftRightSeparation.setSpacing(50);

        return leftRightSeparation;
    }

    private StackPane createStoryTitleStackPane() {
        Label storyTitleLabel = new Label("Story Title");
        storyTitleLabel.setStyle("-fx-font-size: 120px; -fx-font-weight: bold;");

        Rectangle box = new Rectangle(600, 600);
        box.setFill(Color.TRANSPARENT);
        box.setStroke(Color.BLACK);

        StackPane storyTitleStackPane = new StackPane(box, storyTitleLabel);
        
        VBox descriptionLabels = new VBox(
                createDescriptionLabel("Sample Description 1"),
                createDescriptionLabel("Sample Description 2"),
                createDescriptionLabel("Sample Description 3")
        );
        descriptionLabels.setAlignment(Pos.CENTER);

        storyTitleStackPane.getChildren().add(descriptionLabels);

        return storyTitleStackPane;
    }

    private VBox createHistoryBoxes() {
        VBox historyBoxesVBox = new VBox();
        historyBoxesVBox.setAlignment(Pos.TOP_LEFT);

        historyBoxesVBox.getChildren().addAll(
                createHistoryBox("History/Past Story 1"),
                createHistoryBox("History/Past Story 2"),
                createHistoryBox("History/Past Story 3"),
                createHistoryBox("History/Past Story 4"),
                createHistoryBox("History/Past Story 5"),
                createHistoryBox("History/Past Story 6")
        );

        return historyBoxesVBox;
    }

    private StackPane createHistoryBox(String labelText) {
        Label historyLabel = new Label(labelText);
        historyLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        Rectangle box = new Rectangle(500, 100);
        box.setFill(Color.TRANSPARENT);
        box.setStroke(Color.BLACK);

        StackPane historyBox = new StackPane(box, historyLabel);

        historyBox.setOnMouseClicked(event -> {
            if (box.getFill().equals(Color.PURPLE)) {
                box.setFill(Color.TRANSPARENT);
                historyLabel.setVisible(true);
            } else {
                box.setFill(Color.PURPLE);
                historyLabel.setVisible(false);
            }
        });

        return historyBox;
    }

    private Label createDescriptionLabel(String descriptionText) {
        Label descriptionLabel = new Label(descriptionText);
        descriptionLabel.setStyle("-fx-font-size: 20px;");
        return descriptionLabel;
    }
}
