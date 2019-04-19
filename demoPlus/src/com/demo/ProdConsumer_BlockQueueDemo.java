package com.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue= null;

    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data,2L,TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 生产队列生产"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 生产队列生产"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停，生产停止");
    }
    public void myConsmer() throws Exception{
        String resilt = null;
        while (FLAG){
            resilt = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(resilt == null || resilt.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t等待两秒，消费失败");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列消费"+resilt+"成功");
        }
    }

    public void stop (){
        FLAG = false;
    }
}

/**
 * 3.0高并发版  生产者消费者
 * volatile/CAS/atomicInteger/BlockQueue/线程交互/源自引用
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));

         new Thread(() -> {
             System.out.println(Thread.currentThread().getName()+"\t 生产开始");
             try {
                 myResource.myProd();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         },"prod").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 消费开始");
            try {
                System.out.println();
                System.out.println();
                myResource.myConsmer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(Thread.currentThread().getName()+"\t 大老板main叫停");
          myResource.stop();
    }
}
