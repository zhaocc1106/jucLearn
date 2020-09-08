package com.company.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCopyOnWriteArrayList {
    // fixme: list是ArrayList对象时，程序会出错。
//    private static List<String> list = new ArrayList<String>();
    private static List<String> list = new CopyOnWriteArrayList<String>();
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);
        for(int i=0;i<100;i++){
            executor.execute(new TestList("aa"));
        }
    }

    private static void printAll() {
        String value = null;
        Iterator iter = list.iterator();
        while(iter.hasNext()) {
            value = (String)iter.next();
            System.out.print(value+", ");
        }
        System.out.println();
    }

    private static class TestList extends Thread {
        TestList(String name) {
            super(name);
        }
        @Override
        public void run() {
            String val = Thread.currentThread().getName();
            list.add(val);
            printAll();
        }
    }
}