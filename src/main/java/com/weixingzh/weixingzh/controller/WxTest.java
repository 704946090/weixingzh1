package com.weixingzh.weixingzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixingzh.weixingzh.menu.Menu;
import com.weixingzh.weixingzh.po.AccessToken;
import com.weixingzh.weixingzh.util.MessageUti;
import com.weixingzh.weixingzh.util.Weixutil;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class WxTest {
    public static void main(String[] args) {
        AccessToken token = Weixutil.getAccessToken();
        System.out.println("票据:"+token.getToken());
        System.out.println("有效时间:"+token.getExpiresIn());
//        String path = "D:/imooc.jpg";
        try {
//            String mediaId = Weixutil.upload(path,token.getToken(),"thumb");
//            System.out.println("mediaId:"+mediaId);




//            String menu = JSONObject.fromObject(Weixutil.initMenu()).toString();
//            System.out.println("meu:"+menu);
//            int result = Weixutil.createMenu(token.getToken(),menu);
//            if(result == 0){
//                System.out.println("创建菜单成功");
//            }else {
//                System.out.println("错误码:"+result);
//            }


            JSONObject jsonObject = Weixutil.queryMenu(token.getToken());
            System.out.println(jsonObject);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
