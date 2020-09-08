package com.company.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class ForkJoinPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(1,100));
        System.out.println("[" + Thread.currentThread().getId() + "] await tasks.");
        pool.awaitTermination(2, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        pool.shutdown();
    }
}

class PrintTask extends RecursiveAction {
    private int start;
    private int end;
    private int num;
    final int MAX = 50;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start < 50){
            for(int i = start;i <= end; i++){
                num += i;
            }
            System.out.println("[" + Thread.currentThread().getId() + "] 当前任务结果为： "+num);
        }else{
            int mid = (end + start)/2;
            PrintTask left = new PrintTask(start,mid);
            PrintTask right = new PrintTask(mid+1,end);
            left.fork();
            right.fork();
            System.out.println("[" + Thread.currentThread().getId() + "] fork 2 tasks.");
        }
    }
}