package com.zzzfyrw.common.cache.lettuce;

import com.zzzfyrw.common.cache.IRedisCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class LettuceHandler<V> implements IRedisCommand<String,V> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Set<String> getAllKeys() {
        Set<String> keys = redisTemplate.keys("*");
        if(keys == null){
            return new HashSet<>();
        }
        return keys;
    }

    @Override
    public V get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return (V) o;
    }

    @Override
    public List<V> getList(String key) {
        Long size = redisTemplate.opsForList().size(key);
        if(size == null){
            return new ArrayList<>();
        }
        List<V> range = redisTemplate.opsForList().range(key, 0, size);
        return range;
    }

    @Override
    public Map<String, V> getMap(String key, String hasKey) {
        Object o = redisTemplate.opsForHash().get(key, hasKey);
        Map<String,V> map = new HashMap<>();
        map.put(hasKey,(V) o);
        return map;
    }

    @Override
    public Map<String, V> getAllMap(String key) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        Map<String,V> result = new HashMap<>();
        entries.forEach((k,v) ->{
            result.put((String)k,(V) v);
        });
        return result;
    }

    @Override
    public Set<V> getSet(String key) {
        Set<V> members = redisTemplate.opsForSet().members(key);
        return members;
    }

    @Override
    public void setSet(String key, Set<V> value) {
        for (V v : value) {
            redisTemplate.opsForSet().add(key,v);
        }
    }

    @Override
    public void set(String key, V value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void setList(String key, List<V> value) {
        redisTemplate.opsForList().leftPushAll(key,value);
    }

    @Override
    public void putMap(String key, Map map) {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        map.forEach((k,v) ->{
            hashOperations.put(key, k, v);
        });

    }

    @Override
    public Boolean setKeyExpireTime(String key, Long time) {
        Boolean expire = redisTemplate.expire(key, time, TimeUnit.SECONDS);
        return expire;
    }

    @Override
    public void sendMessage(String channel, Object msg) {
        redisTemplate.convertAndSend(channel,msg);
    }


    public V execute(RedisScript<V> redisScript,List keys,int count,int period){
        Object execute = redisTemplate.execute(redisScript, keys, count, period);
        return (V) execute;
    }

}
