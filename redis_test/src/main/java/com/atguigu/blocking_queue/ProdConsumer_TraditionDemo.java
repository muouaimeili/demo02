package com.atguigu.blocking_queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public void increment(){
        //1 判断
        try {
            lock.lock();
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println("生产1个"+number);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public void decrement(){

        try {
            lock.lock();
            while (number==0){
                condition.await();
            }
            number--;
            System.out.println("消费1个"+number);
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 题目：一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 * 1 线程 操作 资源类
 * 2 判断 干活 通知
 * 3 防止虚假唤醒线程
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {

        ShareData shareData=new ShareData();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shareData.increment();

            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shareData.decrement();
            }
        },"B").start();
    }
}
