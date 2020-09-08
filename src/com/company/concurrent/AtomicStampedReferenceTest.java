package com.company.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        AtomicInteger atomicInt = new AtomicInteger(100);
        atomicInt.compareAndSet(100, 101);
        atomicInt.compareAndSet(101, 100);
        System.out.println("new value = " + atomicInt.get());
        boolean result1 = atomicInt.compareAndSet(100, 101);
        System.out.println(result1); // result:true

        AtomicInteger v1 = new AtomicInteger(100);
        AtomicInteger v2 = new AtomicInteger(101);
        AtomicStampedReference<AtomicInteger> stampedRef = new AtomicStampedReference<AtomicInteger>(v1, 0);

        int stamp = stampedRef.getStamp();
        stampedRef.compareAndSet(v1, v2, stampedRef.getStamp(),
                stampedRef.getStamp() + 1);

        System.out.println(stampedRef.getStamp());
        stampedRef.compareAndSet(v2, v1, stampedRef.getStamp(),
                stampedRef.getStamp() + 1);

        System.out.println("new value = " + stampedRef.getReference());
        boolean result2 = stampedRef.compareAndSet(v1, v2, stamp, stamp + 1);
        System.out.println(result2); // result:false
    }
}