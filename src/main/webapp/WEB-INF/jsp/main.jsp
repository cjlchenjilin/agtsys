<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>代理商管理系统</title>
<%@ include file="head.html"%>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body class="easyui-layout" fit="true">

	<div data-options="region:'north',title:'',split:false"
		style="height: 122px; background: url(images/top_1px.gif) repeat-x; overflow: hidden;">
		<div
			style="height: 120px; line-height: 120px; text-align: right; background: url(images/top_bg.gif) no-repeat; width: 1000px; float: left">
			&nbsp;&nbsp;&nbsp;&nbsp;欢迎，${login_user.username}</div>
		<!-- 链接菜单 -->
		<a href="javascript:void(0)" id="mb" class="easyui-menubutton"
			data-options="menu:'#mm',iconCls:'icon-edit'" style="float: right;">系统菜单</a>
		<div id="mm" style="width: 150px;">
			<div data-options="iconCls:'icon-edit'">修改密码</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-no'">退出系统</div>
		</div>

	</div>
	<div data-options="region:'south',title:'',split:false"
		style="height: 50px; text-align: center; line-height: 48px;"><div>版权所有，XXXX公司，电话：88888888</div></div>
	<div data-options="region:'west',title:'导航菜单',split:true"
		style="width: 150px;">
		<ul id="tt" class="easyui-tree">
		</ul>
	</div>
	<div data-options="region:'center',title:''"
		style="padding: 5px; background: #eee;">
		<!-- 选项卡 -->
		<div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false"></div>
	</div>
		<!-- 表单的容器 -->
	<div id="formbox"></div>
</body>
</html>