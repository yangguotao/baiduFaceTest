package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 人脸各部分遮挡的概率，范围[0~1]，0表示完整，1表示不完整
 *
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Occlusion {
    private double left_eye;//左眼遮挡比例
    private double right_eye;//右眼遮挡比例
    private double nose;//鼻子遮挡比例
    private double mouth;//嘴巴遮挡比例
    private double left_cheek;//左脸颊遮挡比例
    private double right_cheek;//右脸颊遮挡比例
    private double chin;//下巴遮挡比例
}
