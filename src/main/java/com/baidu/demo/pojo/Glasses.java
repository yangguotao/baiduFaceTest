package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 是否带眼镜，face_field包含glasses时返回
 *
 * @Author: yangguotao
 * @Date: 2020/1/3
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Glasses {

    private String type;//none:无眼镜，common:普通眼镜，sun:墨镜
    private double glasses_probability;//眼镜置信度，范围【0~1】，0代表概率最小、1代表最大。

}
