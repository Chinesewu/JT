package com.jt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper //将接口交给Spring管理
public interface UserDao extends BaseMapper<User> {

    //查询user表的所有的记录
    @Select("select * from user")
    List<User> findAll();

}
