package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findUserAll() {
        return userMapper.selectList(null);
    }

    @Override
    public boolean checkUser(String param, Integer type) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(, );
        userMapper.selectCount();///////
        return false;
    }
}
