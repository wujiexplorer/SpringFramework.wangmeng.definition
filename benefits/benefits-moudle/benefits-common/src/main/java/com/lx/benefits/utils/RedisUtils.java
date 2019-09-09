package com.lx.benefits.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * User: fan
 * Date: 2019/02/25
 * Time: 16:28
 */
@Component
public class RedisUtils {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 取得有效时间内的锁
     *
     * @param lock
     * @param expireSeconds 有效时间 单位 秒
     * @return
     */
    public boolean lock(String lock, int expireSeconds) {
        if (0 == expireSeconds) {
            return false;
        }
        return (Boolean) redisTemplate.execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() + expireSeconds + 1;
            Boolean acquire = connection.setNX(lock.getBytes(), String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            } else {
                byte[] value = connection.get(lock.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {
                    long expireTime = Long.parseLong(new String(value));
                    if (expireTime < System.currentTimeMillis()) {
                        // 如果锁已经过期
                        byte[] oldValue = connection.getSet(lock.getBytes(), String.valueOf(System.currentTimeMillis() + expireSeconds + 1).getBytes());
                        // 防止死锁
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    /**
     * 释放锁
     *
     * @param key
     * @return
     */
    public boolean unLock(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            return redisTemplate.delete(key);
        } catch (Exception e) {
        }
        return false;
    }


    public String get(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object obj = redisTemplate.opsForValue().get(key);
        return obj != null ? obj.toString() : null;
    }

    public <T> T get(String key, Class<T> clazz) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Object obj = redisTemplate.opsForValue().get(key);
        return JSONObject.parseObject(obj != null ? obj.toString() : null, clazz);
    }

    public boolean set(String key, Object value) {
        if (StringUtils.isAllBlank(key)) {
            return false;
        }
        try {
            String json = JSONObject.toJSONString(value);
            redisTemplate.opsForValue().set(key, json);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean set(String key, String value) {
        if (StringUtils.isAllBlank(key)) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean set(String key, Object value, Integer seconds) {
        if (StringUtils.isAllBlank(key)) {
            return false;
        }
        try {
            String json = JSONObject.toJSONString(value);
            redisTemplate.opsForValue().set(key, json, seconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean set(String key, String value, Integer seconds) {
        if (StringUtils.isAllBlank(key)) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean delete(String key) {
        if (StringUtils.isAllBlank(key)) {
            return false;
        }
        try {
            redisTemplate.delete(key);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean expire(String key, long timeout, final TimeUnit unit) {
    	if (StringUtils.isAllBlank(key)) {
            return false;
        }
    	redisTemplate.expire(key, timeout, unit);
        return true;
    }
}
