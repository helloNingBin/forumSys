$(function(){
	bindChatWindowClick();//绑定好友点击弹出聊天窗口
})
function bindChatWindowClick(){
	$(".group .lf").unbind("click").click(function(e){
		$('.message').html("");//清空所有聊天记录
	     e.stopPropagation();//防冒泡
	     show();
	     //绑定发送者memberId
	     var toMemberId = $(this).attr("memberId");
	     $(".chat_popup").attr("toId",toMemberId);
	     //聊天窗口，显示聊天人的姓名
	     var nickName = $(this).children("h1").html();
	     $(".header .tit").html(nickName);
	     getMsg(toMemberId,'');
	});
}
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
//显示聊天框
function show(){
	  $(".popup_bg").addClass("out");
	  $(".chat_popup").addClass("out");
	  $(".footer").css("bottom","0");
	  $(".chat_popup").css("position","absolute");
}
//隐藏聊天框
function hide(){
	  $(".popup_bg").removeClass("out");
	  $(".chat_popup").removeClass("out");
	  $(".footer").css("bottom","-200px");
	  $(".chat_popup").css("position","fixed");
}



