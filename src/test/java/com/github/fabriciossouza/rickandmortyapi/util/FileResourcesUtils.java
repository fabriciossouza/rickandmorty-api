package com.github.fabriciossouza.rickandmortyapi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FileResourcesUtils {

    public static String getFileStringFromResource(String fileName) {

        InputStream inputStream = currentThread()
                .getContextClassLoader()
                .getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStreamToString(inputStream);
        }
    }

    private static String inputStreamToString(InputStream is) {

        StringBuilder builder = new StringBuilder();

        try (InputStreamReader streamReader =
                     new InputStreamReader(is, UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

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