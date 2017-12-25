<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<%@ include file="/WEB-INF/public/header.jsp"%>
<title>单笔转账到支付宝测试</title>
<script type="text/javascript">
  function pay(){
	  var data = {
			  amount : $("#amount").val()
	  }
		$.ajax({
			   type: "POST",
			   url: "${REQUEST_BASE_PATH}test/alipay/oneToOne.do",
			   data: data,
			   success: function(msg){
                   $("#payShow").html(msg.msg);
			   },
			   error: function(msg){
				   $("#payShow").html(msg.msg);
			   }
		});
  }
</script>
</head>
<body>
金额：<input type="text" id="amount">
<input type="button" value="付款" onclick="pay()"/>
<section>
   付款信息显示：<div id="payShow"></div>
</section>
</body>
</html>