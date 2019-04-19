package com.demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"：离开教室走了");
                countDownLatch.countDown(); //没运行一次计数器-1
            }, String.valueOf(i)).start();
        };

        countDownLatch.await(); //计数器没有=0时  就是要等待
        System.out.println(Thread.currentThread().getName()+"班长关门走人");
    }
}
