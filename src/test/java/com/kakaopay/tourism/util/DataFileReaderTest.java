package com.kakaopay.tourism.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

        List<String[]> expected = new ArrayList<>();
        expected.add(new String[]{"번호", "프로그램명", "테마별 분류", "서비스 지역", "프로그램 소개", "프로그램 상세 소개"});
        expected.add(new String[]{"1", "설악산", "생태체험", "설악산 탐방안내소, 신흥사, 권금성, 비룡폭포",
                "설악산 탐방안내소", "설악산은 왜 설악산이고"});
        expected.add(new String[]{"25", "지리산국립공원 생태체험 학습!", "생태체험,문화생태체험", "경상남도 산청군, 하동군등",
                "중산리탐방안내소~중산리 자연관찰로~하동쌍계사~하동 차문화센터",
                "지리산국립공원의 자연환경과 문화유산을 탐방하면서 느끼고 체험할 수 있는 프로그램입니다."});

        List<String[]> actual = DataFileReader.readFile(multipartFile);
        assertThat(Arrays.equals(expected.get(0), actual.get(0))).isTrue();
        assertThat(Arrays.equals(expected.get(1), actual.get(1))).isTrue();
    }
}