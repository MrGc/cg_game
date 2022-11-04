package com.cg.train.redis;

import redis.clients.jedis.params.SortingParams;
import redis.clients.jedis.resps.Tuple;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/3 11:30
 * @Version: 1.0.0
 */
public interface IRedisCommand {
    /**
     * redis 字符串操作
     * 设置值
     * @param key 键
     * @param value 值
     * @return boolean  命令操作成功完成时才返回 OK
     */
    boolean set(byte[] key, byte[] value);

    /**
     * redis 字符串操作
     * 设置值
     * @param key 键
     * @param value 值
     * @param nxxx  nx-只在键不存在时， 才对键进行设置操作； <b>SET key value NX</b>
     *              xx-只在键已经存在时， 才对键进行设置操作。 <b>SET key value XX</b>
     * @param expx  ex-将过期时间设置为seconds 秒； <b>SET key value EX seconds</b>
     *              px-将过期时间设置为milliseconds 毫秒； <b>SET key vaule PX milliseconds</b>
     * @param time 过期时间
     * @return boolean 命令操作成功完成时才返回 OK
     */
    boolean set(byte[] key, byte[] value,  byte[] nxxx, byte[] expx, int time);

    /**
     * redis 字符串操作
     * 设置值
     * @param key 键
     * @param seconds 过期时间(秒)
     * @param value 值
     * @return boolean 命令操作成功完成时才返回 OK
     */
    boolean setex(byte[] key, int seconds, byte[] value);

    /**
     * redis 字符串操作
     * 获取值
     * @param key 键
     * @return byte[] 值
     */
    byte[] get(byte[] key);

    /**
     * redis 字符串操作
     * 返回多个值，如果key不存在，返回nil
     * @param keys 键集合
     * @return Map<键, 值>
     */
    Map<byte[], byte[]> getMultiBinary(Collection<byte[]> keys);

    /**
     * redis 字符串操作
     * 设置多个key-value 对。
     * @param keyValueMap key-value 键值对集合
     */
    void setMultiBinary(Map<byte[], byte[]> keyValueMap);

    /**
     * redis 哈希(Hash)操作
     * 设置值
     * 只有在字段 field 不存在时，设置哈希表字段的值。
     * @param key 键
     * @param field 字段
     * @param value 值
     * @return 成功返回1，否则0
     */
    Long hsetnx(byte[] key, byte[] field, byte[] value);

    /**
     * redis 哈希(Hash)操作
     * 获取值
     * @param key 键
     * @param field 字段
     * @return byte[] 值
     */
    byte[] hget(byte[] key, byte[] field);

    /**
     * redis 哈希(Hash)操作
     * 设置值
     * @param key 键
     * @param field 字段
     * @param value 值
     * @return 成功返回1，否则0
     */
    Long hset(byte[] key, byte[] field, byte[] value);

    /**
     * redis 哈希(Hash)操作
     * 删除指定字段
     * @param key 键
     * @param field 字段
     * @return 成功返回1，否则0
     */
    Long hdel(byte[] key, byte[]... field);

    /**
     * redis 哈希(Hash)操作
     * 获取指定的键的所有字段-值集合
     * @param key 键
     * @return Map<字段, 值>
     */
    Map<byte[], byte[]> hgetAll(byte[] key);

    /**
     * redis 哈希(Hash)操作
     * 获取指定字段集合的值
     * @param key 键
     * @param fields 字段集合
     * @return 值集合
     */
    List<byte[]> hmget(byte[] key, byte[]... fields);

    /**
     * redis 哈希(Hash)操作
     * 给指定的字段增加值
     * @param key 键
     * @param field 字段
     * @param value 增加的值
     * @return 修改之后的值
     */
    Long hincrBy(byte[] key, byte[] field, long value);

    /**
     * redis 哈希(Hash)操作
     * 给指定的字段增加浮点值
     * @param key 键
     * @param field 字段
     * @param value 增加的值
     * @return 修改之后的值
     */
    Double hincrByFloat(byte[] key, byte[] field, double value);

    /**
     * redis 哈希(Hash)操作
     * 修改指定的字段的值
     * @param key 键
     * @param hash 字段-值 集合
     * @return 返回 ok 因为 HMSET 不会失败
     */
    String hmset(byte[] key, Map<byte[], byte[]> hash);

    /**
     * redis 哈希(Hash)操作
     * 指定的字段是否存在
     * @param key 键
     * @param field 字段
     * @return 存在返回true，不存在返回false
     */
    Boolean hexists(byte[] key, byte[] field);

    /**
     * redis 哈希(Hash)操作
     * 获取指定键的字段长度
     * @param key 键
     * @return 指定键的字段长度
     */
    Long hlen(byte[] key);

