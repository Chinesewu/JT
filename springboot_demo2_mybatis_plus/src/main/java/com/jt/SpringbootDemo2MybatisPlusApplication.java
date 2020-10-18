package com.jt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.jt.dao") //主要告诉mapper的包路径,会自动的完成包扫描
@SpringBootApplication
public class SpringbootDemo2MybatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemo2MybatisPlusApplication.class, args);
	}

}
