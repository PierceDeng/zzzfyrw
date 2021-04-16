package com.zzzfyrw.common.cache.redisson;

import com.zzzfyrw.common.cache.IRedisCommand;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedissonHandler<V> implements IRedisCommand<String,V> {

    @Autowired
    private RedissonClient redissonClient;


    @Override
    public Set<String> getAllKeys() {
        RKeys keys = redissonClient.getKeys();
        long count = keys.count();
        Set<String> allKeys = new HashSet<>((int) count);
        Iterator<String> iterator = keys.getKeys().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            allKeys.add(key);
        }
        return allKeys;
    }

    @Override
    public V get(String key) {
        RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public List<V> getList(String key) {
        RList<V> list = redissonClient.getList(key);
        if(list.isEmpty()){
            return new ArrayList<V>();
        }
        return list;
    }

    @Override
    public Map<String, V> getMap(String key, String hasKey) {
        RMap<String, V> all = redissonClient.getMap(key);
        RMap<Object, Object> map = redissonClient.getMap(key);


        Map<String, V> single = all.getAll(Collections.singleton(hasKey));
        return single;
    }

    @Override
    public Map<String, V> getAllMap(String key) {
        RMap<String, V> all = redissonClient.getMap(key);
        return all;
    }

    @Override
    public Set<V> getSet(String key) {
        RSet<V> set = redissonClient.getSet(key);
        if(set == null){
            return new HashSet<>();
        }
        return set;
    }

    @Override
    public void setSet(String key, Set<V> value) {
        RSet<V> set = redissonClient.getSet(key);
        set.addAll(value);
    }

    @Override
    public void set(String key, V value) {
        RBucket<V> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    @Override
    public void setList(String key, List<V> value) {
        RList<V> list = redissonClient.getList(key);
        list.addAll(value);
    }

    @Override
    public void putMap(String key, Map map) {
        RMap rMap = redissonClient.getMap(key);
        rMap.putAll(map);
    }

    @Override
    public Boolean setKeyExpireTime(String key, Long time) {
        RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.expire(time, TimeUnit.SECONDS);
    }

    @Override
    public void sendMessage(String channel, Object msg) {

    }
}
