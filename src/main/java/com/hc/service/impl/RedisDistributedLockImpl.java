package com.hc.service.impl;

import com.hc.service.DistributedLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisDistributedLockImpl implements DistributedLockService {

    private static final Logger logger = LoggerFactory.getLogger(RedisDistributedLockImpl.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static String LOCK_VALUE = "LOCK_VALUE";

    /**
     * @param key redis key, 唯一键
     * @return true 获得锁成功，false，获得锁失败
     * @desc 加锁 true已锁  false未锁
     */
    @Override
    public boolean getLock(String key, long time, TimeUnit unit) {
        // 对应setnx命令
        if (redisTemplate.opsForValue().setIfAbsent(key, LOCK_VALUE, time, unit)) {
            return true;
        }
        return false;
    }

    /**
     * @param key redis key, 唯一键
     * @return
     * @desc 释放锁
     */
    @Override
    public void releaseLock(String key) {
        try {
            // 删除key
            redisTemplate.opsForValue().getOperations().delete(key);
        } catch (Exception e) {
            logger.error("解锁出现异常了，{}", e);
        }
    }
}
