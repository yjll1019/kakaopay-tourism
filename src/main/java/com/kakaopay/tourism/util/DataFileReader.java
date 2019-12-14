package com.kakaopay.tourism.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.kakaopay.tourism.util.exception.DataFileReadFailException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataFileReader {
    private static final Logger logger = LoggerFactory.getLogger(DataFileReader.class);

    private static final String BYTE_ORDER_MAKR = "\uFEFF";

    public static List<String[]> readFile(File csvFile) {

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(
                new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
            List<String[]> rows = new ArrayList<>();
            String[] nextLine;

            while ((nextLine = csvReader.readNext()) != null) {
                nextLine[0] = nextLine[0].replaceAll(BYTE_ORDER_MAKR, "");
                rows.add(nextLine);
            }

            logger.info("success read : {}", csvFile.getName());
            return rows;
        } catch (IOException | CsvException e) {
            logger.error("Exception : {}", e.getMessage());
            throw new DataFileReadFailException(e);
        }
    }
}
