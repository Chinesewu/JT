package com.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

public class ObjectMapperUtil {
    /**
     *  将用户传递的数据转化为json串
     *  将用户传递的json转为我对象
     */
     private static final ObjectMapper MAPPER=new ObjectMapper();

     //将用户传递的数据转化为json串
    public static String toJSON(Object object){
        if(object==null){
            throw new RuntimeException("传递的数据为null，请检查数据");
        }
        try {
            String json = MAPPER.writeValueAsString(object);
            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //需求：要求用户传递什么样的类型，我返回什么样的对象
    public static <T> T toObj(String json,Class<T> target){
        if(StringUtils.isEmpty(json)||target==null){
            throw new RuntimeException("参数不能为null");
        }
        try{
            return MAPPER.readValue(json, target);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
