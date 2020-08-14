package com.tsu.zzz.service.impl;

import com.tsu.zzz.dao.UserMapper;
import com.tsu.zzz.pojo.User;
import com.tsu.zzz.service.UserService;
import com.tsu.zzz.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, MD5Util.code(password));
    }


}
