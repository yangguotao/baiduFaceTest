package com.baidu.demo.pojoVo;

import com.alibaba.fastjson.JSONObject;
import com.baidu.demo.pojo.FaceDetection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 错误信息类
 *
 * @Author: yangguotao
 * @Date: 2019/12/30
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class FaceDetctionVo {

    private JSONObject results;
    private Long log_id;
    private String error_msg;
    private Integer cached;
    private Integer error_code;
    private FaceDetection faceDetection = new FaceDetection();

}
