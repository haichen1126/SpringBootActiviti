package com.hc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.model.IscUserLocext;

import java.util.List;

/**
 * <p>
 * 同步ISC日志表(员工表) 服务类
 * </p>
 *
 * @author hc
 * @since 2021-03-30
 */
public interface IIscUserLocextService extends IService<IscUserLocext> {


    List<IscUserLocext> userList(int i);
}
