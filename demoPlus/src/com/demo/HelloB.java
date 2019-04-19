package com.demo;

class HelloA {
    public HelloA() {
        System.out.println("HelloA");//4
    }

    {
        System.out.println("I am A class"); //3
    }

    static {
        System.out.println("static A");  //1
    }
}

public class HelloB extends HelloA {
    public HelloB() {
        System.out.println("HelloB");
    }
    {
        System.out.println("I am B class");
    }
    static {
        System.out.println("static B");   //2
    }
    public static void main(String[] args) {
        //new HelloA();
        //System.out.println("---------");
        new HelloB(); //static A static B  amClass A helloA amclassB  helloB
    }

}
