package com.demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("-*************callable come in !!!");
         try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(new MyThread());

         new Thread(integerFutureTask,"AAA").start();
         new Thread(integerFutureTask,"BBB").start();

         int num = 100;

        System.out.println(Thread.currentThread().getName()+"main+++++++++++++++++++++++");
         while (!integerFutureTask.isDone()){

         }
        Integer x = integerFutureTask.get();
        System.out.println(x);

        System.out.println(Thread.currentThread().getName()+"\t" +(num+x) );
    }
}
