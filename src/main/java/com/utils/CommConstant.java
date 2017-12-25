package com.utils;

import java.text.SimpleDateFormat;

public class CommConstant {
	/**
	 * 记录拦截前的访问URL，用于通过验证后的转跳
	 */
	public static final String JUMP_URL = "JUMP_URL";
	/**
	 * 系统基本访问路径=/forumSys/ /forumSys/test/alipay/oneToOne.do
	 */
	public static final String REQUEST_BASE_PATH = "REQUEST_BASE_PATH";
	/**
	 * Member的id
	 */
	public static final String LOGIN_MEMBER_ID_SESSION_KEY = "LOGIN_MEMBER_ID_SESSION_KEY";
	/**
	 * Member的对象
	 */
	public static final String LOGIN_MEMBER = "LOGIN_MEMBER";
	/**
     *yyyy/MM/dd HH:mm:ss
    */
	public static final SimpleDateFormat YMDHMS_DATEFORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	/**
	 * 每页的聊天记录条数
	 */
	public static final int MAX_MSG_LENGTH =7;

}