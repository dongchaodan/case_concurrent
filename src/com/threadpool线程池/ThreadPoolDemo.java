package com.threadpool线程池;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    /*
      1 、继承Thread 类
      2、 实现Runnable接口 没有返回值 不抛异常
      3、 实现CallAble 接口 有返回值  抛异常

    */
    public static void main(String[] args) {

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。  【默认】
//        ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
//        ThreadPoolExecutor.DiscardOldestPolicy：丢弃线称队列的旧的任务，将新的任务添加
//        ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务 【谁调用，谁处理】
        ExecutorService executor = new ThreadPoolExecutor(2,5,2L,
                TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(5),
//                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());  // 7个参数
//                Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());  // 7个参数
//                Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());  // 7个参数
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());  // 7个参数
        try {
            for (int i = 0; i < 15; i++) {
                int finalI = i;
                executor.execute(() -> {
                    System.out.println(finalI + "----" + Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
