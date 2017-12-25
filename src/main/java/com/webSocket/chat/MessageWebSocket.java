/**
 * 
 */
package com.webSocket.chat;

/**
 * @author bin
 *
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.entity.LoginTrace;
import com.entity.Member;
import com.entity.chat.OneToOneMsg;
import com.exception.ChatException;
import com.google.gson.Gson;
import com.service.chat.WebSocketServiceInterface;
import com.service.member.MemberServiceInterface;
import com.utils.CommConstant;
import com.utils.SpringBeansUtil;
import com.webSocket.HttpSessionConfigurator;
@ServerEndpoint(value = "/websocket/chat",configurator=HttpSessionConfigurator.class)
public class MessageWebSocket {
    private static final Logger log = Logger.getLogger(MessageWebSocket.class);
    private WebSocketServiceInterface webService;
    /**
     * 保存所有连接上的webSocket，K为会员id，V为session
     */
    private static final Map<Long,Session> connectionMap = new HashMap<Long, Session>();

    private Member member;
    private Session session;

    public MessageWebSocket() {
    	this.webService = (WebSocketServiceInterface) SpringBeansUtil.getBean("webSocketService");
  }


    /**
     * @param session
     * 新的连接
     * @throws Exception 
     */
    @OnOpen
    public void start(Session session,EndpointConfig config) throws Exception {
        this.session = session;
        //保存session,这里的session不可能为null的了，因为已经做了request监听
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getSimpleName());
        this.member = (Member) httpSession.getAttribute(CommConstant.LOGIN_MEMBER);
        connectionMap.put(member.getId(), session);
        String message = String.format("* %s %s", this.member.getNickName(), "has joined.");
        System.out.println("start session:" + message);
    }


    /**
     * 断开连接
     */
    @OnClose
    public void end() {
        //从map中移除session
    	connectionMap.remove(this.member.getId());
        String message = String.format("* %s %s",member.getName(), "has disconnected.");
//        broadcast(message);
        System.out.println(message);
    }


    /**
     * 接收信息
     * @param message
     */
    @OnMessage
    public void incoming(String message) {
        // Never trust the client
        broadcast(message);
    }




    /**
     * 网络异常
     * @param t
     * @throws Throwable
     */
    @OnError
    public void onError(Throwable t) throws Throwable {
        log.error("Chat Error: " + t.toString(), t);
    }


    private void broadcast(String msg) {
    	if(StringUtils.isNotBlank(msg)){
    		Gson gson = new Gson();
    		OneToOneMsg msgBean = gson.fromJson(msg, OneToOneMsg.class);
    		try {
    			Session receiveSession = connectionMap.get(msgBean.getToId());
    			if(receiveSession == null){
    				msgBean.setIsSend(0);
    			}else{
    				msgBean.setIsSend(1);
    				receiveSession.getBasicRemote().sendText(msg);
    			}
    			webService.updateChatTx(msgBean, null);
    			System.out.println("msg:" + msgBean);
			}  catch (ChatException e) {
				log.error(e.getMessage(), e);
				e.printStackTrace();
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				e.printStackTrace();
			}
    	}
    }
}