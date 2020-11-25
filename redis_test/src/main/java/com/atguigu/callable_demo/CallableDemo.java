package com.atguigu.callable_demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //    两个线程，一个main，一个AAFutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread t1=new Thread(futureTask,"AA");
        t1.start();
        System.out.println(Thread.currentThread().getName()+"******");

        int result01=100;
//        要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成
        int result02=futureTask.get();
        System.out.println("=====result:"+(result01+result02));


    }
}
