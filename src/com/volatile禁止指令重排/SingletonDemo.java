package com.volatile禁止指令重排;

public class SingletonDemo { // 单例模式中案例
   private static  SingletonDemo instance = null;
//   private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"\t 构造方法");
    }

    public static SingletonDemo getInstance() {
        if(instance==null){
            // 双端检索机制--可能有指令重排序的可能
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }

    }
}
