package testing;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.Files;  // Import Files to write to other files
import java.nio.file.Path;	 // Import Path to write to other files
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;  // Import StandardOpenOption to write to other files
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
public class DisplayViewController {

	private Scene scene;
	private Stage stage;
	
	@FXML
	private Label hello;
	@FXML
	private Label username;
	@FXML
	private Label passwordLength;
	
	Security secure = new Security();
	
	public void setLogin(Login login)
	{
		
		hello.setText("Hello, welcome to Planning Poker ");
		username.setText("Username: " + login.getName());
		//passwordLength.setText("Length: " + login.getPasswordLength());
		
	}
	
	//Writes the username and encrypted password from the textfields into a separate text file
	public void writeToFile(Login login) 
		throws NoSuchAlgorithmException, InvalidKeySpecException 
	{
		//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlanningPoker.fxml"));
		String content = login.getName() + "|" + secure.generateStrongPasswordHash(login.getPassword()) + "\n"; 
		Path filePath = Paths.get("src\\main\\java\\testing\\unimportant.txt");
		System.out.println(filePath.toString());
		
		try { // Write to "unimportant.txt" otherwise throw an error
		      Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

		      System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}
