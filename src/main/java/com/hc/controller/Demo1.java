package com.hc.controller;


import com.hc.config.ResponseBean;
import com.hc.model.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/a")
@Api(value = "demo1控制类", tags = "demo1控制类", description = "demo1很好用")
public class Demo1 {


    @RequestMapping(value = "/a1", method = RequestMethod.POST)
    @ApiOperation(value = "a1", notes = "a1查询")
    @ApiOperationSupport(order = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "int")
    })
    public ResponseBean<User> a1(@RequestParam(name = "name") String name, @RequestParam(name = "age") Integer age) {
        ResponseBean<User> responseBean=new ResponseBean<>();
        User user = new User();
        user.setAge(age);
        user.setName(name);
        responseBean.setData(user);
        return responseBean;
    }


    @RequestMapping(value = "/a2", method = RequestMethod.POST)
    @ApiOperation(value = "a2", notes = "a2查询")
    @ApiOperationSupport(order = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "int")
    })
    public ResponseBean a2(@RequestParam(name = "name") String name, @RequestParam(name = "age") Integer age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        return new ResponseBean(200, "", user);
    }


    @RequestMapping(value = "/a3", method = RequestMethod.POST)
    @ApiOperation(value = "a3", notes = "a3查询", response = ResponseBean.class)
    @ApiOperationSupport(order = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, dataType = "int")
    })
    public ResponseBean<User> a3(@RequestParam(name = "name") User user) {
        return new ResponseBean(200, "", user);
    }

}
