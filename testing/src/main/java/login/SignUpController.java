package login;

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
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
	Security secure = new Security();

	
	public void submitLogin(ActionEvent event) 
		throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/EffortLoggerConsole.fxml")); // This is where David's work merges into Adam's work and bugs could be coming from the application package
		//if the passwords are the same, username meets requirements, and password meets requirements EffortLogger will store their credentials
		if(checkPasswordSimilarity(passwordField1, passwordField2) && secure.checkRequirements(usernameField) && secure.checkRequirements(passwordField1)) {
			login.setName(usernameField.getText());
			login.setPassword(passwordField2.getText());
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(fxmlLoader.load(), 900, 600);
		stage.setTitle("Login Info");
		
		writeToFile(login);
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
		if(pass1.getText().equals(pass2.getText())) 
			return true;
		else
			return false;
	}
	
	//Writes the username and encrypted password from the textfields into a separate text file
	public void writeToFile(Login login) 
		throws NoSuchAlgorithmException, InvalidKeySpecException 
	{
		//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlanningPoker.fxml"));
		String content = login.getName() + "|" + secure.generateStrongPasswordHash(login.getPassword()) + "\n"; 
		Path filePath = Paths.get("src\\main\\java\\login\\unimportant.txt");
		System.out.println(filePath.toString());
			
		try { // Write to "unimportant.txt" otherwise throw an error
		      Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		}
}
