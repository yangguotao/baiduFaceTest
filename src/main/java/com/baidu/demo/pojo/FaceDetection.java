package com.baidu.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 百度人检测实体类
 *
 * @Author: yangguotao
 * @Date: 2019/12/12
 * @Version 1.0
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FaceDetection {

    private int result_num;//人脸数目
    private List face_list; //人脸属性对象的集合


}