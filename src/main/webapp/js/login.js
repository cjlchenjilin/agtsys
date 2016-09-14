/**
 * 登录页面的js
 */
$(function(){
	$('#login-div').dialog({
		title: '系统登录',    
		width: 350,    
		height: 260,    
		closed: false,
		iconCls:"icon-ok",
		cache: false,     
		modal: true,
		buttons:[{
			text:'登录',
			handler:function(){
				//表单的提交和验证
				if(check_login_form()){
					$('#login-form').submit();
					$.messager.progress({text:"正在登录..."});
				}
			}
		},{
			text:'重置',
			handler:function(){
				$('#login-form').form('reset');
			}
		}]
	});
	//添加ajax验证验证码是否正确的键盘输入事件
	$("#captchca").keyup(function(){
		  var cpa = $(this).val();
		  if(cpa.length == 4){
			  $.ajax({
				   type: "POST",
				   url: "captchca/check",
				   data: "captchca="+cpa,
				   dataType:"text",
				   success: function(data){
				      if(data=="yes"){
				    	  $("#tip").html("<font color='green'>验证码正确</font>");
				      }else{
				    	  $("#tip").html("<font color='red'>验证码错误</font>"); 
				      }
				   }
			 });
		  }
	});
})
function check_login_form(){
	var flag = false;	
	//获取输入的值
	var usercode = $.trim($("#usercode").val());
	var userpassword = $.trim($("#userpassword").val());
	var patcha = $.trim($("#captchca").val());
	//错误提示框
	var $div_error= $("#tip");
	$div_error.html("");	
	if(usercode == "" || usercode == null){
		//插入光标
		 $("#usercode").focus();
		 $div_error.html("<b style='color:red;'>用户名为空！！</b>");	
	}else if(userpassword == "" || userpassword == null){
		 $("#userpassword").focus();
		 $div_error.html("<b style='color:red;'>密码为空！！</b>");	
	}else if(patcha == "" || patcha == null){
		 $("#captchca").focus();
		 $div_error.html("<b style='color:red;'>验证码为空！！</b>");	
	}else{
		flag = true;
	}
	return flag;
}