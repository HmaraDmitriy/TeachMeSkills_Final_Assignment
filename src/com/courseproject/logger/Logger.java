package com.courseproject.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
        private static final String ACTION_LOG_PATH = "D:\\Java\\TeachMeSkills_Final_Assignment\\resourses\\loggers\\ActionLogs.txt";
        private static final String ERROR_LOG_PATH = "D:\\Java\\TeachMeSkills_Final_Assignment\\resourses\\loggers\\ErrorsLogs.txt";

        public static void actionLogs(String message) {
            log(ACTION_LOG_PATH, message);
        }

        public static void errorLogs(String message) {
            log(ERROR_LOG_PATH, message);
        }

        private static void log(String path, String message) {
            try {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String logMessage = timestamp + " - " + message + System.lineSeparator();
                Files.write(Paths.get(path), logMessage.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println("Failed to write log: " + e.getMessage());
            }
        }
    }

