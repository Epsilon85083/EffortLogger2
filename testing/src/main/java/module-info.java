module login {
    requires javafx.fxml;
    requires transitive javafx.controls;
    exports login;
    opens login to javafx.graphics, javafx.fxml;
    exports application;
    opens application to javafx.graphics, javafx.fxml;
}