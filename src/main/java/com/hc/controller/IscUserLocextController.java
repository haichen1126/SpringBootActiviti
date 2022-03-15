package com.hc.controller;


import com.hc.config.ResponseBean;
import com.hc.model.IscUserLocext;
import com.hc.model.User;
import com.hc.service.IIscUserLocextService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 同步ISC日志表(员工表) 前端控制器
 * </p>
 *
 * @author hc
 * @since 2021-03-30
 */
@RestController
@RequestMapping("/isc-user-locext")
public class IscUserLocextController {

@Resource
private IIscUserLocextService iscUserLocextService;

    @RequestMapping(value = "/a3", method = RequestMethod.GET)
    public ResponseBean a3() {

        List<IscUserLocext> mapList = iscUserLocextService.userList(1);
        return new ResponseBean(200, "", mapList);
    }
}
