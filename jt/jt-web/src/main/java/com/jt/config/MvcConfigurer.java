package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration						  //web.xml配置文件
public class MvcConfigurer implements WebMvcConfigurer{

    //开启匹配后缀型配置
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        //开启后缀类型的匹配.  xxxx.html
        configurer.setUseSuffixPatternMatch(true);
    }
}