package com.kakaopay.tourism.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kakaopay.tourism.util.exception.DataFileReadFailException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataFileReaderTest {
    private String fileDirectory = "./src/test/resources/";

    @Test
    void read_csv_file() {
        String filePath = String.format("%s%s", fileDirectory, "csvTestFile.csv");
        File csvFile = new File(filePath);

        List<String[]> expected = new ArrayList<>();
        expected.add(new String[]{"번호", "프로그램명", "테마별 분류", "서비스 지역", "프로그램 소개", "프로그램 상세 소개"});
        expected.add(new String[]{"1", "설악산", "생태체험", "설악산 탐방안내소, 신흥사, 권금성, 비룡폭포",
                "설악산 탐방안내소", "설악산은 왜 설악산이고"});

        List<String[]> actual = DataFileReader.readFile(csvFile);
        assertThat(Arrays.equals(expected.get(0), actual.get(0))).isTrue();
        assertThat(Arrays.equals(expected.get(1), actual.get(1))).isTrue();
    }

    @Test
    void does_not_found_csv_file() {
        String wrongFilePath = String.format("%s%s", fileDirectory, "/wrong/csvTestFile.csv");
        File wrongCsvFile = new File(wrongFilePath);

        assertThrows(DataFileReadFailException.class, () -> DataFileReader.readFile(wrongCsvFile));
    }

    @Test
    void does_not_exist_csv_file() {
        String nonexistentPath = String.format("%s%s", fileDirectory, "nonexistentFile.csv");
        File nonexistentFile = new File(nonexistentPath);

        assertThrows(DataFileReadFailException.class, () -> DataFileReader.readFile(nonexistentFile));
    }
}