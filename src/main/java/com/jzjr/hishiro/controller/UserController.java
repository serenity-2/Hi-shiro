package com.jzjr.hishiro.controller;

import com.jzjr.hishiro.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/get/name")
    public void getUserNameById(){
        String userId = userService.getUserNameById();
        System.out.println(userId);
    }
}
