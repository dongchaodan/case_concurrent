package com.arraylist线程不安全;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ContainerNotSafeDemo {

    public static void main(String[] args) throws InterruptedException {
//         java.util.ConcurrentModificationException  --线程修改异常
//                List list = new ArrayList();
//                List list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
                List list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
//                list.forEach(System.out::println);
            }, String.valueOf(i)).start();
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(list);

//        Set<String> set = new HashSet<>();
//        set.add("1");
//        HashMap hashMap = new HashMap();
//        hashMap.put("","");
//        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<String,String>();
//        concurrentHashMap.put("","");
    }

}
