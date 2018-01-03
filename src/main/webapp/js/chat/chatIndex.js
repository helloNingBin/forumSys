$(function(){
	bindChatWindowClick();//绑定好友点击弹出聊天窗口
})
function bindChatWindowClick(){
	$(".group .lf").unbind("click").click(function(e){
		$('.message').html("");//清空所有聊天记录
	     e.stopPropagation();//防冒泡
	     //绑定发送者memberId
	     var toMemberId = $(this).attr("memberId");
	     //show();
	     showIframe("v/one/toChatWindow.do?toMemberId=" + toMemberId);
	});
}
//显示聊天框
function show(){
	  $(".popup_bg").addClass("out");
	  $(".chat_popup").addClass("out");
	  $(".footer").css("bottom","0");
	  $(".chat_popup").css("position","absolute");
}



