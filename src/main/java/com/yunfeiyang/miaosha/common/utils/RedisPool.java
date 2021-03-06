package com.yunfeiyang.miaosha.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Gu Zhiqiang on 2019-11-11
 */
@Slf4j
@Component
public class RedisPool {

    private static JedisPool pool;

    private static Integer maxTotal = 300;

    private static Integer maxIdle = 100;

    private static Integer maxWait = 10000;

    private static Boolean testOnBorrow = true;

    private static String redisIP= "120.92.122.136";

    private static Integer redisPort = 7777;

    private static String password = "Gzq123456";

    static {
        initPool();
    }

    // initPool init the redis connection pool
    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setBlockWhenExhausted(true);
        config.setMaxWaitMillis(maxWait);
        pool = new JedisPool(config, redisIP, redisPort, 1000 * 2,password);
        log.info("success to get redis connect pool");
    }

    // getJedis return a redis connection
    public static Jedis getJedis() {
        return pool.getResource();
    }

    // jedisPoolClose close a redis connection
    public static void jedisPoolClose(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        getJedis();
    }
}
