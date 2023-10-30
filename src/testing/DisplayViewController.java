package testing;

import javafx.fxml.*;
import javafx.scene.control.*;

import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.file.Files;  // Import Files to write to other files
import java.nio.file.Path;	 // Import Path to write to other files
import java.nio.file.StandardOpenOption;  // Import StandardOpenOption to write to other files
public class DisplayViewController {
	
	@FXML
	private Label username;
	@FXML
	private Label password;
	@FXML
	private Label passwordLength;
	
	public void setLogin(Login login)
	{
		
		username.setText("Name: " + login.getName());
		password.setText("Password: " + login.getPassword());
		passwordLength.setText("Length: " + login.getPasswordLength());
		
	}
	
	//Stores the new username and password information in unimportant.txt. Will be encrypted in the next variation to ensure that
	//malicious actors can't exploit EffortLoggerV2
	public void writeToFile(Login login) { //Writes the username and password from the textfields into a separate text file
		String content = login.getName() + "|" + login.getPassword() + "\n"; 
		Path filePath = Path.of("C:\\Users\\darkr\\OneDrive\\Documents\\MavenTester\\testing\\src\\main\\java\\testing\\unimportant.txt");
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
