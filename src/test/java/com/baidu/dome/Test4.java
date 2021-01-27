package com.baidu.dome;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 百度网页爬取图片
 * @Author: yangguotao
 * @Date: 2020/8/7
 * @Version 1.0
 */
public class Test4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("程序正在启动。。。");
        while (true) {
            System.out.println("请输入要查找的任务的名称(输入E或e退出程序。)");
            //1.用户输入查询的条件
            String name = scanner.next();
            if ("E".equals(name) || "e".equals(name)) {
                break;
            }
            System.out.println("正在下载，请稍后。。。");
            getPicture(name);
        }
        System.out.println("程序退出。。。");
    }

    private static void getPicture(String name) {
        // 2.创建保存文件的文件夹
        String downPath = "C://Users//57485//Desktop//" + name + System.currentTimeMillis();
        new File(downPath).mkdir();
        int count = 0;
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            //3.创建需要爬取的网页的地址,name: 为用户所查询的内容
            URL url = new URL("https://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1596784501170_R&pv=" +
                    "&ic=&nc=1&z=&hd=&latest=&copyright=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&ie=utf-8&sid=&word=" + name);
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
