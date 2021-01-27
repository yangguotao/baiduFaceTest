package com.baidu.demo.ApiResult;

import com.baidu.demo.enums.EnumMessage;

/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
public class ErrorResult<T> extends ApiResult<T> {
    public ErrorResult() {
        super(EnumMessage.ERROR);
    }

    public ErrorResult(T data, EnumMessage enumMessage) {
        super(data, enumMessage.ERROR);
    }

    public ErrorResult ErrorResult(String message) {
        super.setMessage(message);
        return this;
    }
}
