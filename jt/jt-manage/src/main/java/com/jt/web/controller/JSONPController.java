package com.jt.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemDesc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONPController {

    /**
     *
     */
    /*@RequestMapping("/web/testJSONP")
    public String testJSONP(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L).setItemDesc("JSONP测试!!!");
        String json = ObjectMapperUtil.toJSON(itemDesc);
        return callback+"("+json+")";
    }*/
    @RequestMapping("/web/testJSONP")
    public JSONPObject testJSONP(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L).setItemDesc("JSONP测试!!!");
        return new JSONPObject(callback, itemDesc);
    }
}
