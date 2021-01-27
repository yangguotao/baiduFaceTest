package com.baidu.dome;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: yangguotao
 * @Date: 2020/9/9
 * @Version 1.0
 */
public class Test05 {
    /**
     * 异步方法测试ComoletableFuture
     */
    @Test
    public void runAsyncExample(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture completableFuture = CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1------"+Thread.currentThread().getName());
            },executorService);
        System.out.println("2--------"+Thread.currentThread().getName());
        while (true){
            if (completableFuture.isDone()) {
                System.out.println("3--------CompletedFuture...isDown");
                break;
            }
        }
    }

    @Test
    public void thenApply(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("supplyAsyns: "+Thread.currentThread().getName());
            return "hello";
        },executorService).thenAccept(s ->{
            try {
                System.out.println("thenAccept: "+s+" world");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        System.out.println("thenApply: "+Thread.currentThread().getName());
        while (true){
            if(completableFuture.isDone()){
                System.out.println("CompletedFuture...isDown");
                break;
            }
        }
    }

    @Test
    public void testOptional(){
        Optional<Object> optional = Optional.of("");
        optional.ifPresentOrElse(System.out::println,()-> System.out.println("张三"));
    }

    @Test
    public void testAtomic(){

        AtomicInteger atomicInteger = new AtomicInteger(0);
        //getAndIncrement先get后++
        int a = atomicInteger.getAndIncrement();
        System.out.println(a); //0
        System.out.println(atomicInteger.get()); //1
        //incrementAndGet先++后获取
        a = atomicInteger.incrementAndGet();
        System.out.println(a); //2
        System.out.println(atomicInteger.get()); //2

    }

    @Test
    public void test05(){
        Object list = (Object)new ArrayList<>();

        String str = "123";
        System.out.println(list.getClass().getName());
    }}
