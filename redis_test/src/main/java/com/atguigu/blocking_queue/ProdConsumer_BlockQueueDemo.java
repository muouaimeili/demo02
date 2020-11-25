package com.atguigu.blocking_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean flag=true;//默认开启，进行生产+消费
    private AtomicInteger atomicInteger=new AtomicInteger();

    BlockingQueue<String> blockingQueue=null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProduct() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (flag){
            data=atomicInteger.incrementAndGet()+"";
            retValue=blockingQueue.offer(data,1L, TimeUnit.SECONDS);
            if (retValue){
                System.out.println(Thread.currentThread().getName()+data+"插入队列成功");
            }else {
                System.out.println(Thread.currentThread().getName()+data+"插入队列失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"叫停，生产活动结束，标志位为false");

    }
    public void consumer() throws InterruptedException {
        String result=null;

        while (flag){
            result=blockingQueue.poll(1L,TimeUnit.SECONDS);
            if (null!=result){

                System.out.println(Thread.currentThread().getName()+"消费队列成功"+result);
            }else {
                System.out.println("还没有值呢等会");
                continue;
            }
        }
        System.out.println(Thread.currentThread().getName()+"消费者线程停止工作，标志位为："+flag);


    }
    public void stop(){
        this.flag=false;
        System.out.println(Thread.currentThread().getName()+"下达命令，停止工作");

    }
}

/**
 * volatile/CAS/atomicInteger/BlockingQueue/线程交互/原子引用
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {

        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));

        new Thread(()->{

            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            try {
                myResource.myProduct();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            try {
                myResource.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者").start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("5秒结束，全部停止");
        myResource.stop();

    }
}
