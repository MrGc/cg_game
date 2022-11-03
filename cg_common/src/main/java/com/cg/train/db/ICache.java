package com.cg.train.db;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/2 10:46
 * @Version: 1.0.0
 */
public interface ICache<K, V> {
    /**
     * <p>
     *     获取数据，如果缓存命中则返回缓存，
     *     如果没有命中，则加载缓存，加载缓存的同时会缓存Other再返回，
     *     只允许查询自己数据。
     * </p>
     * @param key
     * @return
     */
    V get(K key);

    /**
     * <p>
     *     读取其他人的数据，如果缓存则命中缓存，
     *     如果没有命中，则加载缓存。
     *     该方法的缓存和{@link ICache#get(K)} 不在同一个。
     * </p>
     * @param key
     * @return
     */
    V getOther(K key);

    /**
     * 缓存的replace操作，更新变更集
     * @param key
     * @param value
     */
    void replace(K key, V value);

    /**
     * 缓存delete操作， 更新变更集
     * @param key
     */
    void delete(K key);

    /**
     * 单用户回写，并清楚过期缓存和Redis变更集，氪用于用户下线回写
     * @param key
     */
    void writeBackAndClear(K key);
}
