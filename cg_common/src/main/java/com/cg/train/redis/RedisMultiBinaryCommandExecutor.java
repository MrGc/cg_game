package com.cg.train.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/7 16:50
 * @Version: 1.0.0
 */
public interface RedisMultiBinaryCommandExecutor<T> {
    T execute(Jedis jedis, Set<byte[]> keys) throws Exception;
}
