<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="" method="post" class="formTable" id="editBoxForm">
	<div id="tip"></div>
	<input id="id" name="id" type="hidden" value="${user.id}" />
	<table>
		<tr>
			<th width="100" style="text-align: right">登录账号：</th>
			<td><input id="usercode" type="text" name="usercode"
				value="${user.usercode}" class="easyui-validatebox"
				data-options="required:true" readonly="readonly" /></td>
		</tr>
		<tr>
			<th width="100" style="text-align: right">用户名称：</th>
			<td><input id="username" type="text" name="username"
				value="${user.username}" class="easyui-validatebox"
				data-options="required:true" readonly="readonly" /></td>
		</tr>
		<tr>
			<th width="100" style="text-align: right">角&nbsp;&nbsp;色：</th>
			<td><select id="roleid" name="roleid" class="easyui-validatebox"
				data-options="required:true">
					<c:forEach var="role" items="${roles}">
						<option value="${role.id}" <c:if test="${user.roleid == role.id}"> selected="selected"</c:if>>${role.rolename}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th style="text-align: right">是否启用：</th>
			<td><select id="isstart" name="isstart"
				class="easyui-validatebox" data-options="required:true">
					<c:if test="${user.isstart == 1 }">
						<option value="1" selected="selected">启用</option>
						<option value="0">不启用</option>
					</c:if>
					<c:if test="${user.isstart == 0 }">
						<option value="1">启用</option>
						<option value="0" selected="selected">不启用</option>
					</c:if>
			</select></td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	//全局的变量，用来保存原始的账号名称
	var orignal_name = $("#usercode").val();
	$(function() {
		$('#usercode').blur(function() {
			$("#tip").html("");
			if (orignal_name != $(this).val()) {
				check_usercode($(this).val());
			}
		});
	})
	function check_usercode(usercode) {
		var flag = false;
		if (usercode != "") {
			var $div_tip = $("#tip");
			$.ajax({
				url : 'user/check',
				type : 'get',
				data : {
					'usercode' : usercode
				},
				dataType : 'json',
				timeout : 5000,
				async : false,
				error : function() {
					alert("error");
				},
				success : function(data) {
					if (data.msg == "empty") {
						$div_tip.html("<font color='red'> 登录账号为空！！</font>");
					} else if (data.msg == "exist") {
						$div_tip.html("<font color='red'>登录账号已存在！！</font>");
					} else if (data.msg == "no_exist") {
						$div_tip.html("<font color='green'>登录账号可以使用！！</font>");
						flag = true;
					}
				}
			});
		}
		return flag;
	}
</script>