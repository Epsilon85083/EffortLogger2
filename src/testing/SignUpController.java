package testing;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SignUpController {
	private Scene scene;
	private Stage stage;
	private Login login = new Login();
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField1;
	@FXML
	private PasswordField passwordField2;
	@FXML
	private Label incorrectPassword;
	

	
	public void submitLogin(ActionEvent event) throws IOException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DisplayPage.fxml"));
		//if the passwords are the same, username meets requirements, and password meets requirements EffortLogger will store their credentials
		if(checkPasswordSimilarity(passwordField1, passwordField2) && checkRequirements(usernameField) && checkRequirements(passwordField1)) {
			login.setName(usernameField.getText());
			login.setPassword(passwordField2.getText());
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Login Info");
		
		DisplayViewController control = fxmlLoader.getController();
		control.setLogin(login);
		control.writeToFile(login);
		stage.setScene(scene);
		stage.show();
		}
		else {
			usernameField.setText("");
			passwordField1.setText("");
			passwordField2.setText("");
			incorrectPassword.setText("Login credentials did not meet the requirements. Please resubmit.");
		}
	}
	
	public boolean checkPasswordSimilarity(PasswordField pass1, PasswordField pass2) { //Makes certain the user inputs the correct password
		if(pass1.getText().equals(pass2.getText())) { 
			return true;
		} 
		else {
			return false;
		}
	}
	
	public boolean checkRequirements(TextField username) {
		if(username.getText().length() < 3 || 33 < username.getText().length()) { //ensure username is within secure character length and lexical rules
			return false;
		}
		// Define a regular expression pattern that matches the allowed characters
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]+$");

        // Create a Matcher object and check if the password matches the pattern
        Matcher matcher = pattern.matcher(username.getText());
        
        //If true it is a valid password. If false it is invalid.
        return matcher.matches(); 
	}
	
	public boolean checkRequirements(PasswordField password) {
		if(password.getText().length() < 3 || 33 < password.getText().length()) { //ensure password is within secure character length and lexical rules
			return false;
		}
		// Define a regular expression pattern that matches the allowed characters
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._-]+$");

        // Create a Matcher object and check if the password matches the pattern
        Matcher matcher = pattern.matcher(password.getText());
        
        //If true it is a valid password. If false it is invalid.
        return matcher.matches(); 
	}

}
