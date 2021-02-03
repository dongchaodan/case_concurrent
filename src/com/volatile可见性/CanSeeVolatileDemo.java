package com.volatile可见性;

import java.util.concurrent.TimeUnit;

class MyData {

//    int number = 0;
    volatile int number = 0;

    public void add60() {
        this.number = 60;
    }
}

// volstile 可见性的Demo
public class CanSeeVolatileDemo {

    public static void main(String[] args) {
        MyData myData = new MyData();
       new Thread(()->{
           System.out.println(Thread.currentThread().getName()+"in");
           try {
               TimeUnit.SECONDS.sleep(3);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           myData.add60();
           System.out.println(Thread.currentThread().getName()+"+60"+myData.number);
       },"AA").start();


    while (myData.number==0){

    }
        System.out.println(Thread.currentThread().getName()+"end");
    }
}
