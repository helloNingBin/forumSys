<%@page import="com.utils.CommConstant"%>
<%@ page language="java" pageEncoding="UTF-8" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href=${REQUEST_BASE_PATH}favicon.jpg />
<link rel="bookmark" href="${REQUEST_BASE_PATH}favicon.jpg" type="image/x-icon"　/>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
 --><meta name="viewport" content="width=750,target-densitydpi=340,user-scalable=no">
<meta name="format-detection" content="telephone=no,email=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-touch-fullscreen" content="yes">
<%
String path = request.getContextPath();  
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";  
pageContext.setAttribute("basePath", basePath);
Object memberId = session.getAttribute(CommConstant.LOGIN_MEMBER_ID_SESSION_KEY);
//webSocket基本路径
String wsPath = request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="${REQUEST_BASE_PATH}js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
   var path = '<%=basePath%>';
   var memberId = '<%=memberId%>';
   var wsPath = '<%=wsPath%>';
</script>
