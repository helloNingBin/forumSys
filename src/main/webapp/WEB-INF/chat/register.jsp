<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/public/header.jsp"%>
<title>登陆</title>
<link rel="stylesheet" href="${REQUEST_BASE_PATH}css/public.css">
<script type="text/javascript" src="${REQUEST_BASE_PATH}js/chat/register.js"></script>
<style>
body{background:#FD4C5B; max-width:800px; margin:auto}
.logo{ padding-top:50px; padding-bottom:40px}
.logo img{ display:block; margin:auto}
.logoin{ padding:20px }
.logoin ul{}
.logoin li{ list-style:none; margin-bottom:40px}
.logoin input{ background:#fff; width:95%; padding-left:5%;  height:100px; border-radius:10px; font-size:35px;outline:none; border:none}
.logoin li button{background:#FEB836; width:100%;height:100px; border-radius:10px; font-weight:bolder; font-size:40px; color:#fff; border:none;
outline:none}
.logoin .enroll{ text-align:right;}
.logoin .enroll a{ color:#fff; font-size:30px}
.footer_in{ padding-top:120px}
.footer_in img{ display:block;margin:auto}
.logoin textarea {
    background: #fff;
    width: 95%;
    padding-left: 5%;
    height: 200px;
    border-radius: 10px;
    font-size: 35px;
    outline: none;
}
</style>

</head>
<body>
<div class="logoin">
	<div class="logo">
    	<img src="${REQUEST_BASE_PATH}images/logo.png" />
    </div>
	<ul>
    	<li>
        	<input type="text" placeholder="请输入电话号码" name="phone"/>
        </li>
        <li>
        	<input type="password" placeholder="请输入密码" name="loginPwd"/>
        </li>
        <li>
        	<input type="password" placeholder="请确认密码" />
        </li>
        
    	<li>
        	<input type="text" placeholder="请输入昵称" name="nickName"/>
        </li>        
    	<li>
        	<input type="text" placeholder="请输入真实姓名" name="name"/>
        </li>     
    	<li>
        	<input type="text" placeholder="请输入性别" name="gender"/>
        </li>      
    	<li>
        	<input type="text" placeholder="请输入家庭地址" name="homeAddress"/>
        </li>
    	<li>
        	<input type="date" value="请输入" name="birDate" />
        </li>       
    	<li>
        	<input type="text" placeholder="请输入工作" name="job"/>
        </li>      
    	<li>
        	<textarea name="personSign" placeholder="请输入个性签名" ></textarea>
        </li>      
        <li>
        	 <button onclick="submit()">注册</button>
        </li>
    </ul>
    <div class="enroll">
    	<a href="${REQUEST_BASE_PATH}toLogin.do">已有账户？直接登陆。</a>
    </div>
</div>
<div class="footer_in">
	<img src="${REQUEST_BASE_PATH}images/1.png" />
</div>
</body>
</html>
