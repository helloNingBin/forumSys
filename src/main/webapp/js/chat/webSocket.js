var Chat = {};

Chat.socket = null;

Chat.connect = (function(host) {
	//初始化WebSocket实例
	if ('WebSocket' in window) {
		Chat.socket = new WebSocket(host);
	} else if ('MozWebSocket' in window) {
		Chat.socket = new MozWebSocket(host);
	} else {
		//Console.log('Error: WebSocket is not supported by this browser.');
		return;
	}

	Chat.socket.onopen = function() {
		//Console.log('Info: WebSocket connection opened.');
		$('.footer p').click(function(){
			send(path + "images/touxiangm.png",$(this).prev().val(),false);
			Chat.sendMessage();
		})
	};

	Chat.socket.onclose = function() {
		//document.getElementById('chat').onkeydown = null;
		//Console.log('Info: WebSocket closed.');
	};
    //接收信息
	Chat.socket.onmessage = function(message) {
 		var msg = eval('(' + message.data + ')');
		receive(path + "images/touxiangm.png",msg.content,false);
		//Console.log(message.data);
	};
});

Chat.initialize = function() {
	if (window.location.protocol == 'http:') {
//		Chat.connect('ws://' + window.location.host + '/websocket/chat');
		Chat.connect('ws://' + wsPath +'websocket/chat');
	} else {
//		Chat.connect('wss://' + window.location.host + '/websocket/chat');
		Chat.connect('wss://' + wsPath + 'websocket/chat');
	}
};
//发送信息
Chat.sendMessage = (function() {
	var message = getSendMsg();
	Chat.socket.send(JSON.stringify(message));
	$("#msgContent").val("");
});
$(function(){
	Chat.initialize();
});
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