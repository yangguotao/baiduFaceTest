package com.baidu.demo.enums;

/**
 * @Author: yangguotao
 * @Date: 2019/12/13
 * @Version 1.0
 */
public enum EnumMessage {

    SUCCESS(0, "SUCCESS"),
    ERROR(-1, "ERROR"),
    NOT_FOUND(-2, "Not Found");


    private Integer code;
    private String message;

    EnumMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
