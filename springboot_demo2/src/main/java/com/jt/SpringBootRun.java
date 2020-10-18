package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //标识我是一个SpringBoot的项目
public class SpringBootRun {
    public static void main(String[] args) {
        //加载@SpringBootApplication注解
        SpringApplication.run(SpringBootRun.class,args);
    }
}
