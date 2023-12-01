//Author: Joseph Felix
package application;

public class DefectLog {
	public String project;
	public String defectName;
	public String defectDescription;
	
	
	//contructor
	public DefectLog(String project, String defectName, String defectDescription) {
		this.project = project;
		this.defectName = defectName;
		this.defectDescription = defectDescription;
		
	}
	
	//constructor from parsed log
	public DefectLog(String log) {
		parseDefectData(log);
	}
	
	
	//parses values from string and sets them respectively
	public void parseDefectData(String formattedData) {
	    // Split the string based on semicolon
	    String[] parts = formattedData.split(";");

	    // Check if the string is correctly formatted
	    if(parts.length != 4) {
	        throw new IllegalArgumentException("Formatted data is not in the expected format.");
	    }

	    // Assign each part to its respective variable
	    project = parts[0];
	    defectName = parts[1];
	    defectDescription = parts[2];
	    
	}
	
	//creates log
	public String createLog() {
		return project + ";" + defectName + ";" + defectDescription+ ".";
	}

}
