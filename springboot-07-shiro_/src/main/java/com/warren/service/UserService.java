package com.warren.service;

import com.warren.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User queryUserByName(String name);
}
