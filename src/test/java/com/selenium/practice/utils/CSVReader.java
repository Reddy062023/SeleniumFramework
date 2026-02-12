package com.selenium.practice.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    // Reads a CSV file from src/test/resources/testdata and returns list of String arrays
    public static List<String[]> readCSV(String fileName) {
        List<String[]> records = new ArrayList<>();

        try (InputStream input = CSVReader.class.getClassLoader().getResourceAsStream("testdata/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                // Skip header
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                // Split by comma
                String[] data = line.split(",");
                records.add(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return records;
    }
}
