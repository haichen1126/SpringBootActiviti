package com.hc.service;

import java.util.concurrent.TimeUnit;

public interface DistributedLockService {
    boolean getLock(String key, long time, TimeUnit unit);

    void releaseLock(String key);
}
