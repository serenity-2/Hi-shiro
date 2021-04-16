package com.jzjr.hishiro.service.impl;

import com.jzjr.hishiro.dao.UserMapper;
import com.jzjr.hishiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public String getUserNameById() {
        return userMapper.selectUserNameById(1);
    }
}
