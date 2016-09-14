<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=request.getContextPath() %>/" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div id="login-div">
		<div id="tip">
			<font color="red">${msg}</font>
		</div>
		<form id="login-form" action="user/login" method="post" class="p30">  
		  <div class="h30 lh28"> 
		         <label for="usercode">用户名:</label>  
		         <input class="easyui-validatebox" type="text" id="usercode" name="usercode" data-options="required:true" /> 		         
		  </div>   
		  <div class="h30 lh28">  
		        <label for="userpassword">密&nbsp;码:</label>       
		        <input class="easyui-validatebox" type="password" id="userpassword" name="userpassword" data-options="required:true" />    
		  </div>
		   <div class="h30 lh28">  
		        <label for="captchca">验证码:</label>       
		        <input class="easyui-validatebox" type="text" id="captchca" name="captchca" data-options="required:true" />
		        <img alt="单击刷新" src="captchca/get" onclick="this.src='captchca/get?'+Math.random()" />    
		  </div>      
		  </form>
	</div>
</body>
</html>