package com.semaphore线程控制;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// 信号灯 1、共享资源互斥，2、并发线程数量的控制
public class SemaPhoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//三个车位
        for (int i = 0; i < 6; i++) {  //六部车
            new Thread(()-> {
                try {

                    semaphore.acquire();//获取到车位，车位减一
                    System.out.println(Thread.currentThread().getName() + "进入");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"停留3秒离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}