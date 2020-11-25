package com.atguigu.lock_demo;

public class DeadLockDemo2 {
    private  static Object lockA = new Object();
    private  static Object lockB = new Object();

    public static void main(String[] args) {



        new Thread(()->{
            synchronized (lockA){
                System.out.println(Thread.currentThread().getName()+"我持有lockA的锁开始");
                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName()+"我持有lockB的锁开始");
                    try {
                        System.out.println(Thread.currentThread().getName()+"我释放lockA的锁开始");
                        lockA.wait();
                        System.out.println(Thread.currentThread().getName()+"我释放lockB的锁开始");
                        lockB.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"我拿到了lockA的锁结束");
                }
            }
        },"AAA").start();



        new Thread(()->{
            synchronized (lockA){
                System.out.println(Thread.currentThread().getName()+"我持有lockA的锁开始");
                lockA.notifyAll();
                System.out.println(Thread.currentThread().getName()+"我唤醒lockA的锁开始");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB){
                    System.out.println(Thread.currentThread().getName()+"我持有lockB的锁开始");
                    try {
                        System.out.println(Thread.currentThread().getName()+"我释放lockB的锁开始");
                        lockB.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"我拿到lockB的锁结束");


                }


            }
        },"BBB").start();

    }

}

