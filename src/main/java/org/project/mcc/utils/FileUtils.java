package org.project.mcc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
public final class FileUtils {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private FileUtils() {}

    public static <T> void  exportObjectAsJson(T object, String path) throws IOException {
        log.info("Writing content into file {}...", path);
        Path filePath = Path.of(path);
        Files.deleteIfExists(filePath);
        Files.createDirectory(filePath);
        try(FileWriter fw = new FileWriter(path, false)) {
            GSON.toJson(object, fw);
            log.info("Content exported...");
        }
    }

}
