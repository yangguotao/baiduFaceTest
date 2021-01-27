package com.baidu.dome;


/**
 * @Author: yangguotao
 * @Date: 2019/12/16
 * @Version 1.0
 */
public class Test02 {

    public static void main(String[] args) {

       int count = lengthOfLongestSubstring("asdfaaaaha");
        System.out.println(count);
    }

    public static int lengthOfLongestSubstring(String s) {
        int countF = 0;
        String[] strings =s.split("");
        byte[] arrays = new byte[128];
        s.charAt(1);
        for (String falg : strings) {
            int countX = 0;
            for (String string : strings) {
                if (!falg.equals(string)) {
                    countX++;
                } else {
                    break;
                }
            }
            if (countX > countF)
                countF = countX;
        }
        return countF;
    }
}
