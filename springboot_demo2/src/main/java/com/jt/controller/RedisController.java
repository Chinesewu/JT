package com.jt.controller;


import com.jt.pojo.TestPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//动态获取ip和端口数据
//@ResponseBody    作用1: 将对象转化为JSON  作用2: 如果返回值是String类型,则返回字符串本身
//                 作用3:  一般客户端发起ajax请求时,采用该注解返回数据, 将不会执行视图解析器操作
@RestController
//动态的导入pro配置文件,交给spring容器进行加载.
@PropertySource("classpath:/properties/pro.properties")
public class RedisController {

    @Value("${redis.host}")//spel表达式
    private String host;    // = "192.168.126.130";      private String  host;   // = "192.168.126.130";
    @Value("${redis.port}")
    private Integer port;    // = 4399;

    @RequestMapping("/getMsg")
    public String getMsg(){
        return host+":"+port;
    }

    @Value("${pro.redis.host}")//spel表达式
    private String proHost;    // = "192.168.126.130";      private String  host;   // = "192.168.126.130";
    @Value("${pro.redis.port}")
    private Integer proPort;    // = 4399;

    @RequestMapping("/getPro")
    public String getPro(){
        TestPojo testPojo=new TestPojo();
        testPojo.setId(1);
        System.out.println(testPojo.getId());
        return proHost+":"+proPort;
    }

}
