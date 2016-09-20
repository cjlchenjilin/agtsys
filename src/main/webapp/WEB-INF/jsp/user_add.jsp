<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="" method="post" class="formTable" id="addBoxForm"
	modelAttribute="roles">
	<div id="tip"></div>
	<table>
		<tr>
			<th width="100" style="text-align: right">登录账号：</th>
			<td><input id="usercode" type="text" name="usercode"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" style="text-align: right">用户名称：</th>
			<td><input id="username" type="text" name="username"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" style="text-align: right">登录密码：</th>
			<td><input id="userpassword" type="password" name="userpassword"
				value="123456" class="easyui-validatebox"
				data-options="required:true" /> <span style="color: red;">*默认初始密码123456</span>
			</td>
		</tr>
		<tr>
			<th width="100" style="text-align: right">角&nbsp;&nbsp;色：</th>
			<td><form:select path="" id="roleid" name="roleid"
					class="easyui-validatebox" data-options="required:true">
					<option selected="selected" value="">--请选择--</option>
					<form:options items="${roles}" itemLabel="rolename" itemValue="id" />
				</form:select></td>
		</tr>
		<tr>
			<th style="text-align: right">是否启用：</th>
			<td><select id="isstart" name="isstart"
				class="easyui-validatebox" data-options="required:true">
					<option value="1" selected="selected">启用</option>
					<option value="0">不启用</option>
			</select></td>
		</tr>
	</table>
</form:form>
<script type="text/javascript">
	$(function() {
		$('#usercode').blur(function() {
			check_usercode($(this).val());
		});
	})
	//检查用户是否存在
	function check_usercode(usercode) {
		var flag = false;
		if (usercode != "") {
			var $div_tip = $("#tip");
			$.ajax({
				url : 'user/check',
				type : 'get',
				data : {'usercode' : usercode},
				dataType : 'text',
				timeout : 5000,
				async : false,
				error : function() {
					alert("error");
				},
				success : function(data) {
					if (data == "empty") {
						$div_tip.html("<font color='red'> 登录账号为空！！</font>");
					} else if (data == "exist") {
						$div_tip.html("<font color='red'>登录账号已存在！！</font>");
					} else if (data == "no_exist") {
						$div_tip.html("<font color='green'>登录账号可以使用！！</font>");
						flag = true;
					}
				}
			});
		}
		return flag;
	}
</script>
