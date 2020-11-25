package com.atguigu.lock_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinlockDemo {

    AtomicReference<Thread> atomicReference= new AtomicReference<>();

    public void myLock(){

        Thread thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in");

        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnLock()");
    }


    public static void main(String[] args) {

        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinlockDemo.myUnLock();
        },"AA").start();

        new Thread(()->{
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinlockDemo.myUnLock();
        },"BB").start();

    }
}
