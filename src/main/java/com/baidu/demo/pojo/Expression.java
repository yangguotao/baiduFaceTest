package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 表情，当 face_field包含expression时返回
 *
 * @Author: yangguotao
 * @Date: 2020/1/3
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Expression {

    private String type;//脸型：square/triangle/oval/heart/round
    private double probability;//置信度：0-1

}
