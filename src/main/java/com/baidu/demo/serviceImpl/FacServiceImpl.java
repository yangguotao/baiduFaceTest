package com.baidu.demo.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.demo.pojoVo.FaceDetctionVo;
import com.baidu.demo.utils.FaceDetectionUtils;
import com.baidu.demo.service.FaceService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Service
public class FacServiceImpl implements FaceService {
    /**
     * 人脸检测
     *
     * @param image
     * @return 检测数据集合
     */
    @Override
    public FaceDetctionVo FaceDetection(File image) {
        try {
            JSONObject jsonObject = FaceDetectionUtils.getFaceMessage(image);
            return FaceDetectionUtils.checkFaceDetectionOLResultMsg(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
