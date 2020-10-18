package com.jt.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

public class TestObjectMapper {

    @Test
    public void test01() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(100L).setItemDesc("商品详情信息")
                .setCreated(new Date()).setUpdated(new Date());
        //对象转化为json
        String json=objectMapper.writeValueAsString(itemDesc);
        System.out.println(json);
        //json转为对象
        ItemDesc itemDesc2=objectMapper.readValue(json, ItemDesc.class);
        System.out.println(itemDesc2.getItemDesc());
    }

    /**
     *  //测试集合对象的转化
     */
    @Test
    public void test02() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        ItemDesc itemDesc=new ItemDesc();
        itemDesc.setItemId(100L).setItemDesc("商品详细信息1")
                .setCreated(new Date()).setUpdated(new Date());
        ItemDesc itemDesc2=new ItemDesc();
        itemDesc2.setItemId(100L).setItemDesc("商品详细信息2")
                .setCreated(new Date()).setUpdated(new Date());
        ArrayList<ItemDesc> lists = new ArrayList<>();
        lists.add(itemDesc);
        lists.add(itemDesc2);
        //[{key:value},{}]
        String json = objectMapper.writeValueAsString(lists);
        System.out.println(json);
        //将json转化为对象
        ItemDesc itemDesc3 = objectMapper.readValue(json, itemDesc2.getClass());
        System.out.println(itemDesc3.getItemDesc());
    }


}
