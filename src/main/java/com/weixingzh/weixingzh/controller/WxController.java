package com.weixingzh.weixingzh.controller;



import com.weixingzh.weixingzh.util.MessageUti;
import com.weixingzh.weixingzh.util.Weixutil;
import com.weixingzh.weixingzh.util.WxCheckUtil;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;

@Controller
@RequestMapping("/in")
public class WxController {

    public static String x=null;

    public static String y=null;

    public static String query=null;



    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/weixin",method = RequestMethod.GET)
    @ResponseBody
    public void weixin(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String signature = request.getParameter("signature");
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce");
    String echostr = request.getParameter("echostr");

    PrintWriter out = response.getWriter();
    System.out.println("haode");

    if(WxCheckUtil.checkSignature(signature,timestamp,nonce)){

        out.print(echostr);

    }

    }

    @RequestMapping(value = "/weixin",method = RequestMethod.POST)
    @ResponseBody
    public void weitext(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        try {
            Map<String,String> map = MessageUti.xmlToMap(request);
            String fromUserName =map.get("FromUserName");
            String toUserName =map.get("ToUserName");
            String msgType =map.get("MsgType");
            String content =map.get("Content");

            String message = null;
            if(MessageUti.MESSAGE_TEXT.equals(msgType)){

                if("1".equals(content)){
                    //文本消息
                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.firstmenu());
                }else if("2".equals(content)){
                    //文本消息
                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.secondmenu());
//                    message = MessageUti.initNewsMessage(toUserName,fromUserName);
                }else if("3".equals(content)){
                    //图片消息
                    message = MessageUti.initImageMessage(toUserName,fromUserName);
                }else if("4".equals(content)){
                    //音乐消息
                    message = MessageUti.initMusicMessage(toUserName,fromUserName);
                }else if("5".equals(content)){
                    //第三方翻译
                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.fivedmenu());
                }else if("6".equals(content)){
                    //第三方翻译
                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.sixmenu());
                }else if (content.startsWith("翻译")){
                    String word = content.replaceAll("^翻译","").trim();
                    if("".equals(word)){
                        message = MessageUti.initText(toUserName,fromUserName,MessageUti.fivedmenu());
                    }else {
                        System.out.println("word:"+word);
                        message = MessageUti.initText(toUserName,fromUserName, Weixutil.translate(word));
                    }
                }else if (content.startsWith("查找")){
                    String word = content.replaceAll("^查找","").trim();
                    if("".equals(word)){
                        message = MessageUti.initText(toUserName,fromUserName,MessageUti.sixmenu());
                    }else {
                        System.out.println("word:"+word);
                        query=word;
                        message = MessageUti.initText(toUserName,fromUserName,"请点击click按钮获取信息。");
                    }
                }
                else if("?".equals(content) || "？".equals(content)){
                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.menuText());
                }else {
                    message = MessageUti.initText(toUserName,fromUserName,"请按照提示使用功能。");
                }

            }else if(MessageUti.MESSAGE_EVENT.equals(msgType)){
                String eventType = map.get("Event");
                System.out.println("eventType:"+eventType);
                if(MessageUti.MESSAGE_SUBSCRIBE.equals(eventType)){
                    //文本消息
                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.menuText());
                    //图文消息
//                    message = MessageUti.initNewsMessage(toUserName,fromUserName);
                }else if(MessageUti.MESSAGE_UNSUBSCRIBE.equals(eventType)){
                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.menuText());
                }else if(MessageUti.MESSAGE_CLICK.equals(eventType)){
//                    message = MessageUti.initText(toUserName,fromUserName,MessageUti.menuText());

                    if(x!=null&&y!=null&&query!=null){
                        message = MessageUti.initlocationMessage(toUserName,fromUserName,query,x,y);
                        x=null;
                        y=null;
                        query=null;
                    }else {
                        message = MessageUti.initText(toUserName,fromUserName,MessageUti.sixmenu());
                    }

                }else if(MessageUti.MESSAGE_VIEW.equals(eventType)){
                    String url = map.get("EventKey");
                    message = MessageUti.initText(toUserName,fromUserName,url);
                }else if(MessageUti.MESSAGE_SCANCODE.equals(eventType)){
                    String key = map.get("EventKey");
                    message = MessageUti.initText(toUserName,fromUserName,key);
                }
            }else if(MessageUti.MESSAGE_LOCATIONS.equals(msgType)){

                String label = map.get("Label");
                String Location_X = map.get("Location_X");
                String Location_Y = map.get("Location_Y");
                x=Location_X;
                y=Location_Y;
                System.out.println("Location_X:"+Location_X);
                System.out.println("Location_Y:"+Location_Y);
                message = MessageUti.initText(toUserName,fromUserName,label);

            }
            System.out.println("message:"+message);
            out.print(message);
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }

    }


}
