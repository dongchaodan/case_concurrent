package com.countdownlatch线程;

import java.util.concurrent.CountDownLatch;

/**
 * 线程递减 当前面所有线程执行完毕以后 最后执行主线程--CountDownLatch
 */
public class CountDownLatchDemo {
    private static final int countThread = 4;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(countThread);
        for (int i = 1; i <=countThread ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"天过去了！");
                downLatch.countDown();
            },CountDownLatchEnum.count_downLatchMsg(i).getMsg()).start();
        }
        downLatch.await();
        System.out.println("新的一年开始了！");
    }
}
