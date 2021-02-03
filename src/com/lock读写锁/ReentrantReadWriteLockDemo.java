package com.lock读写锁;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁
 *共享锁
 * 多个线程同时读一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源，其他的线程就不应该对资源进行读或写
 * 所以就是：
 *     读读可以共存
 *     读写不能共存
 *     写写不能共存
 */
class MyCache{ // 资源类
    private volatile Map<String,Object> map = new HashMap();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key,String value){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+":正在写key:"+key+",value:"+value);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+":写完成！");
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+":正在读key:"+key);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+":读完成！"+o);
    }
}
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache lockDemo = new MyCache();
        for (int i = 1; i <= 3; i++) {
            final int a = i;
            new Thread(()->{
                lockDemo.put(a+"",a+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 3; i++) {
            final int a = i;
            new Thread(()->{
                lockDemo.get(a+"");
            },String.valueOf(i)).start();
        }
    }
}