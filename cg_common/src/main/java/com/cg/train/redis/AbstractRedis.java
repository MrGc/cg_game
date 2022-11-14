package com.cg.train.redis;


import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.params.SortingParams;
import redis.clients.jedis.resps.Tuple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/7 11:55
 * @Version: 1.0.0
 */
public abstract class AbstractRedis implements IRedisCommand{
    @Override
    public boolean set(byte[] key, byte[] value) {
        return runBinaryCommand(key, redis -> "OK".equals(redis.set(key, value)));
    }

    @Override
    public boolean set(byte[] key, byte[] value, SetParams setParams) {
        return runBinaryCommand(key, redis -> "OK".equals(redis.set(key, value, setParams)));
    }

    @Override
    public boolean setex(byte[] key, int seconds, byte[] value) {
        return runBinaryCommand(key, redis -> "OK".equals(redis.set(key, value, SetParams.setParams().ex(seconds))));
    }

    @Override
    public byte[] get(byte[] key) {
        return runBinaryCommand(key, redis -> redis.get(key));
    }

    @Override
    public Map<byte[], byte[]> getMultiBinary(Collection<byte[]> keys) {
        if (keys == null || keys.isEmpty()) {
            return Collections.emptyMap();
        }
        if (keys.size() == 1) {// 不走多key流程
            byte[] key = keys.iterator().next();
            byte[] value = get(key);
            if (value == null) {
                return Collections.emptyMap();
            }
            return Map.of(key, value);
        }
        return runMultiBinaryCommand(keys, (jedis, jkeys) -> {
            byte[][] jKeyArr = new byte[jkeys.size()][];
            jkeys.toArray(jKeyArr);
            List<byte[]> values = jedis.mget(jKeyArr);
            return IntStream.range(0, values.size()).boxed().collect(Collectors.toMap(i -> jKeyArr[i], values::get));
        });
    }

    @Override
    public void setMultiBinary(Map<byte[], byte[]> keyValueMap) {

    }

    @Override
    public Long hsetnx(byte[] key, byte[] field, byte[] value) {
        return null;
    }

    @Override
    public byte[] hget(byte[] key, byte[] field) {
        return new byte[0];
    }

    @Override
    public Long hset(byte[] key, byte[] field, byte[] value) {
        return null;
    }

    @Override
    public Long hdel(byte[] key, byte[]... field) {
        return null;
    }

    @Override
    public Map<byte[], byte[]> hgetAll(byte[] key) {
        return null;
    }

