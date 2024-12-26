package com.courseproject.operation.documentsOparation.sorterInvoices;

import com.courseproject.exception.fileException.NullFolderException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SorterInvoices {

    public static void sortInvoices() throws NullFolderException {
        Path pathInvoices = Paths.get("resourses", "data", "invoices");

        File folder = pathInvoices.toFile();

        File invalidFolder = Paths.get("resourses", "InvalidDocument").toFile();

        File[] folderInvoices = folder.listFiles();
        if (folderInvoices == null) {
            throw new NullFolderException("Folder invoices are equal null.", 920);
        }

        for (File file : folderInvoices) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.contains("2024") && (fileName.endsWith("txt"))
                        && (fileName.toLowerCase().contains("invoice"))){
                } else {
                    File invalidFile = new File(invalidFolder, fileName); // создает файл с этим же именем но в папке invalidFolder
                    file.renameTo(invalidFile); // перемещает файл в новую папку с новым путем
                }
            }
        }
    }
}
