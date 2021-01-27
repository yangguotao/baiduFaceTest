package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 双眼状态（睁开/闭合） face_field包含eye_status时返回
 *
 * @Author: yangguotao
 * @Date: 2020/1/3
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class EyeStatus {
    private double left_eye;//左眼状态 [0,1]取值，越接近0闭合的可能性越大
    private double right_eye;//右眼状态 [0,1]取值，越接近0闭合的可能性越大

}
