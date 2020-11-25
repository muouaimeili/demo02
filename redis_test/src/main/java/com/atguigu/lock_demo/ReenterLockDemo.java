package com.atguigu.lock_demo;

public class ReenterLockDemo {
    public static void main(String[] args) {

        Phone phone = new Phone();
        new Thread(()->{
            phone.sendSMS();
        },"t1").start();

        new Thread(()->{
            phone.sendSMS();
        },"t1").start();
    }
}

class Phone{

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getId()+"\t invoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getId()+"\t ===invoked sendEmail");
    }
}