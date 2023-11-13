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
import java.util.Date;

public class DataUtil {

    private static final String FILE_PATH = "effortLoggerData.txt";
    private static final String BACKUP_DIRECTORY = "backup/"; 
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

    public static void saveData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadData() {
        try {
            return new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
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
