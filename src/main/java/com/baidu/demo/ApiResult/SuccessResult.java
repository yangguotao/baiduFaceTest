package com.baidu.demo.ApiResult;

import com.baidu.demo.enums.EnumMessage;

/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
public class SuccessResult<T> extends ApiResult<T> {
    public SuccessResult() {
        super(EnumMessage.SUCCESS);
    }

    public SuccessResult(T data) {
        super(data, EnumMessage.SUCCESS);
    }

    public SuccessResult message(String message) {
        super.setMessage(message);
        return this;
    }
}
