package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 人脸旋转角度参数
 *
 * @Author: yangguotao
 * @Date: 2020/1/3
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Angle {

    private double yaw;//三维旋转之左右旋转角[-90(左), 90(右)]
    private double pitch;//三维旋转之俯仰角度[-90(上), 90(下)]
    private double roll;//平面内旋转角[-180(逆时针), 180(顺时针)]

}
