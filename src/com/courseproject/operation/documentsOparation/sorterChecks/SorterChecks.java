package com.courseproject.operation.documentsOparation.sorterChecks;

import com.courseproject.exception.fileException.NullFolderException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SorterChecks {

    public static void sortChecks() throws NullFolderException {

        Path pathChecks = Paths.get("resourses", "data", "checks");
        File folder = pathChecks.toFile();

        File invalidFolder = Paths.get("resourses", "InvalidDocument").toFile();

        File[] folderChecks = folder.listFiles();
        if (folderChecks == null) {
            throw new NullFolderException("Folder checks are equal null.", 930);
        }

        for (File file : folderChecks) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.startsWith("2024") && (fileName.endsWith("txt"))
                        && (fileName.toLowerCase().contains("bill"))) {
                } else {
                    File invalidFile = new File(invalidFolder, fileName); // создает файл InvalidFile с этим же именем но в папке invalidFolder
                    file.renameTo(invalidFile); // перемещает файл в новую папку с новым путем
                }
            }
        }
    }
}
