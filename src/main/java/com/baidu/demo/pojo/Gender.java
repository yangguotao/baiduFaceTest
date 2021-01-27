package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 性别，face_field包含gender时返回
 *
 * @Author: yangguotao
 * @Date: 2020/1/3
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Gender {
    private String type;//male:男性 female:女性
    private double gender_probability;//性别置信度，范围[0~1]，face_fields包含gender时返回
}
