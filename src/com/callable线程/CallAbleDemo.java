package com.callable线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallAbleDemo implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println("---Callable---");
        return 11111;
    }
}


public class CallAbleDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallAbleDemo myCallAbleDemo= new MyCallAbleDemo();
        FutureTask futureTask = new FutureTask(myCallAbleDemo);
        Thread thread = new Thread(futureTask,"A");
        thread.start();
        System.out.println(thread.getThreadGroup());
        System.out.println("==============="+futureTask.get());
    }
}
