package com.baidu.dome;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试原子特性Atomic_*
 * @Author: yangguotao
 * @Date: 2020/8/5
 * @Version 1.0
 */
public class Test03 {

    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(()->{
               try{
                   for (int j = 0; j < 10; j++) {
                       System.out.println(atomicInteger.incrementAndGet());
                       Thread.sleep(500);
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }
            });
            threads[i].start();
        }

        for (int i = 0; i < 10000; i++) {
            atomicInteger.getAndIncrement();
        }
        System.out.println(atomicInteger.get());
    }


    public static List<Thread> getThread(){
        AtomicInteger finalI = new AtomicInteger(0);
        List<Thread>  threadList =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    finalI.incrementAndGet();
                    System.out.println("线程:"+ finalI);
                }
            });
            threadList.add(thread);
        }
        return threadList;
    }
}
