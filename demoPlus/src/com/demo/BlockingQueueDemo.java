package com.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //List list = new ArrayList();

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(3);
        //3.0阻塞  *****慎用  但是很强大  用好的话很nb
        //满了就一直在等  在等别的数据出去   消息中间件的底层有
        //队列满的时候一直死战不退
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        System.out.println("============================");
        //blockingQueue.put("x");

        //今儿买不到蛋糕我不走 -------
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();






        //2.0  插入成功true  失败false
    /*    System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("xxx"));

        System.out.println(blockingQueue.peek());

        //取得失败就是 null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/


        //1.0抛出异常组
      /*  System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element()); //d

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/
       // System.out.println(blockingQueue.remove());
    }
}
