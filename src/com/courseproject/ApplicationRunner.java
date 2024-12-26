package com.courseproject;

import com.courseproject.exception.fileException.NullFolderException;
import com.courseproject.operation.foldersOperation.FoldersOperation;
import com.courseproject.statistics.checksStatistics.ChecksStatistics;
import com.courseproject.statistics.invoicesStatistics.InvoicesStatistics;
import com.courseproject.statistics.ordersStatistics.OrdersStatistics;
import com.courseproject.writeStatistics.WriteStatistics;

import java.util.Scanner;

public class ApplicationRunner {
    public static void main(String[] args){
        // у меня путь к папке Data = /Users/admin/IdeaProjects/TeachMeSkills_Final_Assignment/resourses/data
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the file: ");
        String path = scanner.nextLine();
        scanner.close();
        try {
            FoldersOperation.distributeFolders(path);

            double totalTurnoverOrders = OrdersStatistics.calculatesTotalTurnoverOrders();
            double totalTurnoverChecks =  ChecksStatistics.calculatesTotalTurnoverChecks();
            double totalTurnoverInvoices = InvoicesStatistics.calculatesTotalTurnoverInvoices();
            WriteStatistics.writeStatisticsToFile(totalTurnoverOrders, totalTurnoverChecks, totalTurnoverInvoices);
        } catch (NullFolderException e) {
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
