package com.thread线程生产者消费者;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareMySource {
    private volatile Boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue = null;

    public ShareMySource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String judge = null;
        boolean result = false;
        while (FLAG) {

            judge = atomicInteger.incrementAndGet() + "";
            try {
                result = blockingQueue.offer(judge, 2L, TimeUnit.SECONDS);
                if (result) {
                    System.out.println("插入阻塞队列成功,此时插入数据为" + judge);
                } else {
                    System.out.println("插入阻塞队列失败,此时插入数据为" + judge);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("FLAG = false 停止生产。。。。。。");
    }


    public void myConsum() {
        String result = null;
        while (FLAG) {
            try {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (result == null || "".equalsIgnoreCase(result)) {
                    System.out.println("消费失败");
                    FLAG = false;
                    return;
                } else {
                    System.out.println("消费成功,此时获取的数据为" + result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("FLAG = false 停止生产。。。。。。");
    }

    public void stop() {
        this.FLAG = false;
    }
}

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        ShareMySource mySource = new ShareMySource(new ArrayBlockingQueue(1));
        new Thread(() -> {
            try {
                mySource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            mySource.myConsum();
        }, "BBB").start();
        try {
            TimeUnit.SECONDS.sleep(2);
            mySource.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
