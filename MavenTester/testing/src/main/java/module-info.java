module testing {
    requires javafx.fxml;
    requires transitive javafx.controls;
    opens testing to javafx.graphics, javafx.fxml;
    exports testing;
}