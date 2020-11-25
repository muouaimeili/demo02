package com.atguigu.lock_demo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 完成任务，离开");
                countDownLatch.countDown();
                },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start(); }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t =====最后完成任务，离开");

    }

    public static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 完成任务，离开");
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t =====最后完成任务，离开");
    }
}
