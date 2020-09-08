package com.company.concurrent;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2,new PrioExecut());
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {

                }
                System.out.println(Thread.currentThread().getName()+"正在等待...");
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {

        }
        System.out.println(Thread.currentThread().getName()+"正在等待...");
        System.out.println("人够了，出发吧 当前有 "+c.getParties()+" 个人参与比赛");
    }
}

class PrioExecut implements Runnable{
    @Override
    public void run() {
        System.out.println("我会先跑5秒，不管你信不信！");
    }
}