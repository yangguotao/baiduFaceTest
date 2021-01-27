package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 真实人脸/卡通人脸置信度
 *
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FaceType {
    private String type;//human: 真实人脸 cartoon: 卡通人脸
    private double probability;//人脸类型判断正确的置信度，范围【0~1】，0代表概率最小、1代表最大。
}
