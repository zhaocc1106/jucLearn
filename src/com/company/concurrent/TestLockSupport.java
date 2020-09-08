package com.company.concurrent;

import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {

    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run() {
            synchronized (u) {
                System.out.println("in " + getName() + " park begin.");
                LockSupport.park();
                System.out.println("in " + getName() + " park end.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(2000);
        t2.start();
        LockSupport.unpark(t1);
        System.out.println("Unpark t1");
        LockSupport.unpark(t2);
        System.out.println("Unpark t2");
        t1.join();
        t2.join();
    }
}