    /**
     * redis 哈希(Hash)操作
     * 获取指定键的所有字段名称
     * @param key 键
     * @return 字段名称集合
     */
    Set<byte[]> hkeys(byte[] key);

    /**
     * redis 哈希(Hash)操作
     * 获取指定键的所有字段值
     * @param key 键
     * @return 字段值集合
     */
    Collection<byte[]> hvals(byte[] key);

    /**
     * redis 列表(List)操作
     * 获取指定范围内的元素的列表
     * @param key 键
     * @param start 开始
     * @param end 结束
     * @return 指定范围内的元素的列表
     */
    List<byte[]> lrange(byte[] key, long start, long end);

    /**
     * redis 列表(List)操作
     * 将元素列表添加到键的头部，
     * @param key 键
     * @param strings 元素列表
     * @return 总的元素个数
     */
    Long lpush(byte[] key, byte[]... strings);

    /**
     * redis 列表(List)操作
     * 将元素集合添加到键的尾部，
     * @param key 键
     * @param strings 元素列表
     * @return 总的元素个数
     */
    Long rpush(byte[] key, byte[]... strings);

    /**
     * redis 列表(List)操作
     * 删除第一个元素
     * @param key 键
     * @return 返回删除的元素
     */
    byte[] lpop(byte[] key);

    /**
     * redis 列表(List)操作
     * 删除最后个元素
     * @param key 键
     * @return 返回删除的元素
     */
    byte[] rpop(byte[] key);

    /**
     * redis 列表(List)操作
     * 通过索引获取列表中的元素
     * @param key 键
     * @param index 索引
     * @return 元素值
     */
    byte[] lindex(byte[] key, long index);

    /**
     * redis 列表(List)操作
     * 通过索引设置列表元素的值
     * @param key 键
     * @param index 索引
     * @param value 元素值
     * @return 成功返回 OK
     */
    String lset(byte[] key, long index, byte[] value);

    /**
     * redis 列表(List)操作
     * 移除count个值为value的元素
     * <b>
     *     count > 0 从表头开始搜索
     *     count < 0 从表尾开始搜索
     *     count = 0 移除表中所有的value相等的值
     * </b>
     * @param key 键
     * @param count 数量
     * @param value 元素
     * @return 被移除元素的数量
     */
    Long lrem(byte[] key, long count, byte[] value);

    /**
     * redis 列表(List)操作
     * @param key 键
     * @return 元素长度
     */
    Long llen(byte[] key);

    /**
     * redis 列表(List)操作
     * 截取现有列表
     * @param key 键
     * @param start 开始的索引
     * @param end 结束的索引
     * @return 成功返回 OK
     */
    String ltrim(byte[] key, long start, long end);

    /**
     * 删除键
     * @param key 键
     * @return 删除是否成功
     */
    boolean del(byte[] key);

    /**
     * 检查指定的key是否存在
     * @param key 键
     * @return 存在返回true，不存在返回false
     */
    Boolean exists(byte[] key);

    /**
     * 移除key的过期时间，key将持久保持
     * @param key 键
     * @return 成功返回1，否则0
     */
    Long persist(byte[] key);

    /**
     * 返回key所存储的值的类型
     * @param key 键
     * @return none-key不存在；string-字符串；list-列表，set-集合，zset-有序集，hash-哈希表
     */
    String type(byte[] key);

    /**
     * 为指定的key设置过期时间，以秒计。
     * @param key 键
     * @param seconds 多少秒之后过期
     * @return 成功返回 1
     */
    Long expire(byte[] key, int seconds);

    /**
     * 为指定的key设置过期时间，以毫秒计。
     * @param key 键
     * @param milliseconds 多少毫秒之后过期
     * @return 成功返回 1
     */
    Long pexpire(byte[] key, long milliseconds);

    /**
     * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。
     * @param key 键
     * @param unixTime 过期时间戳
     * @return 成功返回 1
     */
    Long expireAt(byte[] key, long unixTime);

    /**
     * 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计
     * @param key 键
     * @param millisecondsTimestamp 过期时间戳
     * @return 成功返回 1
     */
    Long pexpireAt(byte[] key, long millisecondsTimestamp);

    /**
     * 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。
     * @param key 键
     * @return 剩余时间
     */
    Long ttl(byte[] key);

    /**
     * 对key的值排序
     * @param key 键
     * @return 返回排序好的值的集合，不影响redis数据
     */
    List<byte[]> sort(byte[] key);

    /**
     * 对key的值排序
     * @param key 键
     * @param sortingParameters 排序参数
     * @return 返回排序好的值的集合，不影响redis数据
     */
    List<byte[]> sort(byte[] key, SortingParams sortingParameters);

    /**
     * Redis 集合(Set)操作
     * 向集合添加一个或多个成员
     * @param key 键
     * @param member 成员集合
     * @return 如果有一个新成员添加成功返回1，否则返回0
     */
    Long sadd(byte[] key, byte[]... member);

