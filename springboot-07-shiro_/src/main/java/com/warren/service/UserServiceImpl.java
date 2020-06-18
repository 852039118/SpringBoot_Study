package com.warren.service;

import com.warren.mapper.UserMapper;
import com.warren.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
