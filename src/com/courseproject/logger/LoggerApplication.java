package com.courseproject.logger;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoggerApplication {

    public static void processFolders(String... folderNames) {
        for (String folderName : folderNames) {
            try {
                processFolder(folderName);
            } catch (IOException e) {
                Logger.errorLogs("Processing error in folder " + folderName + ": " + e.getMessage());
            }
        }
        Logger.actionLogs("End of work \n----------------------------------------\n");
    }

    private static void processFolder(String folderName) throws IOException {
        Logger.actionLogs("Starting processing folder: " + folderName);

        Path folderPath = Paths.get("E:\\TeachMeSkills_Final_Assignment\\resourses\\" + folderName);

        if (!Files.exists(folderPath)) {
            Logger.errorLogs("Invalid files not found: " + folderName);
            return;
        }

        try (DirectoryStream<Path> files = Files.newDirectoryStream(folderPath)) {
            for (Path file : files) {
                if (Files.isRegularFile(file)) {
                    Logger.actionLogs("File taken for processing: " + file.getFileName());
                    String resultMessage = processFile(file);
                    Logger.actionLogs("File checked: " + file.getFileName() + " | Result: " + resultMessage);
                }
            }
        }

        Logger.actionLogs("Finished processing folder: " + folderName);
    }

    private static String processFile(Path file) {

        try {
            Logger.actionLogs("Checking file: " + file.getFileName());

            if (file.getFileName().toString().endsWith(".txt")) {
                Thread.sleep(100);
                return "Validation successful";
            } else {
                return "Validation failed: Unsupported file type";
            }
        } catch (InterruptedException e) {
            Logger.errorLogs("Error during file processing: " + e.getMessage());
            return "Validation failed: Processing interrupted";
        }
    }
}
