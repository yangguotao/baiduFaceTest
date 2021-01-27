package com.baidu.demo.utils;

import lombok.extern.java.Log;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 图片格式转换
 *
 * @Author: yangguotao
 * @Date: 2019/12/13
 * @Version 1.0
 */
@Log
public class ImageFormatUtil {
    /**
     * 图片转base64位
     *
     * @param file
     * @return
     */
    public static String ImagetToBase64(File file) {
        FileInputStream fileInputStream;
        byte[] data = null;
        try {
            fileInputStream = new FileInputStream(file);
            data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            fileInputStream.close();
        } catch (Exception e) {
            log.info("jpeg--->base:图片格式转换失败");
            e.printStackTrace();
        }
        String base64 = Base64Utils.encodeToUrlSafeString(data);
        return base64;
    }

    /**
     * base64转图片
     *
     * @param base64   图片base64格式
     * @param savePath 新生成的图片
     * @return
     */
    public static String Base64ToImage(String base64, String savePath) {
        if (base64.isEmpty()) {
            log.info("base64数据位空");
            return "";
        }
        try {
            byte[] b = Base64Utils.decodeFromUrlSafeString(base64);
            for (int i = 0; i < b.length; i++) {
                if (b[i] < 0) {//调整数据异常
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            fileOutputStream.write(b);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            log.info("base64--->jpeg:图片格式转换失败");
            e.printStackTrace();
        }
        return savePath;
    }
}
