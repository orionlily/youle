package com.orion.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.orion.domain.User;
import com.orion.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @date 2019/1/9
 */
@RestController
public class OrderController {

    @Reference
    private UserService userService;

    @RequestMapping("/test")
    public User test(){
        User user= userService.selectUserById(1);
        return  user;
    }
}
