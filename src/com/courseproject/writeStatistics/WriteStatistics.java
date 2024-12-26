package com.courseproject.writeStatistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteStatistics {

    public static void writeStatisticsToFile(double totalTurnoverOrders, double totalTurnoverChecks,
                                             double totalTurnoverInvoices) {
        Path finalStatisticsPath = Paths.get("resourses", "Statistics", "FinalStatistics");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(finalStatisticsPath), true))) {// true - добавление в файл
            writer.write("---------------------Финальная статистика---------------");
            writer.newLine();
            writer.write("Total Turnover by Orders: " + totalTurnoverOrders);
            writer.newLine(); // Новая строка
            writer.write("Total Turnover by Checks: " + totalTurnoverChecks);
            writer.newLine(); // Новая
            writer.write("Total Turnover by Invoices: " + totalTurnoverInvoices);
            //writer.newLine(); // Новая строка
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
