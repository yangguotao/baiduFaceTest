package com.baidu.dome;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yangguotao
 * @Date: 2020/8/5
 * @Version 1.0
 */
public class AtomicIntegerTest implements Runnable {

    static AtomicInteger atomicInteger = new AtomicInteger(0);

    static int commonInteger = 0;

    public void addAtomicInteger() {
        atomicInteger.getAndIncrement();
    }

    public void addCommonInteger() {
        commonInteger++;
    }

    @Override
    public void run() {
        //可以调大10000看效果更明显
        for (int i = 0; i < 10000; i++) {
            addAtomicInteger();
            addCommonInteger();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();
        Thread thread1 = new Thread(atomicIntegerTest);
        Thread thread2 = new Thread(atomicIntegerTest);
        thread1.start();
        thread2.start();
        //join()方法是为了让main主线程等待thread1、thread2两个子线程执行完毕
        thread1.join();
        thread2.join();
        System.out.println("AtomicInteger add result = " + atomicInteger.get());
        System.out.println("CommonInteger add result = " + commonInteger);
    }
}
