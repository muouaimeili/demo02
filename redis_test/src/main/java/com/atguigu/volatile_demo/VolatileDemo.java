package com.atguigu.volatile_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

//        seeOkByVolatile(myData);
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();

        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"int finally number value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+"atomicInteger finally number value:"+myData.atomicInteger);

    }

//    volatile保证可见性
    public static void seeOkByVolatile(MyData myData) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"updated number value"+myData.number);
        },"AAA").start();

//        第二个线程就是我们的main线程
        while (myData.number==0){
//            main线程就一直再这里等待，直到number值不再等于0

        }
        System.out.println(Thread.currentThread().getName()+"mission is over");
    }
}

class MyData{
    volatile int number=0;
    AtomicInteger atomicInteger=new AtomicInteger();

    public void addTo60(){
        this.number=60;
    }

    public void addPlusPlus(){
        number++;
    }
    public void addAtomic(){

        atomicInteger.getAndIncrement();
    }
}
