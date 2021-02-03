package com.lock可重入锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//class Phone {
//     public synchronized void sendSMS(){
//         System.out.println(Thread.currentThread().getName()+"：发短信！");
//         sendEmail();
//     }
//    public synchronized void sendEmail(){
//        System.out.println(Thread.currentThread().getName()+"：发邮件！");
//    }
//}
class Phone implements Runnable{

    Lock lock = new ReentrantLock();

    /**
     * get进去的时候，就加锁，调用get方法的时候，能否访问另外一个加锁的set方法
     */
    public void getLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get Lock");
            setLock();
        } finally {
            lock.unlock();
        }
    }

    public void setLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set Lock");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        getLock();
    }
}

/**
 * 线程可以进入任何一个他已经拥有的锁 所同步的代码块
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
         Phone phone = new Phone();
         Thread t3 = new Thread(phone,"t3");
         Thread t4 = new Thread(phone,"t4");
         t3.start();
         t4.start();
    }
}