    /**
     * Redis 集合(Set)操作
     * 返回集合中的所有成员
     * @param key 键
     * @return 返回成员集合
     */
    Set<byte[]> smembers(byte[] key);

    /**
     * Redis 集合(Set)操作
     * 移除集合中一个或多个成员
     * @param key 键
     * @param member 成员集合
     * @return 如果有一个新成员移除成功返回1，否则返回0
     */
    Long srem(byte[] key, byte[]... member);

    /**
     * Redis 集合(Set)操作
     * 移除并返回集合中的一个随机成员
     * @param key 键
     * @return 返回删除的成员
     */
    byte[] spop(byte[] key);

    /**
     * Redis 集合(Set)操作
     * 移除并返回集合中的count个随机成员
     * @param key 键
     * @param count 移除的成员个数
     * @return 移除的成员
     */
    Set<byte[]> spop(byte[] key, long count);

    /**
     * Redis 集合(Set)操作
     * 获取集合的成员数
     * @param key 键
     * @return 成员个数
     */
    Long scard(byte[] key);

    /**
     * Redis 集合(Set)操作
     * 判断 member 是否是集合 key 的成员
     * @param key 键
     * @param member 成员
     * @return 是否成功
     */
    Boolean sismember(byte[] key, byte[] member);

    /**
     * Redis 集合(Set)操作
     * 返回集合中一个随机成员
     * @param key 键
     * @return 成员
     */
    byte[] srandmember(byte[] key);

    /**
     * Redis 集合(Set)操作
     * 返回集合中多个随机成员
     * @param key 键
     * @return 成员集合
     */
    List<byte[]> srandmember(byte[] key, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 添加成员
     * @param key 键
     * @param score 分数
     * @param member 成员
     * @return 成功返回1，否则返回0
     */
    Long zadd(byte[] key, double score, byte[] member);

    /**
     * Redis 有序集合(sorted set)操作
     * 批量添加成员
     * @param key 键
     * @param scoreMembers 成员-分数集合
     * @return 成功返回1，否则返回0
     */
    Long zadd(byte[] key, Map<byte[], Double> scoreMembers);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过索引区间返回有序集合指定区间内的成员
     * @param key 键
     * @param start 开始的索引
     * @param end   结束的索引
     * @return 成员列表
     */
    Set<byte[]> zrange(byte[] key, long start, long end);

    /**
     * Redis 有序集合(sorted set)操作
     * 移除有序集合中的一个或多个成员
     * @param key 键
     * @param member 成员集合
     * @return 成功返回1，否则返回0
     */
    Long zrem(byte[] key, byte[]... member);

    /**
     * Redis 有序集合(sorted set)操作
     * 有序集合中对指定成员的分数加上增量 increment
     * @param key 键
     * @param score 分数
     * @param member 成员
     * @return 新的分数
     */
    Double zincrby(byte[] key, double score, byte[] member);

    /**
     * Redis 有序集合(sorted set)操作
     * 返回有序集合中指定成员的索引
     * @param key 键
     * @param member 成员
     * @return 返回成员的排名
     */
    Long zrank(byte[] key, byte[] member);

    /**
     * Redis 有序集合(sorted set)操作
     * 返回有序集合中指定成员的索引 降序
     * @param key 键
     * @param member 成员
     * @return 返回成员的排名 降序
     */
    Long zrevrank(byte[] key, byte[] member);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过索引区间返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param start 开始的索引
     * @param end 结束的索引
     * @return 成员集合
     */
    Set<byte[]> zrevrange(byte[] key, long start, long end);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过索引区间返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param start 开始的索引
     * @param end 结束的索引
     * @return 成员集合 （成员，分数）
     */
    Set<Tuple> zrangeWithScores(byte[] key, long start, long end);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过索引区间返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param start 开始的索引
     * @param end 结束的索引
     * @return 成员集合 成员，分数）
     */
    Set<Tuple> zrevrangeWithScores(byte[] key, long start, long end);

    /**
     * Redis 有序集合(sorted set)操作
     * 获取有序集合的成员数
     * @param key 键
     * @return 成员数
     */
    Long zcard(byte[] key);

    /**
     * Redis 有序集合(sorted set)操作
     * 返回有序集中，成员的分数值
     * @param key 键
     * @param member 成员
     * @return 分数
     */
    Double zscore(byte[] key, byte[] member);

    /**
     * Redis 有序集合(sorted set)操作
     * 计算在有序集合中指定区间分数的成员数
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员数
     */
    Long zcount(byte[] key, double min, double max);

    /**
     * Redis 有序集合(sorted set)操作
     * 计算在有序集合中指定区间分数的成员数
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员数
     */
    Long zcount(byte[] key, byte[] min, byte[] max);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合
     */
    Set<byte[]> zrangeByScore(byte[] key, double min, double max);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合
     */
    Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param count 个数
     * @return 成员集合
     */
    Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param count 个数
     * @return 成员集合
     */
    Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return （成员，分数）集合
     */
    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return （成员，分数）集合
     */
    Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param count 个数
     * @return （成员，分数）集合
     */
    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param count 个数
     * @return （成员，分数）集合
     */
    Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合 降序
     */
    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合 降序
     */
    Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param count 个数
     * @return 成员集合 降序
     */
    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param count 个数
     * @return 成员集合 降序
     */
    Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count);
    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return （成员，分数）集合 降序
     */
    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return （成员，分数）集合 降序
     */
    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param max 最大分数
     * @param min 最小分数
     * @param offset 偏移量
     * @param count 个数
     * @return （成员，分数）集合 降序
     */
    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 通过分数返回有序集合指定区间内的成员 降序
     * @param key 键
     * @param max 最大分数
     * @param min 最小分数
     * @param offset 偏移量
     * @param count 个数
     * @return （成员，分数）集合 降序
     */
    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 移除有序集合中给定的排名区间的所有成员
     * @param key 键
     * @param start 开始排名索引
     * @param end   结束的排名索引
     * @return 成功删除的成员个数
     */
    Long zremrangeByRank(byte[] key, long start, long end);

