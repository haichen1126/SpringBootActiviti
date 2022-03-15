package com.hc.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;
import java.util.Collection;
import java.util.Map;

/**
 * @means 重载RedisCacheManager就为了自定义缓存的过期时间
 * @Author HC
 * @Date 2021/4/12
 * @Param
 * @return
 **/
public class RedisAutoCacheManager extends RedisCacheManager {

    private static final String SPLIT_FLAG = "#";
    private static final int CACHE_LENGTH = 2;

    public RedisAutoCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected Collection<RedisCache> loadCaches() {
        return super.loadCaches();
    }

    @Override
    protected RedisCache getMissingCache(String name) {
        return super.getMissingCache(name);
    }

    @Override
    public Map<String, RedisCacheConfiguration> getCacheConfigurations() {
        return super.getCacheConfigurations();
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        if (StringUtils.isBlank(name) || !name.contains(SPLIT_FLAG)) {
            return super.createRedisCache(name, cacheConfig);
        }
        String[] cacheArray = name.split(SPLIT_FLAG);
        if (cacheArray.length < CACHE_LENGTH) {
            return super.createRedisCache(name, cacheConfig);
        }
        if (cacheConfig != null) {
            long cacheAge = Long.parseLong(cacheArray[1]);
            name = name.substring(0, name.lastIndexOf(SPLIT_FLAG));
            cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(cacheAge));
        }
        return super.createRedisCache(name, cacheConfig);
    }

    @Override
    public void setTransactionAware(boolean transactionAware) {
        super.setTransactionAware(transactionAware);
    }

    @Override
    public boolean isTransactionAware() {
        return super.isTransactionAware();
    }

    @Override
    protected Cache decorateCache(Cache cache) {
        return super.decorateCache(cache);
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    public void initializeCaches() {
        super.initializeCaches();
    }

    @Override
    public Cache getCache(String name) {
        return super.getCache(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return super.getCacheNames();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
