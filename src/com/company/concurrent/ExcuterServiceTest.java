package com.company.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Created by zhaochaochao@baidu.com on 2019/4/2.
 */
public class ExcuterServiceTest {

    private static void cachedThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            try {
//                Thread.sleep(index * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        System.out.println(index);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        cachedThreadPool.shutdown();
        try {
            cachedThreadPool.awaitTermination(1, TimeUnit.SECONDS);
            System.out.println("await timeout.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void fixedThreadPoolTest() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {


                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void scheduledThreadPoolTest1() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }

    private static void scheduledThreadPoolTest2() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    private static void singleThreadPoolTtest() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
//            singleThreadExecutor.shutdownNow();
            singleThreadExecutor.execute(new Runnable() {

                @Override
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
//        cachedThreadPoolTest();
//        fixedThreadPoolTest();
//        scheduledThreadPoolTest1();
//        scheduledThreadPoolTest2();
        singleThreadPoolTtest();
    }
}
