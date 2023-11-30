package login;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class PlanningPokerController {
	private Scene scene;
	private Stage stage;
	
	@FXML
	private Label welcome;
	@FXML
	private Label username;
	
	public void bootup(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlanningPoker.fxml"));
		//if the passwords are the same, username meets requirements, and password meets requirements EffortLogger will store their credentials

		welcome.setText("Hello, welcome to Planning Poker");
		//username.setText(login.getUsername);
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Planning Poker");
	

		stage.setScene(scene);
		stage.show();
	}
}
