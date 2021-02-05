package com.cyclicbarrier线程;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()-> {System.out.println("-------");});
        for (int i = 1; i <=7 ; i++) {
            final int a = i;
             new Thread(()->{
                 System.out.println("收集到第"+a+"棵");
                 try {
                     cyclicBarrier.await();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 } catch (BrokenBarrierException e) {
                     e.printStackTrace();
                 }
             },String.valueOf(i)).start();
        }
    }
}
