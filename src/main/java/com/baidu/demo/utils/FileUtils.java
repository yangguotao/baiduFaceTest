package com.baidu.demo.utils;

import lombok.extern.java.Log;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: yangguotao
 * @Date: 2019/12/18
 * @Version 1.0
 */
@Log
public class FileUtils {

    /**
     * multipartFile转File文件
     *
     * @param multipartFile
     * @return
     */
    public static File multipartFileToFile(MultipartFile multipartFile) {
        var filename = multipartFile.getOriginalFilename();
        var prefix = filename.substring(filename.lastIndexOf("."));
        try {
            var file = File.createTempFile(filename + "", prefix);
            multipartFile.transferTo(file);
            return file;
        } catch (IOException e) {
            log.info("文件格式转换失败");
            e.printStackTrace();
        }
        return null;
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            delteTempFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("文件格式转换失败");
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

    public List<File> getFileList(HttpServletRequest request) {
        //将当前上下文初始化给 CommonsMultipartResolver(多部份解析器)
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getServletContext());
        List<File> fileList = new ArrayList<>();
        //检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(request)) {
            //将request转换成多部份request
            MultipartHttpServletRequest servletRequest = (MultipartHttpServletRequest) request;
            //获取上传文件名称
            Iterator<String> fileNames = servletRequest.getFileNames();
            while (fileNames.hasNext()) {
                MultipartFile multipartFile = servletRequest.getFile(fileNames.next());
                File file = FileUtils.multipartFileToFile(multipartFile);
                fileList.add(file);
            }
        }
        return fileList;
    }
}
