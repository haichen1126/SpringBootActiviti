package com.hc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.service.IIscUserLocextService;
import com.hc.mapper.IscUserLocextMapper;
import com.hc.model.IscUserLocext;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 同步ISC日志表(员工表) 服务实现类
 * </p>
 *
 * @author hc
 * @since 2021-03-30
 */
@Service
public class IscUserLocextServiceImpl extends ServiceImpl<IscUserLocextMapper, IscUserLocext> implements IIscUserLocextService {
    @Resource
    private IscUserLocextMapper iscUserLocextMapper;

    @Override

    public List<IscUserLocext> userList(int i) {
        return iscUserLocextMapper.userList(i);
    }
}
