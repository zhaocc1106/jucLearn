package com.company.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestSemaphore {
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int j = 0;
            executorService.submit(new A("car" + (j++), semaphore), "Thread" + (j++));
            //new Thread(new A("car"+(j++),semaphore),"Thread"+(j++)).start();
            if (i == 5) {
                try {
                    Thread.sleep(1000);
                    System.out.println("最后还有" + semaphore.availablePermits() + "个许可可用");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("最后还有" + semaphore.availablePermits() + "个许可可用");
    }

}

class A implements Runnable {
    String carName;
    private Semaphore semaphore;

    public A(String carName, Semaphore semaphore) {
        this.carName = carName;
        this.semaphore = semaphore;
    }

    public void getWay() {
        System.out.println("this car is get the way " + Thread.currentThread().getName());
    }

    public void run() {
        try {
            if (semaphore.availablePermits() > 0) {
                semaphore.acquire();
                getWay();
                semaphore.release();
            } else {
                System.out.println("请等待========");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}