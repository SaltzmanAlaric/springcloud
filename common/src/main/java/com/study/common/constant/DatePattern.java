package com.study.common.constant;

import lombok.Getter;

/**
 * 日期格式枚举.
 *
 * @author kexin.ding
 */
@Getter
public enum DatePattern {

    DATE("yyyy-MM-dd"),
    DATE0("yyyyMMdd"),
    DATE1("yyyyMM"),
    TIME("HH:mm:ss"),
    HHMM("HH:mm"),
    DATETIME("yyyy-MM-dd HH:mm:ss"),
    DATETIME0("yyyyMMddHHmmss"),
    DATETIME1("yyyyMMdd HH:mm"),
    ;

    private String value;

    DatePattern(String value) {
        this.value = value;
    }

}
