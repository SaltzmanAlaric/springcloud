package com.study.common.web;

/**
 * 状态响应
 */
public enum ResponseCode {

    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),
    /**
     * 请求失败
     */
    FAIL(1, "请求失败"),

    /**
     * 请求出现语法错误
     */
    BADREQUEST(400, "参数错误"),

    /**
     * 未认证
     */
    UNAUTHORIZED(401, "未认证"),

    /**
     * 不支持的请求type
     */
    UN_SUPPORT(403, "不支持的请求type"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404, "URL不存在"),

    /**
     * 服务内部错误
     */
    INTERNAL_SERVER_ERROR(500, "服务内部错误"),

    /**
     * 未知错误
     */
    UNKNOW_ERROR(509, "未知错误");

    /**
     * 响应码
     */
    private final int code;
    /**
     * 响应码说明
     */
    private final String name;

    ResponseCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 获取响应码
     *
     * @return 响应码
     */
    public int code() {
        return code;
    }

    /**
     * 获取响应码说明
     *
     * @return 响应码说明
     */
    public String message() {
        return name;
    }

}
