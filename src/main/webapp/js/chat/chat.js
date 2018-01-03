/*接受消息*/
function receive(headSrc,str,isHistory,time){
	if(time != ''){
		time = "<div class='time'>" + time + "</div>";
	}else{
		time = '';
	}
	var html="<div class='send'>" + time + "<div class='msg'><img src="+headSrc+" />"+
	"<p><i class='msg_input'></i>"+str+"</p></div></div>";
	upView(html,isHistory);
}
/*发送消息*/
function send(headSrc,str,isHistory,time){
	if(time != undefined && time != ''){
		time = "<div class='time'>" + time + "</div>";
	}else{
		time = '';
	}
	var html="<div class='show'>" + time + "<div class='msg'><img src="+headSrc+" />"+
	"<p><i class='msg_input'></i>"+str+"</p></div></div>";
	upView(html,isHistory);
}
/*更新视图*/
function upView(html,isHistory){
	if(isHistory){
		$('.message').prepend(html);
		$('.chat_popup').animate({scrollTop:5},200);
	}else{
		$('.message').append(html);
		$('.chat_popup').animate({scrollTop:10000},200);
	}
}
$(function(){
	$('.footer').on('keyup','input',function(){
		if($(this).val().length>0){
			$(this).next().css('background','#114F8E').prop('disabled',true);
		
		}else{
			$(this).next().css('background','#ddd').prop('disabled',false);
		}
	})
})