    /**
     * Redis 有序集合(sorted set)操作
     * 移除有序集合中给定的分数区间的所有成员
     * @param key 键
     * @param start 开始分数索引
     * @param end   结束分数索引
     * @return 成功删除的成员个数
     */
    Long zremrangeByScore(byte[] key, double start, double end);

    /**
     * Redis 有序集合(sorted set)操作
     * 移除有序集合中给定的分数区间的所有成员
     * @param key 键
     * @param start 开始分数索引
     * @param end   结束分数索引
     * @return 成功删除的成员个数
     */
    Long zremrangeByScore(byte[] key, byte[] start, byte[] end);

    /**
     * Redis 有序集合(sorted set)操作
     * 在有序集合中计算指定字典区间内成员数量
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员数量
     */
    Long zlexcount(final byte[] key, final byte[] min, final byte[] max);

    /**
     * Redis 有序集合(sorted set)操作
     * 在有序集合中计算指定字典区间内成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合
     */
    Set<byte[]> zrangeByLex(final byte[] key, final byte[] min, final byte[] max);

    /**
     * Redis 有序集合(sorted set)操作
     * 在有序集合中计算指定字典区间内成员
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param  count 数量
     * @return 成员集合
     */
    Set<byte[]> zrangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 在有序集合中计算指定字典区间内成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合 降序
     */
    Set<byte[]> zrevrangeByLex(final byte[] key, final byte[] max, final byte[] min);

    /**
     * Redis 有序集合(sorted set)操作
     * 在有序集合中计算指定字典区间内成员 降序
     * @param key 键
     * @param min 最小分数
     * @param max 最大分数
     * @param offset 偏移量
     * @param  count 数量
     * @return 成员集合 降序
     */
    Set<byte[]> zrevrangeByLex(final byte[] key, final byte[] max, final byte[] min, final int offset, final int count);

    /**
     * Redis 有序集合(sorted set)操作
     * 移除有序集合中给定的字典区间的所有成员
     * @param key 键
     * @param min 开始索引
     * @param max   结束索引
     * @return 成功删除的成员个数
     */
    Long zremrangeByLex(final byte[] key, final byte[] min, final byte[] max);

    /**
     * Redis 脚本命令
     * @param script 脚本
     * @param keyCount 指定键名参数的个数
     * @param params 从 EVAL 的第三个参数开始算起，表示在脚本中所用到的那些 Redis 键(key)，这些键名参数可以在 Lua 中通过全局变量 KEYS 数组，用 1 为基址的形式访问( KEYS[1] ， KEYS[2] ，以此类推)。
     *                附加参数，在 Lua 中通过全局变量 ARGV 数组访问，访问的形式和 KEYS 变量类似( ARGV[1] 、 ARGV[2] ，诸如此类)。
     * @return 执行结果
     */
    Object eval(byte[] script, int keyCount, byte[]... params);

    /**
     * Redis 脚本命令
     * @param script 脚本
     * @param keys 表示在脚本中所用到的那些 Redis 键(key)，这些键名参数可以在 Lua 中通过全局变量 KEYS 数组，用 1 为基址的形式访问( KEYS[1] ， KEYS[2] ，以此类推)。
     * @param args 附加参数，在 Lua 中通过全局变量 ARGV 数组访问，访问的形式和 KEYS 变量类似( ARGV[1] 、 ARGV[2] ，诸如此类)。
     * @return 执行结果
     */
    Object eval(byte[] script, List<byte[]> keys, List<byte[]> args);
}
