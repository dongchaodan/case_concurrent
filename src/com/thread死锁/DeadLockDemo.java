package com.thread死锁;

import java.util.concurrent.TimeUnit;

class HoldLock implements Runnable{
    private String locakA;
    private String locakB;

    public HoldLock(String locakA, String locakB) {
        this.locakA = locakA;
        this.locakB = locakB;
    }

    @Override
    public void run() {
         synchronized (locakA){
             System.out.println(Thread.currentThread().getName()+"已经持有锁"+locakA+",想要获取锁B");
             try{
                 TimeUnit.SECONDS.sleep(2);
             }catch (Exception e){
              e.printStackTrace();
             }

             synchronized (locakB){
                 System.out.println(Thread.currentThread().getName()+"已经持有锁"+locakB+",想要获取锁A");
             }
         }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
         String alocakA = "locakA";
         String alocakB = "locakB";
         new Thread(()->{new HoldLock(alocakA,alocakB);},"AAA").start();
         new Thread(()->{new HoldLock(alocakB,alocakA);},"BBB").start();
    }
}
