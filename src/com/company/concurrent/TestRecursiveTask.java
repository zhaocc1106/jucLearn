package com.company.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class TestRecursiveTask {

    public static void main(String[] args) {
        Integer result = 0;
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> future = pool.submit(new SumTask(30));
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("[" + Thread.currentThread().getId() + "] ===========================" + result);
    }
}

class SumTask extends RecursiveTask<Integer> {
    int num;

    public SumTask(int num) {
        this.num = num;
    }

    @Override
    protected Integer compute() {
        if (num <= 20) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + Thread.currentThread().getId() + "] 生产完成" + num + "个产品");
            return num;
        } else {
            SumTask task1 = new SumTask(20);
            SumTask task2 = new SumTask(num - 20);
            task1.fork();
            task2.fork();
            System.out.println("[" + Thread.currentThread().getId() + "] fork 2 task.");
            return task1.join() + task2.join();
        }
    }
}