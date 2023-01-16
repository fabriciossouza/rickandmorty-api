package com.github.fabriciossouza.rickandmortyapi.util;

import com.github.fabriciossouza.rickandmortyapi.domain.service.character.CharacterServiceTest;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileResourcesUtils {


    @SneakyThrows

    public static String getFileStringFromResource(Class clazz, String fileName) {

        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(fileName);
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