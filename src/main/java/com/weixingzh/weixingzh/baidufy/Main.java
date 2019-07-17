package com.weixingzh.weixingzh.baidufy;


import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20190624000310056";
    private static final String SECURITY_KEY = "gG0ZLCzmlT8vPpkttknE";

    public static void main(String[] args) throws UnsupportedEncodingException {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "football";
        System.out.println(api.getTransResult(query, "auto", "auto"));
        String result=api.getTransResult(query, "auto", "auto");
//        JSONObject jsonObject = JSONObject.fromObject(result);
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(result);

        System.out.println("jsonObject:"+jsonObject);
        Map map=(Map) jsonObject;
        System.out.println("翻译:"+map.get("trans_result"));
        String result1=map.get("trans_result").toString().replace("[","").replace("]","");
        System.out.println(result1);

        JSONObject jsonObject1 = JSONObject.fromObject(result1);
        Map map1=(Map) jsonObject1;
        String finaly = map1.get("dst").toString();
        System.out.println("翻译:"+finaly);

    }



}
