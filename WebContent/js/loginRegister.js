/*
 * 注册
 */
$(function(){
	//	判断用户名是否存在
	$("#username").blur(function(){
		var username = $("#username").val();
		if(username==''){
			$("#checkResult").text("用户名不能为空!").css("color","red");
		}else{
			//	$.post(URL,data,callback);
			$.post("user/checkUser",
			{
				username:username
			},
			function(data,status){
				$("#checkResult").text(data).css("color","red");
			});
		}
	});
	//	判断两次密码是否一致，判断验证码输入是否正确
	$("#register").click(function(){
		//	刷新验证码
		var rand = parseInt(Math.random()*(101)+0);
	    var newsrc = $("#verifycode")[0].src+"\/"+rand;
		$("#verifycode").attr("src",newsrc);
		//	验证
		if($("#checkResult").text()=="用户名已存在!"){
			alert("用户名已存在!");
			return false;
		}else if($("#password").val()!=$("#repassword").val()){
			alert("两次密码输入不一致")
			return false;
		}else{
			$.post("user/validate",
					{
						veritycode:$("#verifycodes").val()
					},
					function(data,status){
						if(data=="false"){
							alert("验证码输入错误!");
							return false;
						}else{
							$("form").submit();
						}
					});
			}
	});
	//	点击刷新验证码
	$("#verifycode").click(function(){
	    var rand = parseInt(Math.random()*(101)+0);
	    var newsrc = $("#verifycode")[0].src+"\/"+rand;
		$("#verifycode").attr("src",newsrc);
	});
})

/*
 * 登录
*/
$(function(){
	$("#login").click(function(){
		$.ajax({
		    type: "post",
		    url: "user/login",
		    data: {username:$("#loginAccount").val(), password:$("#loginPassword").val()},
		    success: function(data,status){
		    		if(data=="登录成功!"){
		    			var choose = confirm(data+",点击确认跳转至主页。");
		    			if(choose){
		    				window.location.href="index.jsp";
		    			}
		    		}else{
		    			alert(data);
		    			return false;
		    		}
		        }
		});
	});
});
















