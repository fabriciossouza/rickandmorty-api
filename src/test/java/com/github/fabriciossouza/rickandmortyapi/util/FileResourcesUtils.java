package com.github.fabriciossouza.rickandmortyapi.util;

import lombok.SneakyThrows;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileResourcesUtils {


    @SneakyThrows

    public static String getFileStringFromResource(String fileName) {

        var inputStream = FileResourcesUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStreamToString(inputStream);
        }
    }

    private static String inputStreamToString(InputStream is) {

        StringBuilder builder = new StringBuilder();

        try (InputStreamReader streamReader = new InputStreamReader(is, UTF_8); BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}