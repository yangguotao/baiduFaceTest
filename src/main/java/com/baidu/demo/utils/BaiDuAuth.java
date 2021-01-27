package com.baidu.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.demo.Constant.FaceDetctionConstant;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 需要定时更新Token时间为29天 动态检测
 * <p>
 * 获取Token类
 * access_token的有效期为30天，切记需要每30天进行定期更换，或者每次请求都拉取新token；
 *
 * @Author: yangguotao
 * @Date: 2019/12/12
 * @Version 1.0
 */
@Slf4j
public class BaiDuAuth {

    public  static String ACCESS_TOKEN = "";
    public  static String ERRORDESCRIPTION = "error_description";

    /**
     * 获取token
     *
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static   String getAccessToken() {
        if (ACCESS_TOKEN.equals(""))
            ACCESS_TOKEN = getBaiduAccessToken();
        return ACCESS_TOKEN;
    }

    /**
     * 从百度云获取最新token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     *
     * @return assess_token 示例： "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getBaiduAccessToken() {

        StringBuilder result = new StringBuilder();
        String line;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(FaceDetctionConstant.ACCESSTOKENURL).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //定义BufferedReader输出流读取数据
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = bf.readLine()) != null) {
                result.append(line);
            }
            JSONObject jsob = JSONObject.parseObject(result.toString());
            ACCESS_TOKEN = jsob.getString(FaceDetctionConstant.ACCESSTIKEN);
            if (ACCESS_TOKEN.isEmpty()) {
                log.error("{}未获取到百度云TOKEN",jsob.getString(ERRORDESCRIPTION));
            }
            return ACCESS_TOKEN;
        } catch (Exception e) {
           log.error("{}百度云获取TOKEN异常",BaiDuAuth.class);
            e.printStackTrace();
        }
        return null;
    }
}
