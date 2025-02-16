package org.project.mcc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.*;


@Slf4j
public final class FileUtils {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private FileUtils() {}

    public static <T> void  exportObjectAsJson(T object, String path) throws IOException {
        log.info("Writing content into file {}...", path);
        try(FileWriter fw = new FileWriter(path, true)) {
            GSON.toJson(object, fw);
            log.info("Content exported...");
        }
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.delete();
    }


}
