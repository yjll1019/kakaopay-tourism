package com.kakaopay.tourism.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;

public class DataFileReaderTest {
    private String fileDirectory = "./src/test/resources/";

    @Test
    void read_csv_file() throws IOException {
        String filePath = String.format("%s%s", fileDirectory, "csvTestFile.csv");
        File csvFile = new File(filePath);

        MultipartFile multipartFile = new MockMultipartFile("csvTestFile.csv",
                new FileInputStream(csvFile));

        List<String[]> actual = DataFileReader.readFile(multipartFile);
        assertThat(actual.size()).isEqualTo(3);
    }
}