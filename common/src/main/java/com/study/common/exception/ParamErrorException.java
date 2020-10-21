package com.study.common.exception;

/**
 * 入参错误异常类.
 */
public class ParamErrorException extends RuntimeException {

    public ParamErrorException() {
    }

    public ParamErrorException(String message) {
        super(message);
    }

}