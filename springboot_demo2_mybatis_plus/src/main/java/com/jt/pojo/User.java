package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
@TableName  //("user")      //1.将对象与表 进行一对一关联
public class User implements Serializable {
    @TableId(type = IdType.AUTO)    //主键的信息  设定自增
    private Integer Id;
    //@TableField(value = "name")     //如果字段名称与属性的名称一致(包含驼峰规则),可以省略不写
    private String name;
    private Integer age;
    private String sex;
}
