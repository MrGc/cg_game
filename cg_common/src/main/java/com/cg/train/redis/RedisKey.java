package com.cg.train.redis;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/7 11:47
 * @Version: 1.0.0
 */
public interface RedisKey {
    /**
     * 用户表key
     * e_表名_用户id
     */
    String USER_ENTRY_REDIS_KEY = "e_%s_%s";
}
