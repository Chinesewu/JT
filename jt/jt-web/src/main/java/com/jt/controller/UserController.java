package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {

    @Reference(timeout = 3000)
    private DubboUserService userService;
    /**
     *  实现用户模块页面跳转
     *  url:http://www.jt.com/user/login.html
     *  url:http://www.jt.com/user/register.html
     */

    @RequestMapping("/{moduleName}")
    public String module(@PathVariable String moduleName){
        return moduleName;
    }

    /**
     *  需求：实现用户信息的注册
     *  1.url地址：http://www.jt.com/user/doRegister
     *  2.请求参数：
     *  3.返回值类型：SysResult对象
     */
    @ResponseBody   //将数据转化为json串
    @RequestMapping("/doRegister")
    public SysResult saveUser(User user){
        userService.saveUser(user);
        return SysResult.success();
    }

}
