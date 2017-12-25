function login(){
	var phone = $(":input[name='phone']").val();
	var loginPwd = $(":input[name='loginPwd']").val();

    var data ={
    		"phone" : phone,
    		"loginPwd" : loginPwd
    }
    $.ajax({
        url:path + "login.do",
        type:"POST",
        dataType:"json",
        data:data,
        success:function(data){
            if(data.result){
               window.location.href = path + "v/toIndex.do";
               return;
            }
            alert(data.msg);
        },
        error:function(){
            alert("网络错误！");
        }
    });

}