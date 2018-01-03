<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/public/header.jsp"%>
<link rel="stylesheet" type="text/css" href="${REQUEST_BASE_PATH}css/chat.css?ver=1" />
<link rel="stylesheet" type="text/css" href="${REQUEST_BASE_PATH}css/poput.css?ver=1" />
<script src="${REQUEST_BASE_PATH}js/flexible.js"></script>
<script src="${REQUEST_BASE_PATH}js/chat/chat.js?ver=11" type="text/javascript"></script>
<div class="chat_popup" toId="${toMember.id }">
		<header class="header">
			<a class="back" href="javascript:hiddenIframe(true)"></a>
			<h5 class="tit">${toMember.nickName }</h5>
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
<script>
	var flag = true;
	$(function(){
		//初始化聊天信息
		getMsg('${toMember.id }','');
		$('.footer p').click(function(){
			send(path + "images/touxiangm.png",$(this).prev().val(),false);
			window.parent.Chat.sendMessage();
		})
		$(".chat_popup").css("height",$(".chat_popup").height());//聊天的弹出框与window窗口高度保持一致
		//聊天界面内容的固定高度
		$(".chat_popup").scroll(function() {
	    	var chatHeight = $(".chat_popup").height() - $(".header").height() - $(".footer").height();
			if($(".chat_popup").scrollTop() < 0.5 && flag){
				flag = false;
			    getMsg($(".chat_popup").attr("toId"),$("#minId").val());
			 }
			});
	});
	//异常获取聊天信息
	function getMsg(firendMemberId,minId){
	    //获取聊天初始化信息
	    var data = {
	   		 firendMemberId : firendMemberId,
	   		 minId:minId
	    }
	    $.ajax({
	        url:path + "v/one/getMsg.do",
	        type:"POST",
	        data:data,
	        success:function(data){
	       	if(data.result){
	       		var length = data.msgList.length;
	       		if(length > 0){
	       			flag = (length == 7);
	       			for(var i = 0;i < length;i++){
	       				var msg = data.msgList[i];
	       				var time = '';
	       				if(msg.timeRemark == '1'){//这条信息是否显示时间
	       					time = msg.createDateFormat;
	       				}
	       				if(firendMemberId == msg.toId){//接收
	       					receive(path + "images/touxiangm.png",msg.content,true,time);
	       				}else{//发送
	       					send(path + "images/touxiangm.png",msg.content,true,time);
	       				}
	       				if(i == (length - 1)){
	       					$("#minId").val(msg.id);
	       				}
	       			}
	       			if(minId == ''){
	       				$(".chat_popup out").animate({scrollTop:50000},200);//移动到聊天底部
	       			}
	       		}else{
	       			flag = false;
	       		}
	       	}else{
	       		alert(data.msg);
	       	}
	        },
	        error:function(){
	            alert("网络错误！");
	        }
	    });
	}
	/*
	 * 发送信息的内容(json格式)
	 * fromId,toId,content
	 * 
	 */
	function getSendMsg(){
		var content = $("#msgContent").val();
		var toId = $(".chat_popup").attr("toId");
		var data = {
			"content" : content,
			"fromId" : memberId,
			"toId" : toId
		}
		return data;
	}
</script>
