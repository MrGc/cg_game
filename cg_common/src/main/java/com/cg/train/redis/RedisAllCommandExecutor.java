package com.cg.train.redis;

import redis.clients.jedis.Jedis;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/7 16:43
 * @Version: 1.0.0
 */
public interface RedisAllCommandExecutor<T> {
    T execute(T userObj, Jedis jedisCommands) throws Exception;
}
