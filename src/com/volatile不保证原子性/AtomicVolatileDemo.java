package com.volatile不保证原子性;

/**
 * 验证volatile 的不保证原子性
 */
class MyAdd{
    volatile int number = 0;

    public void add60() {
        this.number = 60;
    }
    public void add1() {
        this.number++;
    }
//    AtomicInteger atomicInteger = new AtomicInteger();
//    public void addatomicInteger() {
//        atomicInteger.getAndIncrement();
//    }
}
// volstile 原子性的Demo
public class AtomicVolatileDemo {
    public static void main(String[] args) {
        MyAdd myAdd = new MyAdd();
        for (int i = 0; i < 20; i++) {
             new Thread(()->{
                 for (int j = 0; j < 10000; j++) {
                     myAdd.add1();
//                     myAdd.addatomicInteger();
                 }
             },String.valueOf(i)).start();
        }
     while (Thread.activeCount()>2){
         Thread.yield();
     }
        System.out.println(myAdd.number);
//        System.out.println(myAdd.atomicInteger);

        Thread thread = new Thread();
        thread.start();
    }
}
