package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration  //标识我是一个配置类
public class CorsConfig implements WebMvcConfigurer {
    //在后端 配置cors允许访问的策略
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST")  //定于允许跨域请求的类型
                .allowedOrigins("*")           //任意网址都可以访问
                .allowCredentials(true) //是否允许携带cookie
                .maxAge(1800);          //探针！   定义请求长链接的超时时长
    }
}
