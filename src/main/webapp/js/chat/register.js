function submit(){
	var phone = $(":input[name='phone']").val();
	var loginPwd = $(":input[name='loginPwd']").val();
	var nickName = $(":input[name='nickName']").val();
	var name = $(":input[name='name']").val();
	var gender = $(":input[name='gender']").val();
	var homeAddress = $(":input[name='homeAddress']").val();
	var birDate = $(":input[name='birDate']").val();
	var job = $(":input[name='job']").val();
	var personSign = $(":input[name='personSign']").val();

    var data ={
    		"phone" : phone,
    		"loginPwd" : loginPwd,
    		"nickName" : nickName,
    		"name" : name,
    		"gender" : gender,
    		"homeAddress" : homeAddress,
    		"birDate" : new Date(birDate),
    		"job" : job,
    		"personSign" : personSign
    }
    $.ajax({
        url:path + "register.do",
        type:"POST",
        dataType:"json",
        data:data,
        success:function(data){
        	alert(data.msg);
            if(data.result){
                 setTimeout(function(){
                     //location.href="${REQUEST_BASE_PATH}v/main.do";
                },500);
            }
        },
        error:function(){
            alert("网络错误！");
        }
    });

}