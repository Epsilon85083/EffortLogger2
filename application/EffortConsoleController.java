//Author: Joseph Felix
package application;

import javafx.fxml.FXML;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EffortConsoleController {
	public Button startButton;
	public Button stopButton;
	public Button backButton;
	public Button readLogsButton;
	public Label clockLabel;
	public TextField stringField;
	boolean isClockRunning;
	private Stage stage;
	public TextArea logTextArea;
	private String key = "reggol456troffe6";
	
	//ComboBoxes
	public ComboBox<String> projectComboBox;
	public ComboBox<String> lifeCycleStepComboBox;
	public ComboBox<String> effortCategoryComboBox;
	public ComboBox<String> categoryDetailComboBox;
	
	//time tracking variables
	private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	private Scene mainScene;
    
	//constructor that stores reference to mainline scene
    public EffortConsoleController(Stage stage, Scene mainScene ) {
        this.stage = stage;
        this.mainScene = mainScene;
    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        // Switch back to the main scene
        stage.setScene(mainScene);
    }
	
    //initialize ComboBoxes with starting values
	@FXML
	public void initialize() {
		isClockRunning = false;
		projectComboBox.getItems().removeAll(projectComboBox.getItems());
		projectComboBox.getItems().addAll("Business Project", "Development Project");
		projectComboBox.getSelectionModel().select("Business Project");
	    
		lifeCycleStepComboBox.getItems().removeAll(lifeCycleStepComboBox.getItems());
		lifeCycleStepComboBox.getItems().addAll("Planning", "Information Gathering", "Information Understanding");
		lifeCycleStepComboBox.getSelectionModel().select("Planning");
	    
		effortCategoryComboBox.getItems().removeAll(effortCategoryComboBox.getItems());
		effortCategoryComboBox.getItems().addAll("Plans", "Deliverables", "Interruptions");
		effortCategoryComboBox.getSelectionModel().select("Plans");
	    
		categoryDetailComboBox.getItems().removeAll(categoryDetailComboBox.getItems());
		categoryDetailComboBox.getItems().addAll("Project Plan", "Risk Management Plan", "Comceptual Design Plan");
		categoryDetailComboBox.getSelectionModel().select("Project Plan");
	}
	
	//Handles start activity button that starts timer
	public void handleStartButtonClick() {
		if(!isClockRunning) {
			clockLabel.setText("Clock Started");
			clockLabel.setTextFill(Color.rgb(0, 255, 0));
			startTime = LocalDateTime.now();
			isClockRunning = true;
		}
	}
	
	//handles stop time button that called log handler
	public void handleStopButtonClick() {
		if(isClockRunning) {
			clockLabel.setText("Clock Stopped");
			clockLabel.setTextFill(Color.rgb(255, 0, 0));
			stopTime = LocalDateTime.now();
			handleEffortLog();
			isClockRunning = false;
		}
	}
	
	//creates log and stores it in a file
	private void handleEffortLog() {
		String timeLog = "Logged from " + formatter.format(startTime) + " to " + formatter.format(stopTime);
		//retrieves values from ComboBoxes
		String project = projectComboBox.getValue();
		String lifeCycleStep = lifeCycleStepComboBox.getValue();
		String effortCategory = effortCategoryComboBox.getValue();
		String categoryDetail = categoryDetailComboBox.getValue();
		
		//creates EffortLog from values
		EffortLog effortLog = new EffortLog(project, timeLog, lifeCycleStep, effortCategory, categoryDetail);

		//creates string representation of log
		String log = effortLog.createLog();

		//saves in file
		try {
			//encrypts log before storing
			DataUtil.saveData(EncryptionUtil.encrypt(key, log));
			DataUtil.createBackup("effortLoggerData.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//reads encrypted logs and decrypts them so that they are viewable
	public void handleReadLogs() {
		String[] effortLogs = DataUtil.loadData();
		
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

    