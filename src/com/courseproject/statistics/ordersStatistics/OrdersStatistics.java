package com.courseproject.statistics.ordersStatistics;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OrdersStatistics {

    public static double calculatesTotalTurnoverOrders() {
        Path pathOrders = Paths.get("resourses", "data", "orders");
        double totalTurnoverOrders = 0;

        File folder = pathOrders.toFile();

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
                                        String cleanedWord = word.replaceAll(",", "");
                                        if (cleanedWord.matches("\\d+(\\.\\d+)?")) { //слово состоит только из цифр с десятичной точкой или просто цифр
                                            double number = Double.parseDouble(cleanedWord);
                                            totalTurnoverOrders += number;
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
        totalTurnoverOrders =Math.round(totalTurnoverOrders *100);
        totalTurnoverOrders =totalTurnoverOrders /100;
        return totalTurnoverOrders;
    }
}
