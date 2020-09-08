package com.company.concurrent;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestConcurrentSkipListMap {
    // fixme: maps是HashMap对象时，程序会出错。
    private static Map<String, Long> maps = new ConcurrentSkipListMap<>();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            executor.execute(new TestMap("aa"));
        }
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Timeout.");
            executor.shutdown();
        }

        printAll();
    }

    private static void printAll() {
        String key = null;
        Long value = null;
        Iterator keys = maps.keySet().iterator();
        while (keys.hasNext()) {
            key = (String) keys.next();
            System.out.print("[" + key + "]");
        }
        System.out.println();

        Iterator values = maps.values().iterator();
        while (values.hasNext()) {
            value = (Long) values.next();
            System.out.print("[" + value + "]");
        }
        System.out.println();
    }

    private static class TestMap extends Thread {
        TestMap(String name) {
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            long tid = Thread.currentThread().getId();
            maps.put(name, tid);
//            printAll();
        }
    }
}
