package com.weixingzh.weixingzh.util;

import com.alibaba.fastjson.JSONObject;
import com.weixingzh.weixingzh.menu.Button;
import com.weixingzh.weixingzh.menu.ClickButton;
import com.weixingzh.weixingzh.menu.Menu;
import com.weixingzh.weixingzh.menu.ViewButton;
import com.weixingzh.weixingzh.po.*;
import com.weixingzh.weixingzh.po.Image;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class MessageUti {


    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    public static final String MESSAGE_SCANCODE = "scancode_push";
    public static final String MESSAGE_LOCATIONS = "location";



    /**
     * xml转map格式
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */

    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {

        Map<String, String> map = new HashMap<String, String>();

        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();

        Document doc = reader.read(ins);

        Element root = doc.getRootElement();

        List<Element> list = root.elements();

        for(Element e : list){
            map.put(e.getName(),e.getText());
        }
        ins.close();

        return map;
    }


    public static String textMessageToXML(TextMessage textMessage) {

        XStream.xstream.alias("xml", TextMessage.class);
        String reqXml = XStream.xstream.toXML(textMessage);
        reqXml = reqXml.replaceAll("__", "_");

        System.out.println(reqXml);
        return reqXml;

    }


    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUti.MESSAGE_TEXT);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String da = simpleDateFormat.format(date);
        text.setCreateTime(da);
        text.setContent(content);
        return MessageUti.textMessageToXML(text);
    }


    /**
     * 主菜单
     *
     * @return
     */
    public static String menuText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注，请按照菜单提示进行操作:\n\n");
        sb.append("1.课程介绍\n");
        sb.append("2.慕课网介绍\n\n");
        sb.append("3.图片\n\n");
        sb.append("4.音乐\n\n");
        sb.append("5.翻译宝典\n\n");
        sb.append("6.地图查找\n\n");
        sb.append("回复？调出此菜单。");
        return sb.toString();
    }

    public static String firstmenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("本套课程介绍微信公众号开发");
        return sb.toString();
    }

    public static String secondmenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。");
        sb.append("慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。以纯干货、短视频的形式为平台特点，为在校学生、职场白领提供了一个迅速提升技能、共同分享进步的学习平台。");
        return sb.toString();
    }

    public static String fivedmenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("词组翻译使用指南\n\n");
        sb.append("使用示例:\n");
        sb.append("翻译足球:\n");
        sb.append("football\n\n");
        sb.append("回复？显示主此菜单。");

        return sb.toString();
    }

    public static String sixmenu() {
        StringBuffer sb = new StringBuffer();
        sb.append("地图使用指南\n\n");
        sb.append("使用示例:\n");
        sb.append("1:打开菜单，先发送当前位置\n");
        sb.append("2:打开键盘输入搜索信息。如:查找银行,格式:查找+?\n");
        sb.append("3.点击clik按钮获取信息\n\n");
        sb.append("回复？显示主此菜单。");

        return sb.toString();
    }


    /**
     * 图文消息转为xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXML(NewsMessage newsMessage) {

        XStream.xstream.alias("xml", NewsMessage.class);
        XStream.xstream.alias("item", News.class);
        String reqXml = XStream.xstream.toXML(newsMessage);
        reqXml = reqXml.replaceAll("__", "_");

        return reqXml;

//        com.thoughtworks.xstream.XStream xStream = new com.thoughtworks.xstream.XStream();
//        xStream.alias("xml",newsMessage.getClass());
//        xStream.alias("item",new News().getClass());
//
//        return xStream.toXML(newsMessage);

    }


    public static String imageMessageToXML(ImageMessage imageMessage) {

        XStream.xstream.alias("xml", ImageMessage.class);
        XStream.xstream.alias("Image", Image.class);
        String reqXml = XStream.xstream.toXML(imageMessage);
        reqXml = reqXml.replaceAll("__", "_");

        return reqXml;

    }

    public static String musicMessageToXML(MusicMessage musicMessage) {

        XStream.xstream.alias("xml", MusicMessage.class);
        String reqXml = XStream.xstream.toXML(musicMessage);
        reqXml = reqXml.replaceAll("__", "_");

        return reqXml;

    }



    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName, String fromUserName){

        String message = null;

        List<News> newsList=new ArrayList<News>();

        NewsMessage newsMessage =new NewsMessage();

        News news =new News();
        news.setTitle("慕课网介绍");
        news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。");
        news.setPicUrl("http://8acdc9c4.nat.nsloop.com/image/timg.jpg");
        news.setUrl("www.imooc.com");
        newsList.add(news);


        News news1 =new News();
        news1.setTitle("百度");
        news1.setDescription("百度网");
        news1.setPicUrl("http://8acdc9c4.nat.nsloop.com/image/timg.jpg");
        news1.setUrl("www.baidu.com");
        newsList.add(news1);

        News news2 =new News();
        news2.setTitle("淘宝");
        news2.setDescription("淘宝网");
        news2.setPicUrl("http://8acdc9c4.nat.nsloop.com/image/timg.jpg");
        news2.setUrl("www.taobao.com");
        newsList.add(news2);




        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String da = simpleDateFormat.format(date);
        newsMessage.setCreateTime(da);
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXML(newsMessage);

        return message;

    }


    public static String initlocationMessage(String toUserName, String fromUserName,String Query,String Location_X,String Location_Y){


        String message = null;

        String url = "http://api.map.baidu.com/place/v2/search?query=Query&location=Location_X,Location_Y&radius=2000&output=json&ak=TGXfSE1dvZzAYqPKHdwGL7jB5dC7GuVU";

        String urls = url.replace("Query",Query).replace("Location_X",Location_X).replace("Location_Y",Location_Y);


        JSONObject object = Weixutil.doGetStr(urls);
        System.out.println("object:"+object);
        Map map =(Map) object;
        String str = map.get("results").toString();
        List list=JSONObject.parseArray(str);
        System.out.println("str:"+list);

        List<Map> listResult = new ArrayList<>();

        if(list.size()>7){
            for(int i=0;i<8;i++){
                String str1 = list.get(i).toString();
                JSONObject jsonObject = JSONObject.parseObject(str1);
                Map map1 =(Map) jsonObject;
                listResult.add(map1);
            }
        }else {
            for(int i=0;i<list.size();i++){
                String str1 = list.get(i).toString();
                JSONObject jsonObject = JSONObject.parseObject(str1);
                Map map1 =(Map) jsonObject;
                listResult.add(map1);
            }
        }




        List<News> newsList=new ArrayList<News>();

        NewsMessage newsMessage =new NewsMessage();

        for(Map maps : listResult){
            News news =new News();
            news.setTitle(maps.get("name").toString()+maps.get("address").toString());
            news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。");

            String s = maps.get("location").toString();
            JSONObject jsonObject = JSONObject.parseObject(s);
            Map m = (Map)jsonObject;
            String lng = m.get("lng").toString();
            String lat = m.get("lat").toString();
            String ss= "http://api.map.baidu.com/panorama/v2?ak=TGXfSE1dvZzAYqPKHdwGL7jB5dC7GuVU&width=512&height=256&location=lng,lat&fov=180";
            String rs=ss.replace("lng",lng).replace("lat",lat);
            news.setPicUrl(rs);
            news.setUrl("www.imooc.com");
            newsList.add(news);
        }

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String da = simpleDateFormat.format(date);
        newsMessage.setCreateTime(da);
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());

        message = newsMessageToXML(newsMessage);



       return message;
    }





    /**
     * 组装图片消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage(String toUserName, String fromUserName){
        String message = null;

        Image Image = new Image();
        Image.setMediaId("7BlNnuxp6wAHb7ZFZC_MtY6LWwbXhvXf85X5k3wJ9aUXl5XyCnE_tB_E67vm3oC9");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String da = simpleDateFormat.format(date);
        imageMessage.setCreateTime(da);
        imageMessage.setImage(Image);
        message = imageMessageToXML(imageMessage);

        return message;
    }


    /**
     * 组装音乐消息
     * @param toUserName
     * @param fromUserName
     * @return
     */

    public static String initMusicMessage(String toUserName, String fromUserName){
        String message = null;

        Music music = new Music();
        music.setThumbMediaId("x0h2EZ6cCIp3cOv3becY0e21yD-s7RL45erqjkLiDfRAWR1f4l0-q8mGYCVff0ZQ");
        music.setTitle("see you again");
        music.setDescription("速7片尾曲");
//        music.setMusicUrl("http://8acdc9c4.nat.nsloop.com/music/Wiz Khalifa - See You Again.mp3");
        music.setMusicUrl("https://y.qq.com/portal/player.html");
//        music.setHQMusicUrl("http://8acdc9c4.nat.nsloop.com/music/Charlie Puth - See You Again (Piano Demo Version).mp3");
        music.setHQMusicUrl("https://y.qq.com/portal/player.html");
        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMsgType(MESSAGE_MUSIC);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String da = simpleDateFormat.format(date);
        musicMessage.setCreateTime(da);
        musicMessage.setMusic(music);
        message = musicMessageToXML(musicMessage);

        return message;
    }

}
