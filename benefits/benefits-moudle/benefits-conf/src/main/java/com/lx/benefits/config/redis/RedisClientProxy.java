package com.lx.benefits.config.redis;


import com.apollo.common.lock.support.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RedisClientProxy{

    /**
     * 上锁以后xxx毫秒自动解锁 单位毫秒
     */
    private final static int LOCK_EXPIRE_TIME=10000;
    /** 重试次数*/
    private final static int RETRY_TIMES =3;
    /** 重试的间隔时间,单位毫秒*/
    private final static long SLEEP_MILLIS = 200L;

    @Autowired
    private DistributedLock redisDistributedLock;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量放缓存
     *
     * @param keyValueMap
     * @param <T>
     */
    public <T> void mSet(Map<String, T> keyValueMap) {
        redisTemplate.opsForValue().multiSet(keyValueMap);
    }

    /**
     * 批量读缓存
     *
     * @param keyList
     * @param <T>
     * @return
     */
    public <T> List<T> mGet(List<String> keyList) {
        return redisTemplate.opsForValue().multiGet(keyList);
    }

    /**
     * 删除缓存key
     * 异步处理，不影响主流程
     *
     * @param key
     */
    @Async
    public void removeKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个缓存key
     *
     * @param keys
     */
    @Async
    public void removeKeys(List<String> keys) {
        if (keys == null || keys.isEmpty()) {
            return;
        }
        redisTemplate.delete(keys);
    }

}
