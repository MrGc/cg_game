package com.cg.train.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/7 16:50
 * @Version: 1.0.0
 */
public interface RedisMultiCommandExecutor<T> {
    T execute(T userObj, Jedis jedisCommands, Set<String> keys) throws Exception;
}
