package testing;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoginViewController {
	private Scene scene;
	private Stage stage;
	
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField usernameField;
	
	public void checkLogin(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
		Path filePath = Path.of("C:\\Users\\darkr\\OneDrive\\Documents\\MavenTester\\testing\\src\\main\\java\\testing\\unimportant.txt");
		String read = Files.readAllLines(filePath).get(0);
		String userCheck = read.substring(0, read.indexOf("|"));
		String passwordCheck = read.substring(read.indexOf("|") + 1, read.length());
		if(userCheck.equals(usernameField.getText()) && passwordCheck.equals(passwordField.getText())) { //username and password match entry in the credential log
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Login Info");

		stage.setScene(scene);
		stage.show();
		}
	}
	
	public void signUpPage(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignUpPage.fxml"));
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Sign Up Page");

		stage.setScene(scene);
		stage.show();
	}

}