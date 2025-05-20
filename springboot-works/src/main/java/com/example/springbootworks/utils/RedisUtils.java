package com.example.springbootworks.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
@Service
public class RedisUtils {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public <T> void set(String key, T value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public <T> T get(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);
        return value != null ? (T) value : null;
    }

    public <T> void setList(String key, List<T> list, long timeout, TimeUnit unit) {
        redisTemplate.opsForList().rightPushAll(key, list);
        redisTemplate.expire(key, timeout, unit);
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        List<Object> list = redisTemplate.opsForList().range(key, 0, -1);
        return list.stream().map(o -> (T) o).collect(Collectors.toList());
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }
}