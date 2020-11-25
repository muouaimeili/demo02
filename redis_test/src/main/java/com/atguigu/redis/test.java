package com.atguigu.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class test {
    public static void main(String[] args) {
       Jedis jedis = new Jedis("192.168.50.135",6379);

        Transaction transaction = jedis.multi();
        transaction.set("k1","v1");
        transaction.set("k2","v2");
        transaction.set("k3","v3");
        transaction.exec();
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.ping());
    }
}
