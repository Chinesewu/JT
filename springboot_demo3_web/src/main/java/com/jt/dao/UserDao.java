package com.jt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper //将接口交给Spring管理
public interface UserDao extends BaseMapper<User> {


}
