package com.xie.config;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Author:Eric
 * DATE:2023/6/3-17:00
 * Decription: 建立ServerEndpoint 的java类，能够接受客户端发送过来的信息和发送给客户端的信息
 */
@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //在线人数
    public static int onlineNumber = 0;
    //以用户的姓名为key websocket为对象保存起来
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    //会话
    private Session session;
    //用户名称
    private String username;

    //建立连接
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        onlineNumber++;
        logger.info("现在来连接的客户id:" + session.getId() + "用户名：" + username);
        this.username = username;
        this.session = session;
        logger.info("有新连接加入！当前在线人数：" + onlineNumber);
        try {
            //messageType 1代表上线  2代表下线 3代表在线名单 4代表普通消息
            //先给所有人发送通知 说我上线了
            Map<String, Object> map = new HashMap();
            map.put("messageType", 1);
            map.put("username", username);
            sendMessageAll(JSON.toJSONString(map));
            //把自己的信息加入到map中
            clients.put(username, this);
            //给自己发一条消息，告诉自己现在都有谁在线
            Map<String, Object> map1 = new HashMap();
            map1.put("messageType", 3);
            //移除掉自己
            Set<String> set = clients.keySet();
            map1.put("onlineUsers", set);
            sendMessageTo(JSON.toJSONString(map1), username);
        } catch (IOException e) {
            logger.info(username + "上线的时候通知所有人发生了错误");
        }


    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        clients.remove(username);
        try{
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String,Object> map = new HashMap<>();
            map.put("messageType",2);
            map.put("onelineUsers",clients.keySet());
            map.put("username",username);
            sendMessageAll(JSON.toJSONString(map));

        } catch (IOException e) {
            logger.info(username+"下线的时候通知所有人发生了错误");
        }
        logger.info("有连接关闭！ 当前在线人数" + onlineNumber);

    }

    /**
     * 收到客户端的消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){
        try {
            logger.info("来自客户端消息：" + message+"客户端的id是："+session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String fromusername = jsonObject.getString("username");
            String tousername = jsonObject.getString("to");
            //如果不是发给所有，那么就发给某一个人
            //messageType 1代表上线 2代表下线 3代表在线名单  4代表普通消息
            Map<String,Object> map1 =  new HashMap();
            map1.put("messageType",4);
            map1.put("textMessage",textMessage);
            map1.put("fromusername",fromusername);
            if(tousername.equals("All")){
                map1.put("tousername","所有人");
                sendMessageAll(JSON.toJSONString(map1));
            }
            else{
                map1.put("tousername",tousername);
                sendMessageTo(JSON.toJSONString(map1),tousername);
            }
        }
        catch (Exception e){
            logger.info("发生了错误了");
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务器端发生了错误" + error.getMessage());
    }

    /**
     * 给 某个人发送消息
     *
     * @param message
     * @param ToUserName
     */
    public void sendMessageTo(String message, String ToUserName) throws IOException {
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName)) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineNumber() throws IOException {
        return onlineNumber;
    }

}
