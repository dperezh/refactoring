package org.exercise.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

public class FileUtil {

    public static FileHandler createFileIfNotExists(String fileUrl, String filename) throws IOException {
        File logFile = new File(fileUrl + "/" + filename + ".txt");
        if (!logFile.exists()) {
            logFile.createNewFile();
        }
        return new FileHandler(fileUrl + "/" + filename + ".txt");
    }
}
