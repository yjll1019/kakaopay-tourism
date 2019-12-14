package com.kakaopay.tourism.domain.exception;

public class IdGenerateFailException extends RuntimeException {
    public IdGenerateFailException(Exception e) {
        super("ID를 생성하는데 오류가 발생했습니다.", e);
    }
}
