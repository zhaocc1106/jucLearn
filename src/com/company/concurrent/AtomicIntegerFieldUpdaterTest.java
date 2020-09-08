package com.company.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * allscore 如果和 score 的结果相同则说明线程是安全的
 */
public class AtomicIntegerFieldUpdaterTest {
    public final static AtomicIntegerFieldUpdater<AA> vv = AtomicIntegerFieldUpdater.newUpdater(AA.class, "score"); //newUpdater方法为AA类中的score 对象创造一个更新器

    public static AtomicInteger allscore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final AA stu = new AA();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread() {
                @Override
                public void run() {
                    if (true) {
                        vv.incrementAndGet(stu);
                        allscore.incrementAndGet();
                    }
                }
            };
            t[i].start();
        }
        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }
        System.out.println("score=" + stu.getScore());
        System.out.println("allscore=" + allscore);
    }
}

class AA {
    int id;
    volatile int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}