package com.atguigu.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        for (int i = 0; i <100 ; i++) {
//            executorService1.execute(new Task());
//            executorService1.execute(new Task());
            executorService.execute(new Task());
        }
        System.out.println("开始停止线程");
        scheduledExecutorService.shutdown();


        System.out.println(Thread.currentThread().getName()+"结束进程");
    }
}
