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
        Logger.actionLogs("The path to the folder was obtained: " + folderName);
        Logger.actionLogs("Start processing " + folderName);

        Path folderPath = Paths.get("E:\\TeachMeSkills_Final_Assignment\\resourses\\" + folderName);

        if (!Files.exists(folderPath)) {
            Logger.errorLogs("Folder not found: " + folderName);
            return;
        }

        try (DirectoryStream<Path> files = Files.newDirectoryStream(folderPath)) {
            for (Path file : files) {
                if (Files.isRegularFile(file)) {
                    Logger.actionLogs("Processing file: " + file.getFileName());
                    String resultMessage = processFile(file);
                    Logger.actionLogs("Result: " + resultMessage + " | File: " + file.getFileName());
                }
            }
        }

        Logger.actionLogs("Finished processing folder: " + folderName);
    }

    private static String processFile(Path file) {
        try {
            if (file.getFileName().toString().endsWith(".txt")) {
                Thread.sleep(100);
                return "File passed validation";
            } else {
                return "File failed validation: Unsupported file type";
            }
        } catch (InterruptedException e) {
            Logger.errorLogs("Error during file processing: " + e.getMessage());
            return "File failed validation";
        }
    }
}
