package EffortLoggerV2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class DiscussionPage {
    private Scene discussionPageScene;
    private Stage primaryStage;

    public DiscussionPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        VBox discussionContent = new VBox();
        discussionContent.getChildren().addAll(
                createScrollableDiscussionSections(),
                createBackButtonBox()
        );
        discussionPageScene = new Scene(discussionContent, 1200, 1080);
    }

    public Scene getDiscussionPageScene() {
        return discussionPageScene;
    }

    private ScrollPane createScrollableDiscussionSections() {
        VBox discussionSections = new VBox();
        discussionSections.setAlignment(Pos.TOP_LEFT);

        discussionSections.getChildren().addAll(
                createDiscussionSection("Subteam A"),
                createDiscussionSection("Subteam B"),
                createDiscussionSection("Subteam C")
        );

        ScrollPane scrollPane = new ScrollPane(discussionSections);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        return scrollPane;
    }

    private VBox createDiscussionSection(String sectionTitle) {
        VBox sectionBox = new VBox();
        sectionBox.setAlignment(Pos.TOP_LEFT);

        Text titleText = new Text(sectionTitle);
        titleText.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        Text nameText1 = createBoldText("John", 20);
        Text commentText1 = new Text("I strongly believe that our recent updates will have a positive impact on the project. Let's continue the great work!");
        commentText1.setStyle("-fx-font-size: 20px;");

        Text nameText2 = createBoldText("Alice", 20);
        Text commentText2 = new Text("I have some concerns about the project timeline. We need to ensure that we allocate enough time for testing and refinement.");
        commentText2.setStyle("-fx-font-size: 20px;");

        Text nameText3 = createBoldText("Bob", 20);
        Text commentText3 = new Text("The progress on the UI is impressive! I appreciate everyone's hard work in making it user-friendly.");
        commentText3.setStyle("-fx-font-size: 20px;");

        Map<String, Color> nameColorMap = new HashMap<>();
        nameColorMap.put("John", Color.rgb(255, 0, 0, 0.7)); //Red
        nameColorMap.put("Alice", Color.rgb(0, 255, 0, 0.7)); //Green
        nameColorMap.put("Bob", Color.rgb(0, 0, 255, 0.7)); //Blue

        StackPane entryBox1 = createEntryBox(nameText1, nameColorMap.getOrDefault("John", Color.BLACK));
        StackPane entryBox2 = createEntryBox(nameText2, nameColorMap.getOrDefault("Alice", Color.BLACK));
        StackPane entryBox3 = createEntryBox(nameText3, nameColorMap.getOrDefault("Bob", Color.BLACK));

        HBox discussionEntry1 = new HBox(entryBox1, commentText1);
        HBox discussionEntry2 = new HBox(entryBox2, commentText2);
        HBox discussionEntry3 = new HBox(entryBox3, commentText3);

        sectionBox.getChildren().addAll(titleText, discussionEntry1, discussionEntry2, discussionEntry3);
        sectionBox.setSpacing(20);

        return sectionBox;
    }

    private StackPane createEntryBox(Text nameText, Color boxColor) {
        Rectangle coloredBox = new Rectangle(70, 70);
        coloredBox.setFill(boxColor);

        StackPane entryBox = new StackPane(coloredBox, nameText);
        entryBox.setAlignment(Pos.CENTER_LEFT);
        entryBox.setMargin(nameText, new javafx.geometry.Insets(0, 10, 0, 10));

        return entryBox;
    }

    private HBox createBackButtonBox() {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> returnToTabPane());
        HBox backButtonBox = new HBox(backButton);
        backButtonBox.setAlignment(Pos.BOTTOM_LEFT);
        return backButtonBox;
    }

    private void returnToTabPane() {
        primaryStage.setScene(new BuildTabs(primaryStage).getTabPaneScene());
    }

    private Text createBoldText(String text, int fontSize) {
        Text boldText = new Text(text);
        boldText.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
        return boldText;
    }
}
