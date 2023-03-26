package net.zhongfu.springframework.test.bean;

import net.zhongfu.springframework.beans.factory.DisposableBean;

import java.util.HashMap;
import java.util.Map;

public class UserDao implements DisposableBean {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    /**
     * 自定义一个同名方法
     */
    public void destroy(){
        System.out.println("执行自定义destroy()：destroy-method--1");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
