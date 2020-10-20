/**
 * ‎Hangzhou Lejian Technology Co., Ltd.
 * Copyright (c) 2019 All Rights Reserved.
 */
package com.jt;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户注册入口
 *
 * @author wy
 *
 */
public class SigninController {

    /**
     * TODO 请从这里开始补充代码
     *
     * 测试1：138 1234 1234
     *	结果：通过此手机号注册成功
     *
     * 测试2：13812345abc
     *	结果：通知本手机号无法注册，提示为非法手机号
     *
     * 测试3：13812345678
     *	结果：通知此手机号注册成功
     *
     * 测试4：138 1234 5678
     *	结果：提示此手机号已经被其他用户注册
     *
     * 测试5：98765432101
     *	结果：此手机号码为中国大陆非法手机号码
     */


    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
        List <String> numbers=new  ArrayList<String>();
        numbers.add("138 1234 1234");
        numbers.add("13812345abc");
        numbers.add("13812345678");
        numbers.add("138 1234 5678");
        numbers.add("98765432101");
        for (int i=0;i<numbers.size();i++){
            String test=isMobiPhoneNum(numbers.get(i).replaceAll("\\s*", ""));
            if(list.contains(numbers.get(i).replaceAll("\\s*", ""))){
                System.out.println("此手机号已经被其他用户注册");
            }else if("1".equals(test)){
                System.out.println("此手机号注册成功");
                list.add(numbers.get(i).replaceAll("\\s*", ""));
            }else if ("2".equals(test)){
                System.out.println("此手机号码为中国大陆非法手机号码");
            }else{
                System.out.println("手机号无法注册，为非法手机号");
            }
        }
    }
    public static String isMobiPhoneNum(String telNum){
        String regex = "^\\d{0,11}$";
        String regexCh = "^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
        Pattern pCh = Pattern.compile(regexCh,Pattern.CASE_INSENSITIVE);
        Matcher mCh = pCh.matcher(telNum);
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(telNum);
        boolean a=m.matches();
        boolean b=mCh.matches();
        if(m.matches()&&mCh.matches()){ //通过
            return "1";
        }else if(m.matches()&&!mCh.matches()){ //非大陆
            return "2";
        }else{ //不通过
            return "3";
        }
    }

}