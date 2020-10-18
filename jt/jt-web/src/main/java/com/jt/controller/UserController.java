package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/user")
@Controller
public class UserController {
    /**
     *  实现用户模块页面跳转
     *  url:http://www.jt.com/user/login.html
     *  url:http://www.jt.com/user/register.html
     */

    @RequestMapping("/{moduleName}")
    public String module(@PathVariable String moduleName){
        return moduleName;
    }

}
