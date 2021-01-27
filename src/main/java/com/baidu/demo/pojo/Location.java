package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 人脸在图片中的位置
 *
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Location {
    private int left;//人脸区域离左边界的距离
    private int top;//人脸区域离上边界的距离
    private int width;//人脸区域的宽度
    private int height;//人脸区域的高度
}
