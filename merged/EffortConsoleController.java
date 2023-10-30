package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import application.DataUtil;
import application.EffortLog;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EffortConsoleController {
	public Button startButton;
	public Button stopButton;
	public Label clockLabel;
	public TextField stringField;
	boolean isClockRunning;
	
	//ComboBoxes
	public ComboBox<String> projectComboBox;
	public ComboBox<String> lifeCycleStepComboBox;
	public ComboBox<String> effortCategoryComboBox;
	public ComboBox<String> categoryDetailComboBox;
	
	//time tracking variables
	private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
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
	
	//Handles start time button
	public void handleStartButtonClick() {
		if(!isClockRunning) {
			clockLabel.setText("Clock Started");
			clockLabel.setTextFill(Color.rgb(0, 255, 0));
			startTime = LocalDateTime.now();
			isClockRunning = true;
		}
	}
	
	//handles stop time button
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
		DataUtil.saveData(log);
	}
	
    
   
}

    