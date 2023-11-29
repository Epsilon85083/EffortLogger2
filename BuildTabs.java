package EffortLoggerV2;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuildTabs {
    private TabPane tabPane;

    public BuildTabs(Stage primaryStage) {
        tabPane = new TabPane();
        
        Tab tab1 = new Tab("EffortLoggerV2");
        tab1.setStyle("-fx-font-size: 20px");

        VBox tab1Content = new EffortLoggerTab(primaryStage).getTab1Content(primaryStage);
        tab1.setContent(tab1Content);

        Tab tab2 = new Tab("Planning Poker");
        tab2.setStyle("-fx-font-size: 20px");
        
        VBox tab2Content = new PlanningPokerTab(primaryStage).getTab2Content(primaryStage);
        tab2.setContent(tab2Content);

        tabPane.getTabs().addAll(tab1, tab2);
        tabPane.setStyle("-fx-tab-min-width: 570px; -fx-tab-min-height: 50px;-fx-font-size: 20px");
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    public Scene getTabPaneScene() {
        return new Scene(tabPane, 1200, 1080);
    }
}
