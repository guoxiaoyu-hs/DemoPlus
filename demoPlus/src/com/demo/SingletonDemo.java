package com.demo;


public class SingletonDemo {

   private volatile static SingletonDemo instance  = null;

   private SingletonDemo (){
       System.out.println(Thread.currentThread().getName()+"\t 我是一个私有的构造器！！！");
   }

   public static SingletonDemo getInstance(){
       if(instance == null){
           synchronized (SingletonDemo.class){
               if(instance == null){
                   instance = new SingletonDemo();
               }
           }
       }
       return instance;
   }

    public static void main(String[] args) {
     /*   SingletonDemo instance = SingletonDemo.getInstance();
        SingletonDemo instance2= SingletonDemo.getInstance();*/

     //单例模式在单线程的情况下是没问题的，但是在多线程的情况下会发生线程安全问题，原因是底层指令重排/
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
        ;
    }
}
