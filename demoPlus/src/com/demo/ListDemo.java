package com.demo;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDemo {

    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();  //单线程下ArrayList没有问题，但是在多线程下容易发成并发修改异常  java.util.ConcurrentModification Exception

        //解决ArrayList线程不安全的方法  PS：set集合同理，map集合的并发类的对对象是ConcurrentMap OR ConcurrentHashMap
        //1.0    List<String> list = new Vector<>();
        //2.0   List<String> list = Collections.synchronizedList(new ArrayList<>());

        List<String> list = new CopyOnWriteArrayList<>();  //3.0

        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+"\t" + list);
            }, String.valueOf(i)).start();
        };
    }

}
