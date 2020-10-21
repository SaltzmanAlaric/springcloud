package com.study.common.constant;

/**
 * 排序枚举.
 *
 * @author kexin.ding
 */
public enum TypeEnum {

    //升序
    ASC("ASC"),
    //降序
    DESC("DESC");

    private String value;

    TypeEnum(String value) {
        this.value = value;
    }
}