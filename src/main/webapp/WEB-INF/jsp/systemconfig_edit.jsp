<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="" method="post" class="formTable" id="editBoxForm">
	<div id="tip"></div>
	<input id="id" name="id" type="hidden" value="${systemconfig.id}" />
	<table>
		<tr>
			<th width="100" style="text-align: right">配置类型：</th>
			<td><input id="configtypename" type="text" name="configtypename" value="${systemconfig.configtypename}"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<c:if test="${systemconfig.configtype==2 or systemconfig.configtype==3 or systemconfig.configtype==4 or systemconfig.configtype==7}">
			<tr>
				<th width="100" style="text-align: right">配置数值：</th>
				<td><input id="configvalue" type="text" name="configvalue" value="${systemconfig.configvalue}"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
		</c:if>
		<tr>
			<th style="text-align: right">是否启用：</th>
			<td><select id="isstart" name="isstart"
				class="easyui-validatebox" data-options="required:true">
					<c:if test="${systemconfig.isstart == 1 }">
						<option value="1" selected="selected">启用</option>
						<option value="0">不启用</option>
					</c:if>
					<c:if test="${systemconfig.isstart == 0 }">
						<option value="1">启用</option>
						<option value="0" selected="selected">不启用</option>
					</c:if>
			</select></td>
		</tr>
	</table>
</form>