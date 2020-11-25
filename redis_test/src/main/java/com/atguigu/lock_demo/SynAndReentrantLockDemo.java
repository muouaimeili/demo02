package com.atguigu.lock_demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynAndReentrantLockDemo {
    /*
    * 题目：多线程之间按顺序调用，实现A-b-c三个线程启动，要求如下：
    * A打印5次，B打印10次，C打印15次
    * 紧接着
    * A打印5次，B打印10次，C打印15次
    * 。。。。
    * 来5轮
    *
    * */
    public static void main(String[] args) {
        ShareResoure shareResoure = new ShareResoure();
        new Thread(()-> {
            for (int i = 1; i <= 5; i++) {
                shareResoure.printA();
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 1; i <= 5 ; i++) {
                shareResoure.printB();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 1; i <= 5 ; i++) {
                shareResoure.printC();
            }
        },"CCC").start();
    }
}

class ShareResoure{

    private volatile Integer num=1;

    private Lock lock=new ReentrantLock();

    Condition c1=lock.newCondition();
    Condition c2=lock.newCondition();
    Condition c3=lock.newCondition();

    public void printA(){
        lock.lock();
        try {
            while (num!=1){
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            num=2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while (num!=2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            num=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while (num!=3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+i);
            }
            num=1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}