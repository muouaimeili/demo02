package com.atguigu.callable_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("====come in Callable");
        TimeUnit.SECONDS.sleep(2);
        return 1024;
    }
}
