package com.courseproject;

import com.courseproject.exception.fileException.NullFolderException;
import com.courseproject.logger.LoggerApplication;
import com.courseproject.operation.foldersOperation.FoldersOperation;
import com.courseproject.statistics.checksStatistics.ChecksStatistics;
import com.courseproject.statistics.invoicesStatistics.InvoicesStatistics;
import com.courseproject.statistics.ordersStatistics.OrdersStatistics;
import com.courseproject.twoFA.twoFARunner;
import com.courseproject.writeStatistics.WriteStatistics;
import com.s3.java.PropertyS3;

import java.util.Scanner;

public class ApplicationRunner {
    public static void main(String[] args) {
        try {
            System.out.println("Starting Two-Factor Authentication...");
            boolean isAuthenticated = twoFARunner.launchTFA();

            if (!isAuthenticated) {
                System.out.println("Authentication failed.");
                return;
            }
        } catch (Exception e) {
            System.err.println("Authentication error: " + e.getMessage());
            return;
        }

        try {
            PropertyS3.main(null);
            System.out.println("AWS credentials saved successfully.");
        } catch (Exception e) {
            System.err.println("Error generating AWS credentials: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the folder: ");
        String path = scanner.nextLine();
        scanner.close();

        try {

            System.out.println("Distributing folders...");
            FoldersOperation.distributeFolders(path);
            System.out.println("Folders distributed successfully.");

            System.out.println("Calculating statistics...");
            double totalTurnoverOrders = OrdersStatistics.calculatesTotalTurnoverOrders();
            double totalTurnoverChecks = ChecksStatistics.calculatesTotalTurnoverChecks();
            double totalTurnoverInvoices = InvoicesStatistics.calculatesTotalTurnoverInvoices();
            WriteStatistics.writeStatisticsToFile(totalTurnoverOrders, totalTurnoverChecks, totalTurnoverInvoices);
            System.out.println("Statistics calculated and written successfully.");

        } catch (NullFolderException e) {
            System.out.println("Folder operation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        System.out.println("Processing folders and generating logs...");
        LoggerApplication.processFolders("invoices", "orders", "checks");
        System.out.println("Folder processing and logging completed.");
    }
}
