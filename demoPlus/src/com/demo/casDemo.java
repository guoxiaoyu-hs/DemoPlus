package com.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自定义自旋锁
 */
public class casDemo {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void MyLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in ##################");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void MyUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t Go out MyUnLock");
    }
    public static void main(String[] args) {
        casDemo casDemo = new casDemo();

         new Thread(() -> {
             casDemo.MyLock();
             try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
             casDemo.MyUnLock();
         },"AA").start();

          try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

         new Thread(() -> {
              casDemo.MyLock();
              try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
              casDemo.MyUnLock();
         },"BB").start();
    }
}