    @Override
    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        return null;
    }

    @Override
    public Long hincrBy(byte[] key, byte[] field, long value) {
        return null;
    }

    @Override
    public Double hincrByFloat(byte[] key, byte[] field, double value) {
        return null;
    }

    @Override
    public String hmset(byte[] key, Map<byte[], byte[]> hash) {
        return null;
    }

    @Override
    public Boolean hexists(byte[] key, byte[] field) {
        return null;
    }

    @Override
    public Long hlen(byte[] key) {
        return null;
    }

    @Override
    public Set<byte[]> hkeys(byte[] key) {
        return null;
    }

    @Override
    public Collection<byte[]> hvals(byte[] key) {
        return null;
    }

    @Override
    public List<byte[]> lrange(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public Long lpush(byte[] key, byte[]... strings) {
        return null;
    }

    @Override
    public Long rpush(byte[] key, byte[]... strings) {
        return null;
    }

    @Override
    public byte[] lpop(byte[] key) {
        return new byte[0];
    }

    @Override
    public byte[] rpop(byte[] key) {
        return new byte[0];
    }

    @Override
    public byte[] lindex(byte[] key, long index) {
        return new byte[0];
    }

    @Override
    public String lset(byte[] key, long index, byte[] value) {
        return null;
    }

    @Override
    public Long lrem(byte[] key, long count, byte[] value) {
        return null;
    }

    @Override
    public Long llen(byte[] key) {
        return null;
    }

    @Override
    public String ltrim(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public boolean del(byte[] key) {
        return false;
    }

    @Override
    public Boolean exists(byte[] key) {
        return null;
    }

    @Override
    public Long persist(byte[] key) {
        return null;
    }

    @Override
    public String type(byte[] key) {
        return null;
    }

    @Override
    public Long expire(byte[] key, int seconds) {
        return null;
    }

    @Override
    public Long pexpire(byte[] key, long milliseconds) {
        return null;
    }

    @Override
    public Long expireAt(byte[] key, long unixTime) {
        return null;
    }

    @Override
    public Long pexpireAt(byte[] key, long millisecondsTimestamp) {
        return null;
    }

    @Override
    public Long ttl(byte[] key) {
        return null;
    }

    @Override
    public List<byte[]> sort(byte[] key) {
        return null;
    }

    @Override
    public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
        return null;
    }

    @Override
    public Long sadd(byte[] key, byte[]... member) {
        return null;
    }

    @Override
    public Set<byte[]> smembers(byte[] key) {
        return null;
    }

    @Override
    public Long srem(byte[] key, byte[]... member) {
        return null;
    }

    @Override
    public byte[] spop(byte[] key) {
        return new byte[0];
    }

    @Override
    public Set<byte[]> spop(byte[] key, long count) {
        return null;
    }

    @Override
    public Long scard(byte[] key) {
        return null;
    }

    @Override
    public Boolean sismember(byte[] key, byte[] member) {
        return null;
    }

    @Override
    public byte[] srandmember(byte[] key) {
        return new byte[0];
    }

    @Override
    public List<byte[]> srandmember(byte[] key, int count) {
        return null;
    }

    @Override
    public Long zadd(byte[] key, double score, byte[] member) {
        return null;
    }

    @Override
    public Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
        return null;
    }

    @Override
    public Set<byte[]> zrange(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public Long zrem(byte[] key, byte[]... member) {
        return null;
    }

    @Override
    public Double zincrby(byte[] key, double score, byte[] member) {
        return null;
    }

    @Override
    public Long zrank(byte[] key, byte[] member) {
        return null;
    }

    @Override
    public Long zrevrank(byte[] key, byte[] member) {
        return null;
    }

    @Override
    public Set<byte[]> zrevrange(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public Long zcard(byte[] key) {
        return null;
    }

    @Override
    public Double zscore(byte[] key, byte[] member) {
        return null;
    }

    @Override
    public Long zcount(byte[] key, double min, double max) {
        return null;
    }

    @Override
    public Long zcount(byte[] key, byte[] min, byte[] max) {
        return null;
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
        return null;
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
        return null;
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        return null;
    }

    @Override
    public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return null;
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
        return null;
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        return null;
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        return null;
    }

    @Override
    public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        return null;
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return null;
    }

    @Override
    public Long zremrangeByRank(byte[] key, long start, long end) {
        return null;
    }

    @Override
    public Long zremrangeByScore(byte[] key, double start, double end) {
        return null;
    }

    @Override
    public Long zremrangeByScore(byte[] key, byte[] start, byte[] end) {
        return null;
    }

    @Override
    public Long zlexcount(byte[] key, byte[] min, byte[] max) {
        return null;
    }

    @Override
    public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max) {
        return null;
    }

    @Override
    public Set<byte[]> zrangeByLex(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return null;
    }

    @Override
    public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min) {
        return null;
    }

    @Override
    public Set<byte[]> zrevrangeByLex(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return null;
    }

    @Override
    public Long zremrangeByLex(byte[] key, byte[] min, byte[] max) {
        return null;
    }

    @Override
    public Object eval(byte[] script, int keyCount, byte[]... params) {
        return null;
    }

    @Override
    public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args) {
        return null;
    }

    abstract <T> T runCommand(String key, RedisCommandExecutor executor);

    abstract <T> T runCommandWithMultiKeys(int keyCount, String[] keys, RedisCommandExecutor executor);

    abstract <T> T runMultiCommand(T userObj, Collection<String> keys, RedisMultiCommandExecutor<T> executor);

    private void runMultiCommand(Collection<String> keys, RedisMultiCommandExecutor<Void> executor) {
        runMultiCommand(null, keys, executor);
    }

    abstract <T> T runAllCommand(T userObj, RedisAllCommandExecutor<T> executor);

    private void runAllCommand(RedisCommandExecutor executor) {
        runAllCommand(null, (userObj, jedis) -> executor.execute(jedis));
    }

    abstract <T> T runBinaryCommand(byte[] key, RedisCommandExecutor executor);

    abstract <T> T runMultiBinaryCommand(Collection<byte[]> keys, RedisMultiBinaryCommandExecutor<T> executor);

    abstract <T> T runBinaryCommandWithMultiKeys(int keyCount, byte[][] keys, RedisCommandExecutor executor);
}
