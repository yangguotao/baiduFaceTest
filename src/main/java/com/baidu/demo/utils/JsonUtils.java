package com.baidu.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;

/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
public class JsonUtils extends GsonUtils {
    /**
     * 将json数据封装对象返回
     *
     * @param jsonObject 带有数据的json对象
     * @param ObjClass   需要封装的对象;
     * @return
     */
    public static <T> T getObj(JSONObject jsonObject, Class<?> ObjClass) {
        String jsonStr = jsonObject.toString();
        return JSON.parseObject(jsonStr, (Type) ObjClass);
    }

}
