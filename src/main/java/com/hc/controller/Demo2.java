package com.hc.controller;


import com.hc.model.User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/demo2")

public class Demo2 {

    @RequestMapping(value = "/a1",method = RequestMethod.POST)
    public String a1(@RequestBody String a) {
        User user=new User();

        return "hhhh";
    }


}
