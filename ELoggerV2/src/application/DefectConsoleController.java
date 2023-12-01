//Author: Joseph Felix
package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import application.DataUtil;
import application.EffortLog;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DefectConsoleController {
	public Button backButton;
	public Button readLogsButton;
	public Button createLogButton;
	public TextField defectNameTextField;
	private Stage stage;
	public TextArea defectDescriptionTextArea;
	public TextArea logTextArea;
	private String key = "reggol456troffe6";
	
	//ComboBoxes
	public ComboBox<String> projectComboBox;
	
	//time tracking variables
	private Scene mainScene;
    
	//constructor that stores reference to mainline scene
    public DefectConsoleController(Stage stage, Scene mainScene ) {
        this.stage = stage;
        this.mainScene = mainScene;
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) throws IOException {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EffortLoggerConsole.fxml"));
            // Pass the stage or main scene to the controller
            EffortConsoleController controller = new EffortConsoleController(stage, mainScene);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    //initialize ComboBoxes with starting values
	@FXML
	public void initialize() {
		projectComboBox.getItems().removeAll(projectComboBox.getItems());
		projectComboBox.getItems().addAll("Business Project", "Development Project");
		projectComboBox.getSelectionModel().select("Business Project");
	    

	}
	
	//creates log and stores it in a file
	public void handleDefectLog() {
		
		//retrieves values from ComboBoxes
		String project = projectComboBox.getValue();
		String defectName = defectNameTextField.getText();
		String defectDescription = defectDescriptionTextArea.getText();
		
		
		//creates EffortLog from values
		DefectLog defectLog = new DefectLog(project, defectName, defectDescription);

		//creates string representation of log
		String log = defectLog.createLog();

		//saves in file
		try {
			//encrypts log before storing
			DataUtil.saveData(EncryptionUtil.encrypt(key, log), "defectLoggerData.txt");
			System.out.println(log);
			DataUtil.createBackup("defectLoggerData.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//reads encrypted logs and decrypts them so that they are viewable
	public void handleReadLogs() {
		String[] effortLogs = DataUtil.loadData("defectLoggerData.txt");
		
		//decrypts each effort log from file
		for(int i = 0; i < effortLogs.length; i++) {
			effortLogs[i] = EncryptionUtil.decrypt(key, effortLogs[i]);
		}
		
//		for(int i = 0; i < effortLogs.length; i++) {
//			 System.out.println(effortLogs[i]);
//		}
		
		//loades them onto TextArea so they are viewable
		StringBuilder sb = new StringBuilder();
        for (String entry : effortLogs) {
            sb.append(entry).append("\n");
        }
        logTextArea.setText(sb.toString());
		
	}
	
    
   
}

    