package com.jt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    /**
     * 用户通过/hello的路径获取 友好的提示信息.
     */
    @RequestMapping("/hello")
    public String hello(){

        return "搭建环境真困难,为什么就我的不行呢? 我昨天干了什么呢!!!!!";
    }
}
