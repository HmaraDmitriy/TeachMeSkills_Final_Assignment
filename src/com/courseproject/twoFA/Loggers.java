package com.courseproject.twoFA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Loggers {

    private static final String LOG_FILE_PATH = "logs.txt";

    public static void actionLogs(String message) {
        log("ACTION: " + message);
    }

    public static void errorLogs(String message) {
        log("ERROR: " + message);
    }

    private static void log(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().toString();
            writer.write(timestamp + " - " + message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
