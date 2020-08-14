package com.tsu.zzz.service;

import com.tsu.zzz.pojo.User;

public interface UserService {
    User findByUsernameAndPassword(String username,String password);
}
