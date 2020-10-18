package com.jt.controller;

import com.jt.dao.UserDao;
import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//RestController json,字符串本身， 不经过视图解析器
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

@RequestMapping("/findAll")
    public String findAll(Model model){

    List<User> userList = userService.findAllUser();
    model.addAttribute("userList",userList);
    return "userList";
    }
    //跳转ajax页面
    @RequestMapping("/ajax")
    public String toAjax(){

        return "ajax";
    }
    //实现ajax业务调用,返回页面列表数据.
    @RequestMapping("/findAjax")
    @ResponseBody
    public List<User> findAjax(){

        return userDao.selectList(null);
    }

}
