package com.baidu.demo.controller;

import com.baidu.demo.ApiResult.SuccessResult;
import com.baidu.demo.ApiResult.ApiResult;
import com.baidu.demo.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 语音文字转化api
 * @Author: yangguotao
 * @Date: 2020/3/27
 * @Version 1.0
 */
@Slf4j
@RestController()
@RequestMapping("")
public class languageController {

    @PostMapping("/getFileContent")
    public ApiResult getFileContent(@PathVariable("image") MultipartFile multipartFile) {
        log.info("{}进入文字转换起始时间是：",System.currentTimeMillis());
        if(null != multipartFile){
            File file = FileUtils.multipartFileToFile(multipartFile);
            //断言 类似if判断
            assert null != file;
            String filePath = file.getPath();
        }
       return new SuccessResult<>();
    }
}
