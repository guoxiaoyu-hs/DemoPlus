package com.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyTheardPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                                            2, //常驻线程数
                                            5, //线程池最大容纳线程数
                                            30L,//空闲线程存活时间
                                            TimeUnit.SECONDS,//存活时间单位
                                            new LinkedBlockingDeque<Runnable>(3),//阻塞队列
                                            Executors.defaultThreadFactory(),//生产线程池中的线程
                                            new ThreadPoolExecutor.AbortPolicy());//拒绝策略
        try {
            for (int i = 1; i <=5 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
