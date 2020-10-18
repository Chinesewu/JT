package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.dao.UserDao;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringbootDemo2MybatisApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void testFind(){

        List<User> userList = userDao.findAll();
        System.out.println(userList);
    }
    @Test
    public void testSelect01(){

        List<User> userList = userDao.selectList(null);
        System.out.println(userList);
    }
    /**
     * 业务: 查询id=11的用户信息   主键...
     */
    @Test
    public void testSelect02(){

        User user = userDao.selectById(11);
        System.out.println(user);
    }

    /**
     * 业务: 查询name属性为"小乔"的数据
     * sql:  select * from user where name="小乔";
     * 对象的方式  >  sql方式
     */
    @Test
    public void testSelect03(){
        //条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "小乔");
        List<User> userList = userDao.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 业务: 查询name属性为"小乔"的数据 并且 age >=18岁
     * sql:  select * from user where name="小乔" and age>=18;
     *
     * 大于   >  gt| 小于 <  lt   |
     * 大于等于  >= ge  |  小于等于 le
     */
    @Test
    public void testSelect04(){
        //条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "小乔")
                .ge("age", 18);
        List<User> userList = userDao.selectList(queryWrapper);
        System.out.println(userList);
    }

    /**
     * 业务: 查询name中包含 "精"的用户,并且sex为女
     * 业务: 查询name中包含 以精结尾的数据,并且sex为女
     * sql:  select * from user where name like "%精%" and sex="女";
     */
    @Test
    public void testSelect05(){
        //条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeLeft("name", "精")
                .eq("sex", "女");
        List<User> userList = userDao.selectList(queryWrapper);
        System.out.println(userList);
    }

}
