package com.jt;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 找出最长对称字符串
 *
 * @author wy
 *
 */
public class Test1 {
    public static void main(String[] args) {
        // TODO 输出最长对称字符串：goog
        String input1 = "google";

        // TODO 输出3个最长对称字符串：aba/aca/ada
        String input2 = "abcda";

        // TODO 输出2个最长对称字符串：pop/upu，不会输出特殊字符的对称字符串p-p
        String input3 = "pop-upu";
        List<String> list = new ArrayList<>();
        char[] c=input2.toCharArray();
        StringBuffer sb=new StringBuffer();
        for(int i=1;i<=c.length;i++) {
            getAllSubs(c, 0, i, sb,list);
        }
        //去重
        Set<String> set = new HashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        print(list);
        System.out.println("=================");
        List<String> list1 = getRel(list);
        print(list1);
        System.out.println("=================");
        List<String> list2 = getRelsult(list1);
        print(list2);
    }
    /**
     * 获取list中的对称字符串的最大长度，如果有多个分别输出
     * @param list
     * @return
     */
    public static List<String> getRelsult(List<String>  list){
        List<String> list2 = new ArrayList<>();
        int maxLen = 0;
        //求最大字符串的长度
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).length() > maxLen){
                maxLen = list.get(i).length();
            }
        }
        //遍历集合，输出符合要求的字符串
        for (String s : list) {
            if(s.length() == maxLen){
                list2.add(s);
            }
        }
        return list2;
    }
    /**
     * 获取给定字符串中的所有排列的字符串
     * @param c
     * @param begin
     * @param len
     * @param sb
     * @param list
     * @return
     */
    private static List<String> getAllSubs(char[] c,int begin,int len, StringBuffer sb,List<String> list) {
        if(len==0) {                //当都选择结束时打印sb内容
            String s = sb+"";
            //排除含有特殊符号和长度等于1的字符串
            if(s.matches("[A-Za-z0-9]*") && (s.length() > 1)){
                list.add(s);
            }
            return list;
        }
        if(begin==c.length)
            return list;
        sb.append(c[begin]);   				//取
        getAllSubs(c,begin+1,len-1,sb,list);   	//剩下的里面选len-1个
        sb.deleteCharAt(sb.length()-1); 	//不取
        getAllSubs(c,begin+1,len,sb,list);   	//剩下的里面选len个
        return list;
    }


    /**
     * 将字符串反转后如果和原来相等，则证明是对称的，将对称的字符串放到list中
     * @param list
     * @return
     */
    public static List<String> getRel(List<String> list){
        List<String> list2 = new ArrayList<>();
        //遍历list中的字符串
        for (String s : list) {
            //判断反转后是否相等
            StringBuilder sb = new StringBuilder(s);
            if(s.equals(sb.reverse().toString())){
                list2.add(s);
            }
        }
        return list2;
    }

    /**
     * 打印list中的内容
     * @param list
     */
    public static void print(List<String> list){
        for (String s: list) {
            System.out.print(s+" ");
        }
        System.out.println();
    }
}