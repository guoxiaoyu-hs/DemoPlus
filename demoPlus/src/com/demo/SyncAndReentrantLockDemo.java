package com.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareSource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            //判断
            while (number != 2) {
                c2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //判断
            while (number != 3){
                c3.await();
            }
            //干活
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName() +"\t" +i);
            }
            number = 1;
            //通知
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


/**
 * 多线程之间按顺序调用， 实现A->B->C三个线程启动
 * AA打印5次，BB打印10次，CC打印15次
 * 。。。。
 * 来10轮
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareSource shareSource = new ShareSource();

         new Thread(() -> {
             for (int i = 0; i <=10 ; i++) {
                 shareSource.print5();
             }
         },"AA").start();
        new Thread(() -> {
            for (int i = 0; i <=10 ; i++) {
                shareSource.print10();
            }
        },"BB").start();
        new Thread(() -> {
            for (int i = 0; i <=10 ; i++) {
                shareSource.print15();
            }
        },"CC").start();
    }
}
