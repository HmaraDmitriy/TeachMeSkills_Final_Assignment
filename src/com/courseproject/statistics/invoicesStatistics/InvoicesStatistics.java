package com.courseproject.statistics.invoicesStatistics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InvoicesStatistics {
    public static double calculatesTotalTurnoverInvoices() {
        double totalTurnoverInvoices = 0;
        Path pathInvoices = Paths.get("resourses", "data", "invoices");

        File folder = pathInvoices.toFile();

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {

                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            String line;

                            while ((line = reader.readLine()) != null) {
                                if (line.toLowerCase().contains("total")) {
                                    String[] words = line.split(" ");
                                    for (String word : words) {
                                        String cleanedWord = word.replaceAll("\\$", "");
                                        if (cleanedWord.matches("\\d+(\\.\\d+)?")) { //слово состоит только из цифр с десятичной точкой или просто цифр
                                            double number = Double.parseDouble(cleanedWord);
                                            totalTurnoverInvoices += number;
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
        }
        totalTurnoverInvoices = Math.round(totalTurnoverInvoices * 100);
        totalTurnoverInvoices = totalTurnoverInvoices / 100;
        return totalTurnoverInvoices;
    }
}
