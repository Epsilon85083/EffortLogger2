package application;

public class EffortLog {
	public String project;
	public String timeLog;
	public String lifeCycleStep;
	public String effortCategory;
	public String plan;
	
	
	//contructor
	public EffortLog(String project, String timeLog, String lifeCycleStep, String effortCategory, String plan) {
		this.project = project;
		this.timeLog = timeLog;
		this.lifeCycleStep = lifeCycleStep;
		this.effortCategory = effortCategory;
		this.plan = plan;
	}
	
	//constructor from parsed log
	public EffortLog(String log) {
		parseEffortData(log);
	}
	
	//Getters and Setters
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getLifeCycleStep() {
		return lifeCycleStep;
	}
	public void setLifeCycleStep(String lifeCycleStep) {
		this.lifeCycleStep = lifeCycleStep;
	}
	public String getEffortCategory() {
		return effortCategory;
	}
	public void setEffortCategory(String effortCategory) {
		this.effortCategory = effortCategory;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	//parses values from string and sets them respectively
	public void parseEffortData(String formattedData) {
	    // Split the string based on semicolon
	    String[] parts = formattedData.split(";");

	    // Check if the string is correctly formatted
	    if(parts.length != 4) {
	        throw new IllegalArgumentException("Formatted data is not in the expected format.");
	    }

	    // Assign each part to its respective variable
	    timeLog = parts[0];
	    lifeCycleStep = parts[1];
	    effortCategory = parts[2];
	    plan = parts[3];
	}
	
	//creates log
	public String createLog() {
		return timeLog + ";" + lifeCycleStep + ";" + effortCategory + ";" + plan;
	}

}
