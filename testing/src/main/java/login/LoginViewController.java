package login;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.List;

public class LoginViewController {
	private Scene scene;
	private Stage stage;
	
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField usernameField;
	@FXML 
	private Label passwordErrorLabel;
	@FXML 
	private Label usernameErrorLabel;
	@FXML
	private Label invalidLoginLabel;
	
	//Someone that already has a valid log in is able to sign in to their account. Ensures that there is security when logging in.
	public void checkLogin(ActionEvent event) 
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		Security secure = new Security();
		
		//Re-prompt user if username field doesn't adhere to requirements
		if(!secure.checkRequirements(usernameField)) {
			resetFields();
			usernameErrorLabel.setText("Username outside of acceptable input range");
			return;
		}
		//Re-prompt user if password field doesn't adhere to requirements
		if(!secure.checkRequirements(passwordField)) {
			resetFields();
			passwordErrorLabel.setText("Password outside of acceptable input range");
			return;
		}
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EffortLoggerConsole.fxml")); // This is where David's work merges into Adam's work and bugs could be coming from the application package
		Path filePath = Path.of("src/main/java/login/unimportant.txt");
		String filePathString = "src/main/java/login/unimportant.txt"; 
		FileReader fileReader = new FileReader(filePathString);
		
		List<String> allSavedLogins = Files.readAllLines(filePath);
		
		String read = "";
		String userCheck = "";
		String passwordCheck = "";
		boolean validLogin = false;
		
		//check to see if any of the saved login info in unimportant.txt matches the user input
		for(Iterator<String> iter = allSavedLogins.iterator(); iter.hasNext(); ) {
			read = iter.next();
			userCheck = read.substring(0, read.indexOf("|"));
			passwordCheck = read.substring(read.indexOf("|") + 1, read.length());
			if(userCheck.equals(usernameField.getText()) && secure.validatePassword(passwordField.getText(), passwordCheck)) {
				validLogin = true;
				break;
			}
		}
		
		if(validLogin) {
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(fxmlLoader.load(), 900, 600);
			stage.setTitle("Planning Poker");

			stage.setScene(scene);
			stage.show();
		} else {
			resetFields();
			invalidLoginLabel.setText("Please input valid login credentials");
		}
	}
	
	public void resetFields() {
		usernameField.setText("");
		usernameErrorLabel.setText("");
		passwordField.setText("");
		passwordErrorLabel.setText("");
		invalidLoginLabel.setText("");
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