package com.thread线程生产者消费者;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t" +number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t" +number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}

// 题目  两个线程，对一个变量，一个加一 一个减一 交替操作5次
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
          new Thread(()->{
              for (int i = 0; i < 5; i++) {
                  resource.increment();
              }
          },"A").start();
          new Thread(()->{
              for (int i = 0; i < 5; i++) {
                  resource.decrement();
              }

          },"B").start();
    }
}