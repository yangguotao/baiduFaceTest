package com.baidu.dome;


import java.awt.*;

/**
 * @Author: yangguotao
 * @Date: 2019/12/13
 * @Version 1.0
 */
public class Test01 {

    public static void main(String[] args) {
        var str = "108.92588,34.2346;108.925434,34.233864;108.925171,34.233429;108.927612,34.233434;108.927639,34.233833;108.928208,34.233872;108.928181,34.234998";
        long staTime = System.nanoTime();
        String[] strarr = str.split(";");
        Point[] pointArr = new Point[strarr.length];
        for (int i = 0; i < strarr.length; i++) {
            String[] strarr2 = strarr[i].split(",");
            System.out.println(strarr2[0] + "---" + strarr2[1]);
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - staTime);

        long staTime2 = System.nanoTime();
        //Point[] point = new Point[]{};
        str = str.replaceAll(",", "---");
        String[] strarr3 = str.split(";");
        Point[] pointArr2 = new Point[strarr3.length];
        for (int i = 0; i < strarr3.length; i++) {
            String[] split = strarr3[i].split("---");
            System.out.println(split[0] + "---" + split[1]);
        }
        long endTime2 = System.nanoTime();
        System.out.println(endTime2 - staTime2);
    }
}
