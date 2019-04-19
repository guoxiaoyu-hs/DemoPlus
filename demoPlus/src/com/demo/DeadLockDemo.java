package com.demo;

import java.util.concurrent.TimeUnit;

class HoldLOckThread implements Runnable {
    private String lockA;
    private String lockB;
    public HoldLOckThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有一个"+lockA+"\t尝试获取\t"+lockB);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有一个"+lockB+"\t尝试获取\t"+lockA);
            }
        }
    }
}
//前程无忧

/**
 * 死锁案例
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLOckThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLOckThread(lockB,lockA),"ThreadBBB").start();
    }
}
