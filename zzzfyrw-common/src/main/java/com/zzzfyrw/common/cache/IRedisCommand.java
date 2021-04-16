package com.zzzfyrw.common.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRedisCommand<K,V> {


    /**
     * 获取Redis中所有的键的key 慎用
     * @return
     */
    Set<String> getAllKeys();


    /**
     * 获取单个key的值
     * @param key
     * @return
     */
     V get(final K key);

    /**
     * 获取key值的list类型
     * @param key
     * @return
     */
     List<V> getList(final K key);

    /**
     * 获取key值 hashKey 的map类型
     * @param key
     * @return
     */
    Map<K,V> getMap(final K key,String hasKey);

    /**
     * 获取key值的所有map
     */
    Map<K,V> getAllMap(final K key);

    /**
     * 获取key值的set类型
     */
    Set<V> getSet(final K key);

    /**
     * 存储set类型
     */
    void setSet(final K key,Set<V> value);


    /**
     * 存储string类型
     */
    void set(final K key,V value);

    /**
     * 存储list类型
     */
    void setList(final K key,List<V> value);

    /**
     * 存储map类型
     */
    void putMap(final K key,Map map);

    /**
     * 设置存储的过期时间
     * @param key
     * @param time
     * @return
     */
    Boolean setKeyExpireTime(String key,Long time);


    void sendMessage(String channel,Object msg);

}
