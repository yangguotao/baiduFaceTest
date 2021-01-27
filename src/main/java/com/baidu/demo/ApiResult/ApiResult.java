package com.baidu.demo.ApiResult;

import com.baidu.demo.enums.EnumMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Data
public class ApiResult<T> implements Serializable {

    private T data;
    private Integer code;
    private String message;

    public ApiResult(EnumMessage enumMessage) {
        this.code = enumMessage.getCode();
        this.message = enumMessage.getMessage();
    }

    public ApiResult(T data, EnumMessage enumMessage) {
        this.data = data;
        this.code = enumMessage.getCode();
        this.message = enumMessage.getMessage();
    }

    public ApiResult(Throwable throwable) {
        this.code = EnumMessage.ERROR.getCode();
        this.message = throwable.getMessage();
    }

    public ApiResult(Throwable throwable, EnumMessage enumMessage) {
        this.code = enumMessage.getCode();
        this.message = throwable.getMessage();
    }

}
