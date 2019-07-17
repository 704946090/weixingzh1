package com.weixingzh.weixingzh.controller;

import com.alibaba.fastjson.JSONObject;
import com.weixingzh.weixingzh.util.Weixutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class baiduTest {
    public static void main(String[] args) {

        String url = "http://api.map.baidu.com/place/v2/search?query=银行&location=30.503679,114.323563&radius=2000&output=json&ak=TGXfSE1dvZzAYqPKHdwGL7jB5dC7GuVU";
        JSONObject object = Weixutil.doGetStr(url);
        System.out.println("object:"+object);
        Map map =(Map) object;
        String str = map.get("results").toString();
        List list=JSONObject.parseArray(str);
        System.out.println("str:"+list);

        List<Map> listResult = new ArrayList<>();
        for(int i=0;i<list.size();i++){

            String str1 = list.get(i).toString();
            JSONObject jsonObject = JSONObject.parseObject(str1);
            Map map1 =(Map) jsonObject;
            listResult.add(map1);
        }
//        for (Map i:listResult){
//            System.out.println(i.get("name"));
//            System.out.println(i.get("address"));
//            System.out.println();
//        }
    }
}
