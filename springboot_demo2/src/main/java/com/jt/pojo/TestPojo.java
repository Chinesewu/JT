package com.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)//链式加载....
@NoArgsConstructor //添加无参构造----
@AllArgsConstructor //添加全参构造
public class TestPojo {
    private Integer id; //pojo类必须包装类型''
    private String name;///

}
