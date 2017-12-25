<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${REQUEST_BASE_PATH}/css/chat.css" />
<script src="${REQUEST_BASE_PATH}js/flexible.js"></script>
<script src="${REQUEST_BASE_PATH}js/chat/chat.js?ver=11" type="text/javascript"></script>
<div class="chat_popup" toId="">
		<header class="header">
			<a class="back" href="javascript:hide()"></a>
			<h5 class="tit">追星星的人</h5>
			<div class="right">资料</div>
		</header>
		<div class="message">

		</div>
		<div class="footer">
			<img src="${REQUEST_BASE_PATH}images/hua.png" alt="" />
			<img src="${REQUEST_BASE_PATH}images/xiaolian.png" alt="" />
			<input type="text" id="msgContent"/>
			<p>发送</p>
		</div>
</div>
<input type="hidden" id="minId" value=""/>
