package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 人脸属性对象的集合
 *
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Result {
    private double age;//年龄。face_fields包含age时返回
    private double beauty;//美丑打分，范围0-100，越大表示越美。face_fields包含beauty时返回
}
