/**显示iframe窗口*/
function showIframe(url){
	$("#selectDataFrame").attr("src", contextPath + url);
	$('#selectData').addClass('out');
	stop();
}
/**隐藏iframe窗口*/
function hiddenIframe(isIframe){
	if(isIframe){
		$('#selectData',window.parent.document).removeClass('out');
	}else{
		$('#selectData').removeClass('out');
	}
	$("#selectDataFrame").attr("src", '');
	move();
}
/**禁止滑动*/
function stop(){
	document.body.style.position = 'fixed';
}
/**取消滑动限制**/
function move(){
	document.body.style.position = 'static';
}