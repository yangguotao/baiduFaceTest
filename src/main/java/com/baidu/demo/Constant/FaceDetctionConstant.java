package com.baidu.demo.Constant;

/**
 * @Author: yangguotao
 * @Date: 2019/12/30
 * @Version 1.0
 */
public interface FaceDetctionConstant {
    String ACCESSTIKEN = "access_token";
    /* baidu 应用API Key*/
    String APIKEY = "3ruCL5zeD2j87nIyXWVkTuis";
    //baidu 应用 Secret Key
    String SECRETKEY = "PjOp5C5UOOSVgtdCCNYnWwK4VI6U0KIP";
    //获取token地址
    String AUTHHOST = "https://aip.baidubce.com/oauth/2.0/token?";
    //获取 token 携带的参数
    String GRANT_TYPE = "grant_type=client_credentials";
    String CLINET_ID = "&client_id=";
    String CLIENT_SECRET = "&client_secret=";

    String ACCESSTOKENURL = AUTHHOST + GRANT_TYPE + CLINET_ID + APIKEY+ CLIENT_SECRET + SECRETKEY;


}
