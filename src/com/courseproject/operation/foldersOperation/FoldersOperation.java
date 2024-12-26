package com.courseproject.operation.foldersOperation;

import com.courseproject.exception.fileException.NullFolderException;
import com.courseproject.operation.documentsOparation.sorterChecks.SorterChecks;
import com.courseproject.operation.documentsOparation.sorterInvoices.SorterInvoices;
import com.courseproject.operation.documentsOparation.sorterOrders.SorterOrders;

import java.io.File;

public class FoldersOperation {

    public static void distributeFolders(String path) throws NullFolderException {

        File mainFolder = new File(path); // приводим главный путь к главной папке

        File[] subfolders = mainFolder.listFiles(); // создаем массив подпапок в главной папке
        if (subfolders == null) {
            throw new NullFolderException("SubFolders are equal null.", 909);
        }

        for (File folder : subfolders) {
            String folderName = folder.getName().toLowerCase();

            if (folderName.contains("orders")) {
                SorterOrders.sortOrders();
            }
            if(folderName.contains("invoices")){
                SorterInvoices.sortInvoices();
            }
            if(folderName.contains("checks")){
                SorterChecks.sortChecks();
            }
        }
    }
}
