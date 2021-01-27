package com.baidu.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.demo.pojo.Angle;
import com.baidu.demo.pojo.Emotion;
import com.baidu.demo.pojo.Expression;
import com.baidu.demo.pojo.EyeStatus;
import com.baidu.demo.pojo.FaceType;
import com.baidu.demo.pojo.Faceshape;
import com.baidu.demo.pojo.Gender;
import com.baidu.demo.pojo.Glasses;
import com.baidu.demo.pojo.Location;
import com.baidu.demo.pojo.Quality;
import com.baidu.demo.pojoVo.FaceDetctionVo;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.java.Log;

import java.io.File;
import java.util.*;

/**
 * 人脸检测
 *
 * @Author: yangguotao
 * @Date: 2019/12/12
 * @Version 1.0
 */

@Log
public class FaceDetectionUtils {

    private static final String ISTEMP = "age,beauty,expression,face_shape,gender,glasses,landmark,race,quality,eye_status,emotion,face_type";

    /**
     * @param image base64编码后的图片数据，需urlencode，编码后的图片大小不超过2M
     * @return
     */
    public static JSONObject getFaceMessage(File image) throws Exception {

        String imageStr = Base64Util.fileToBase64(image.getPath());
        JSONObject jsonObject = getFaceDetectionOL(imageStr, 1, ISTEMP);
        if (jsonObject != null) {
            System.out.println("---------------------->" + jsonObject.toString());
            jsonObject.getClass();
        }
        return jsonObject;
    }

    /**
     * @param image        base64编码后的图片数据，需urlencode，编码后的图片大小不超过2M
     * @param max_face_num 最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸
     * @return
     */
    public static JSONObject getFaceMessage(File image, Integer max_face_num) throws Exception {
//        String imageStr = ImageFormatUtil.ImagetToBase64(image);
//         FileUtil.readFileByBytes(image.getPath());
        String imageStr = Base64Util.encode(FileUtil.readFileByBytes(image.getPath()));
        JSONObject jsonObject = getFaceDetectionOL(imageStr, max_face_num, ISTEMP);

        return jsonObject;
    }

    /**
     * @param image       URLEncode is required for Base64 encoded image data, and the encoded image size shall not exceed 2m
     * @param face_fields 包括age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities信息，逗号分隔，默认只返回人脸框、概率和旋转角度
     * @return
     */
    public static JSONObject getFaceMessage(File image, String face_fields) {
        String imageStr = ImageFormatUtil.ImagetToBase64(image);
        JSONObject jsonObject = getFaceDetectionOL(imageStr, 1, face_fields);
        return jsonObject;
    }

    /**
     * @param image        base64编码后的图片数据，需urlencode，编码后的图片大小不超过2M
     * @param max_face_num 最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸
     * @param face_fields  包括age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities信息，逗号分隔，默认只返回人脸框、概率和旋转角度
     * @return
     */
    public static JSONObject getFaceMessage(File image, Integer max_face_num, String face_fields) {
        String imageStr = ImageFormatUtil.ImagetToBase64(image);
        JSONObject jsonObject = getFaceDetectionOL(imageStr, max_face_num, face_fields);
        return jsonObject;
    }

