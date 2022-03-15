package com.hc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hc.config.RedisKeyUUID;
import com.hc.model.IscUserLocext;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 同步ISC日志表(员工表) Mapper 接口
 * </p>
 *
 * @author hc
 * @since 2021-03-30
 */
public interface IscUserLocextMapper extends BaseMapper<IscUserLocext> {


//   @Cacheable(value = RedisKeyUUID.MP_KEY_USER,key="'redis_user_'+#i",keyGenerator = "myKeyGenerator")
   @Cacheable(value = RedisKeyUUID.MP_KEY_USER,key="'redis_user_'+#i")
    List<IscUserLocext> userList(int i);
}
