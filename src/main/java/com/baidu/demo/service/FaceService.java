package com.baidu.demo.service;

import com.baidu.demo.pojoVo.FaceDetctionVo;

import java.io.File;

/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
public interface FaceService {

    FaceDetctionVo FaceDetection(File filePath);

    default boolean FaceServerTest(){
        return true;
    }

}
