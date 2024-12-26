package com.courseproject.operation.documentsOparation.sorterOrders;

import com.courseproject.exception.fileException.NullFolderException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SorterOrders {
    public static void sortOrders() throws NullFolderException {
        Path pathOrders = Paths.get("resourses", "data", "orders");
        File folder = pathOrders.toFile();

        File invalidFolder = Paths.get("resourses", "InvalidDocument").toFile();

        File[] folderOrders = folder.listFiles();
        if (folderOrders == null) {
            throw new NullFolderException("Folder orders are equal null.", 910);
        }

        for (File file : folderOrders) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.startsWith("2024") && (fileName.toLowerCase().contains ("order"))
                        && (fileName.endsWith("txt"))) {
                } else {
                    File invalidFile = new File(invalidFolder, fileName); // создает файл с этим же именем но в папке invalidFolder
                    file.renameTo(invalidFile); // перемещает файл в новую папку с новым путем
                }
            }
        }
    }
}



