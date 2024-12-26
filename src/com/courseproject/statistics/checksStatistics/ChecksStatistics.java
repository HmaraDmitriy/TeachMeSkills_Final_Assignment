package com.courseproject.statistics.checksStatistics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChecksStatistics {

    public static double calculatesTotalTurnoverChecks() {
        double totalTurnoverChecks = 0;

        Path pathChecks = Paths.get("resourses", "data", "checks");
        File folder = pathChecks.toFile();

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files)
                    if (file.isFile()) {

                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            String line;

                            while ((line = reader.readLine()) != null) {
                                if (line.toLowerCase().contains("total")) {
                                    String[] words = line.split(" ");
                                    for (String word : words) {
                                        String cleanedWord = word.replaceAll(",", ".");
                                        if (cleanedWord.matches("\\d+(\\.\\d+)?")) { //слово состоит только из цифр с десятичной запятой или просто цифр
                                            double number = Double.parseDouble(cleanedWord);
                                            totalTurnoverChecks += number;
                                        }
                                    }
                                }
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
            }
        }
        totalTurnoverChecks = Math.round(totalTurnoverChecks * 100);
        totalTurnoverChecks = totalTurnoverChecks / 100;
        return totalTurnoverChecks;
    }
}








