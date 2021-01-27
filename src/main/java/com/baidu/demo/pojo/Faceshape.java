package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 脸型置信度。face_fields包含faceshape时返回
 *
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Faceshape {
    private String type;//脸型：square/triangle/oval/heart/round
    private double probability;//置信度：0-1

}
