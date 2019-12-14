package com.kakaopay.tourism.util.exception;

public class DataFileReadFailException extends RuntimeException {
    public DataFileReadFailException(Exception e) {
        super("업로드한 데이터 파일을 읽는데 오류가 발생했습니다.", e);
    }
}