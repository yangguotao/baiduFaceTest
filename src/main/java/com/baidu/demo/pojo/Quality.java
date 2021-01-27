package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 人脸质量信息。face_field包含quality时返回
 *
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Quality {
    private Occlusion occlusion;//人脸各部分遮挡的概率，范围[0~1]，0表示完整，1表示不完整
    private double blur;//人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊
    private int illumination;//取值范围在[0~255],表示脸部区域的光照程度
    private int completeness;//人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内
}
