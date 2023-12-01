//Author: Joseph Felix
package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataUtil {

    private static final String BACKUP_DIRECTORY = "backup/"; 
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    
    //method for storing log data into file
    public static void saveData(String data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method for loading data that is stored in file
    public static String[] loadData(String filePath) {
//        try {
//            return new String(Files.readAllBytes(Paths.get(FILE_PATH)));
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
    	
    	List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return lines.toArray(new String[0]);
    }
    
    
    //method that is called when new log is made to craete backup
    public static void createBackup(String originalFilePath) throws IOException {
        File originalFile = new File(originalFilePath);
        if (!originalFile.exists()) {
            System.out.println("Original file not found!");
            return;
        }
        
        // Create backup directory if it doesn't exist
        File backupDir = new File(BACKUP_DIRECTORY);
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        String backupFileName = formatter.format(new Date()) + "_" + originalFile.getName();
        File backupFile = new File(BACKUP_DIRECTORY + backupFileName);

        Files.copy(originalFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Backup created: " + backupFile.getAbsolutePath());
    }
}
