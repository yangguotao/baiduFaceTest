package com.baidu.dome;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

/**
 * 爬取图片
 *
 * @Author: yangguotao
 * @Date: 2020/10/29
 * @Version 1.0
 */

@Slf4j
public class Test06 {

    //测试（自定义一个json的日期类型格式化类）
    @JsonSerialize(using = DateJsonSerializer.class)
    private Date testDate;
    //picUrl
    public static List<String> picUrls = new ArrayList<>();
    //图片位置
    public static String PIC_PATH_URL = "C://Users//57485//Desktop//";
    //网页地址
    public static String picPageMsgUrl = "https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&" +
            "lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1596784501170_R&pv=" +
            "&ic=&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&" +
            "width=&height=&face=0&istype=2&ie=utf-8&sid=&word=";
    //查询的名字
    public static String findName;
    //校验规则
    public static String regularExpression = "https://.*?0\\.jpg";


//    public static void main(String[] args) {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("程序正在启动。。。");
//        while (true) {
//            System.out.println("请输入要查找的任务的名称(输入E或e退出程序。)");
//            //1.用户输入查询的条件
//            String name = scanner.next();
//            if ("E".equals(name) || "e".equals(name)) {
//                break;
//            }
//            System.out.println("正在下载，请稍后。。。");
//            getPicture(name);
//        }
//        System.out.println("程序退出。。。");
//    }


    public static void main(String[] args) {
        //启动程序，初始化数据
        startCodeing();
        //创建保存文件
        makedir(findName,PIC_PATH_URL);
        //获取下载网页信息
        String picPageMsg = findMsgPag(picPageMsgUrl);
        //下载图片
        findPicUrlOnPageMessage(picPageMsg,regularExpression);
    }


    /**
     * 启动程序
     */
    public static void startCodeing() {

        System.out.println("程序启动中...");
        while (true) {
            try {
                sleep(3000);
                System.out.println("启动成功！！！");
                sleep(500);

                Scanner sc = new Scanner(System.in);
                System.out.println("请输入网页地址(默认使用百度进行查询,默认请按1,输入E或e退出程序。)");
                String scStr = sc.next();
                if (!shutdownCode(scStr)) {
                    System.out.println("程序结束。。。");
                    break;
                }
                if (!"1".equals(scStr)) {
                    picPageMsgUrl = scStr;
                }

                sleep(200);

                System.out.println("请输入要查找的任务的名称(输入E或e退出程序。)");
                findName = sc.next();
                if (!shutdownCode(findName)) {
                    System.out.println("程序结束。。。");
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("PIC_PATH_URL:" + PIC_PATH_URL);
                System.out.println("picPageMsgUrl:" + picPageMsgUrl);
                System.out.println("findName:" + findName);
            }
        }
    }
    /**
     * 结束程序
     */
    public static boolean shutdownCode(String shutDownCode){
       switch (shutDownCode){
           case "E" : return false;
           case "e" : return false;
       }
       return true;
    }
    /**
     * 创建保存的文件夹
     *
     * @param name 爬取的名称
     * @param path 存放照片的全路径 C://Users//57485//Desktop//
     * @return
     */
    private static String makedir(String name, String path) {
        path = path + name + System.currentTimeMillis();
        new File(path).mkdir();
        PIC_PATH_URL = path;
        return path;
    }

    //TODO 创建网页url地址

    /**
     * 获取网页
     *
     * @param url 网页url
     * @return pageText 网页字符串信息
     */
    public static String findMsgPag(String url) {

        if (url.isBlank()) {
            return null;
        }

        try {
            int len;
            byte[] bt = new byte[1024];
            StringBuffer stringBuffer = new StringBuffer();
            URL newUrl = new URL(url);
            InputStream inputStream = newUrl.openStream();
            while ((len = inputStream.read(bt)) != -1) {
                stringBuffer.append(new String(bt, 0, len, StandardCharsets.UTF_8));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
           // log.error("Get pageMessage Error!: {}", Test06.findMsgPag(null));
        }
        return null;
    }

    //TODO 编写正则表达式对数据进行校验获取图片地址

    private static void findPicUrlOnPageMessage(String pageMessage, String regularExpression) {
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(pageMessage);

        int downCount = 0;
        while (matcher.find()) {
            String picUrl = matcher.group();
            if (picUrls.contains(picUrl)) {
                continue;
            }
            downCount++;
            log.info("开始下载第" + downCount + "张图片");
            //利用picUrl下载图片并保存
            downLoadPicByPicUrl(picUrl,downCount);
        }
    }

    private static   void downLoadPicByPicUrl(String picUrl,int downCount) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            URL newUrl = new URL(picUrl);
            inputStream = newUrl.openStream();
            int len;
            byte[] bt = new byte[1024];
            outputStream = new FileOutputStream(picUrl + "\\" + System.currentTimeMillis() + ".jpg");
            if ((len = inputStream.read(bt)) != -1)
                outputStream.write(bt, 0, len);
            log.info("第"+downCount+"下载完成。");
            picUrls.add(picUrl);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("图片下载失败！");
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    //TODO 利用图片地址进行图片下载保存至文件夹
    private static void getPicture(String name) {
        // 2.创建保存文件的文件夹
        String downPath = "C://Users//57485//Desktop//" + name + System.currentTimeMillis();
        //单纯的创建个文件夹
        new File(downPath).mkdir();
        int count = 0;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            //3.创建需要爬取的网页的地址,name: 为用户所查询的内容
            URL url = new URL("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1" +
                    "&fm=result&fr=&sf=1&fmq=1596784501170_R&pv=" +
                    "&ic=&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8" +
                    "&sid=&word=" + name);
            inputStream = url.openStream();
            int len;
            byte[] b = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(b)) != -1) {
                sb.append(new String(b, 0, len, StandardCharsets.UTF_8));
            }
            //爬取目标网页
            String pageText = sb.toString();
            //创建查询符合目标结果的正则表达式
            Pattern pattern = Pattern.compile("https://.*?0\\.jpg");
            Matcher matcher = pattern.matcher(pageText);
            ArrayList<String> urls = new ArrayList<>();
            //寻找符合目标结果的地址
            while (matcher.find()) {
                String eachURLStr = matcher.group();
                if (urls.contains(eachURLStr)) {
                    continue;
                }
                count++;
                //利用目标地址下载目标结果
                URL eachUrl = new URL(eachURLStr);
                inputStream = eachUrl.openStream();
                fos = new FileOutputStream(downPath + "\\" + System.currentTimeMillis() + ".jpg");
                while ((len = inputStream.read(b)) == -1) {
                    fos.write(b, 0, len);
                }
                //关闭流
                inputStream.close();
                fos.flush();
                fos.close();
                urls.add(eachURLStr);
            }
            urls.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("下载错误!");
            e.printStackTrace();
        } finally {
            System.out.println("下载完成，共下载了" + count + "图片，请到  " + downPath + "  目录下查看");
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
