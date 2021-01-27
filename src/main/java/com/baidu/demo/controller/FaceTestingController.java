package com.baidu.demo.controller;

import com.baidu.demo.ApiResult.ApiResult;
import com.baidu.demo.ApiResult.SuccessResult;
import com.baidu.demo.pojoVo.FaceDetctionVo;
import com.baidu.demo.service.FaceService;
import com.baidu.demo.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("")
public class FaceTestingController {


    @Autowired
    private FaceService faceService;


    @GetMapping("testAop")
    public ApiResult testAop(@RequestParam(name = "name",required = false)String name,
                             @RequestParam(name = "age",required = false) Integer age)  {

        if (age>20){
            throw  new IllegalArgumentException("age>20");
        }
        log.info("5: controller层接收到值：name:{},age:{}",name,age);
        return new SuccessResult(name+","+age);
    }




    @GetMapping("/getFace")
    public ApiResult faceDetection(@RequestParam(name = "name", required = false) String name,
                                   @RequestParam(name = "age", required = false) Integer age) {
        if (null == name || name.isEmpty()) {
            name = "华煤-";
        }
        if (!"华煤".equals(name)) {
            name = "华煤-";
        }
        if (age == null || age > 100 || age < -1) {
            name = "年龄异常！";
        } else {
            name += age;
        }
        return new SuccessResult(name).message("FaceDetection");
    }


    @GetMapping("/getFace2")
    public ApiResult faceDetection2(@RequestParam(name = "lastname", required = false) String lastname,
                                    @RequestParam(name = "address", required = false) String address) {

        return new SuccessResult(lastname + "," + address);
    }

    @PostMapping("/FaceDetection")
    public ApiResult faceDetection(@PathVariable("image") MultipartFile image) {

        log.info("{}访问人脸检测接口:image-->name:" + image.getName());
        File file = FileUtils.multipartFileToFile(image);
        FaceDetctionVo faceDetctionVo = faceService.FaceDetection(file);
        log.info("{}访问人脸检测接口结束,返回数据：" + faceDetctionVo.toString());
        FileUtils.delteTempFile(file);
        if (faceDetctionVo.getError_code() != 0 || faceDetctionVo.getError_msg().compareTo("SUCCESS") != 0) {
            return new SuccessResult<>(faceDetctionVo).message("参数异常，请查询api");
        }
        return new SuccessResult<>(faceDetctionVo).message("加测成功");
    }

}
