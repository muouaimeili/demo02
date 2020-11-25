package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestPool {
    public static void main(String[] args) {
        JedisPool jedisPoolInstance = JedisPoolUtil.getJedisPoolInstance();
        Jedis jedis = jedisPoolInstance.getResource();
        jedis.set("k4","v4");
        jedis.set("k5","v5");
        jedis.set("k6","v6");
        jedis.set("k7","v7");
        System.out.println(jedis.keys("*"));

    }
}
