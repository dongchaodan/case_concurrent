package com.blockingqueue阻塞队列;


import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1、队列
 *
 * 2、阻塞队列
 * 2.1 阻塞队列有没有优点
 *
 * 2.2 不得不阻塞，你如何管理\
 *
 * (1)ArrayBlockingQueue :
 *      内部基于数组实现的有界阻塞队列，按照先进先出的原则对数组进行排序，支持公平锁和非公平锁，
 *   对于生产者和消费者取出数据使用的是同一把锁，所以二者不能真正的并行运行。同时由于数组中存放
 *   的直接是放入的数据，所以不会产生额外的对象实例。
 *
 * (2)LinkedBlockingQueue:
 *      内部基于链表实现的有界阻塞队列，按照先进先出的顺序对元素存取，内部使用非公平锁
 *    对于生产者和消费者使用的是两把不同的锁所以生产者和消费者可以同步进行。由于是使用的链表结构，
 *    所以会生成Node节点对象实例，销毁的时候需要额外处理。
 *
 *（3）DelayQueue：
 *       内部基于非线程安全的优先队列实现的无界阻塞队列，内部使用非公平锁。
 *    DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。
 *
 *（4）PriorityQueue：
 *       内部基于堆实现的无界阻塞队列（可进行扩容，默认11），内部使用非公平锁。
 *    一个支持线程优先级排序的无界队列，默认自然序进行排序，也可以自定义实现compareTo()方法来指定
 *    元素排序规则，不能保证同优先级元素的顺序。该队列不会阻塞生产者产生数据，当队列中没有数据时，
 *    会阻塞消费者。所以如果生产者的速度一旦超过消费者，会快速消耗内存资源。
 *
 *（5）SynchronousQueue：
 *      内部是基于无缓冲并且无界的等待队列，但是由于该Queue本身的特性，非公平锁,
 *    在某次添加元素后必须等待其他线程取走后才能继续添加；可以认为SynchronousQueue是一个缓存值为1的阻塞队列；
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        BlockingQueue
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(3);
        queue.put(1);
//        queue.offer(1,2L, TimeUnit.SECONDS);
//        queue.remove();
//        queue.take();
//        queue.add(1);
//        queue.offer(1);
        queue.poll();
        System.out.println(queue.peek());
        queue.peek();
//        queue.element();
//        System.out.println(offer1+"--"+offer);
    }
}