    /**
     * 人脸测试调用百度接口获取信息
     *
     * @param imageBase64  （必须）base64编码后的图片数据，需urlencode，编码后的图片大小不超过2M
     * @param max_face_num （默认为1）最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸
     * @param face_fields  包括age,beauty,expression,faceshape,gender,glasses,landmark,race,qualities信息，逗号分隔，
     *                     默认只返回人脸框、概率和旋转角度
     * @return
     */
    public static JSONObject getFaceDetectionOL(String imageBase64, int max_face_num, String face_fields) {
        String faceDetectionMessgae_Host = "https://aip.baidubce.com/rest/2.0/face/v3/detect";

        faceDetectionMessgae_Host += "?access_token=" + BaiDuAuth.ACCESS_TOKEN;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imageBase64);
            map.put("image_type", "BASE64");
            map.put("face_field", face_fields);
            String param = JsonUtils.toJson(map);
            String result = HttpUtil.post(faceDetectionMessgae_Host, BaiDuAuth.ACCESS_TOKEN, "application/json", param);
            System.out.println(result);
            JSONObject jsonb = JSON.parseObject(result);
            log.info("success--->getFaceDetectionOL:人脸测试调用百度接口获取信息成功:" + jsonb.toString());
            return jsonb;
        } catch (Exception e) {
            log.info("error--->getFaceDetectionOL:人脸测试调用百度接口获取信息失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查返回结果
     *
     * @param jsonObject
     * @return
     */
    public static FaceDetctionVo checkFaceDetectionOLResultMsg(JSONObject jsonObject) {
        FaceDetctionVo faceDetctionVo = new FaceDetctionVo()
                .setLog_id((Long) jsonObject.get("log_id"))
                .setError_msg((String) jsonObject.get("error_msg"))
                .setCached((Integer) jsonObject.get("cached"))
                .setError_code((Integer) jsonObject.get("error_code"));
        Integer error_code = (Integer) jsonObject.get("error_code");
        if (error_code != 0) {
            return faceDetctionVo;
        }
        JSONObject results = (JSONObject) jsonObject.get("result");
        Integer face_num = (Integer) results.get("face_num");
        JSONArray face_list = (JSONArray) results.get("face_list");
        List<Map> list = new ArrayList<>();
        System.out.println("利用JSONObject中的parseArray方法来解析json数组字符串");
        for (Object mapList : face_list) {
            Map<String, Object> map = new HashMap<>();
            for (Object entry : ((Map) mapList).entrySet()) {
                System.out.println(((Map.Entry) entry).getKey() + "  " + ((Map.Entry) entry).getValue());
                String key = (String) ((Map.Entry) entry).getKey();
                if (key.equals("face_shape")) {
                    Faceshape faceshape = JsonUtils.getObj((JSONObject) ((Map.Entry) entry).getValue(), Faceshape.class);
                    map.put("face_shape", faceshape);
                } else if (key.equals("face_token")) {
                    map.put("face_token", ((Map.Entry) entry).getValue());
                } else if (key.equals("location")) {
                    Location location = JsonUtils.getObj((JSONObject) ((Map.Entry) entry).getValue(), Location.class);
                    map.put("location", location);
                } else if (key.equals("face_probability")) {
                    map.put("face_probability", ((Map.Entry) entry).getValue());
                } else if (key.equals("angle")) {
                    Angle angle = JsonUtils.getObj((JSONObject) ((Map.Entry) entry).getValue(), Angle.class);
                    map.put("angle", angle);
                } else if (key.equals("age")) {
                    map.put("age", ((Map.Entry) entry).getValue());
                } else if (key.equals("beauty")) {
                    map.put("beauty", ((Map.Entry) entry).getValue());
                } else if (key.equals("expression")) {
                    Expression expression = JsonUtils.getObj((JSONObject)((Map.Entry) entry).getValue(), Expression.class);
                    map.put("expression", expression);
                } else if (key.equals("gender")) {
                    Gender gender = JsonUtils.getObj((JSONObject)((Map.Entry) entry).getValue(), Gender.class);
                    map.put("gender", gender);
                } else if (key.equals("glasses")) {
                    Glasses glasses = JsonUtils.getObj((JSONObject)((Map.Entry) entry).getValue(), Glasses.class);
                    map.put("glasses", glasses);
                } else if (key.equals("eye_status")) {
                    EyeStatus eye_status = JsonUtils.getObj((JSONObject)((Map.Entry) entry).getValue(), EyeStatus.class);
                    map.put("eye_status", eye_status);
                } else if (key.equals("emotion")) {
                    Emotion emotion = JsonUtils.getObj((JSONObject)((Map.Entry) entry).getValue(), Emotion.class);
                    map.put("emotion", emotion);
                } else if (key.equals("face_type")) {
                    FaceType face_type = JsonUtils.getObj((JSONObject) ((Map.Entry) entry).getValue(), FaceType.class);
                    map.put("face_type", face_type);
                } else if (key.equals("landmark")) {
                    map.put("landmark", ((Map.Entry) entry).getValue());//4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心。face_field包含landmark时返回
                } else if (key.equals("landmark72")) {
                    map.put("landmark", ((Map.Entry) entry).getValue());//72个特征点位置 face_field包含landmark72时返回
                } else if (key.equals("landmark150")) {
                    map.put("landmark150", ((Map.Entry) entry).getValue());//150个特征点位置 face_field包含landmark150时返回
                } else if (key.equals("quality")) {
                    Quality quality = JsonUtils.getObj((JSONObject) ((Map.Entry) entry).getValue(), Quality.class);
                    map.put("quality", quality);
                }
            }
            list.add(map);
        }
        faceDetctionVo.getFaceDetection().setFace_list(list);
        return faceDetctionVo;
    }
}
