package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {
    public List<User> findUserAll();

    boolean checkUser(String param, Integer type);
}
