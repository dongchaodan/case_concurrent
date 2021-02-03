package com.lock自旋锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class TheSpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + ":开始获取锁");
        while (!atomicReference.compareAndSet(null, thread)) {
        }
        System.out.println(thread.getName() + ":已经获取锁");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + ":释放锁");

    }

    public static void main(String[] args) {
        TheSpinLockDemo lockDemo = new TheSpinLockDemo();

        new Thread(() -> {
            lockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockDemo.myUnLock();
        }, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            lockDemo.myLock();
            lockDemo.myUnLock();
        }, "t2").start();
    }
}
