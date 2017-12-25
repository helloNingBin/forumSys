<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/public/header.jsp"%>
<title>单笔转账到支付宝测试</title>
<link rel="stylesheet" type="text/css" href="${REQUEST_BASE_PATH}css/poput.css" />
<script type="text/javascript">
  function pay(){
	  alert("pay method in main")
  }
  function show(){
	  $(".popup_bg").addClass("out");
	  $(".chat_popup").addClass("out");
	  $(".footer").css("bottom","0");
  }
  function hide(){
	  $(".cp_popup_bg").removeClass("out");
	  $(".chat_popup").removeClass("out");
	  $(".footer").css("bottom","-200px");
  }
</script>
</head>
<body>
金额：<input type="text" id="amount">
<input type="button" value="付款" onclick="pay()"/>
<section>
   付款信息显示：<div id="payShow"></div>
</section>
<input type="button" value="显示聊天窗口" onclick="show()">
<div class="popup_bgw"></div>
<%@include file="/WEB-INF/chat/talkWindow.jsp"%>
</body>
</html>
