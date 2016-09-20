<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="" modelAttribute="role" method="post" class="formTable"  id="editBoxForm">
    <div id="tip"></div>
    <table>
        <tr>
            <th width="100" style="text-align: right">角色名称：</th>
            <td><form:input path="rolename" id="rolename"  class="easyui-validatebox" data-options="required:true"  /> </td>
        </tr>
        <tr>
            <th style="text-align: right">是否启用：</th>
            <td>
                <form:radiobutton path="isstart" value="1"  id="status1" /><label for="status1">启用</label>
                <form:radiobutton path="isstart"  value="0" id="status2" /><label for="status2">禁用</label>
            </td>
        </tr>
    </table>
</form:form>
<script type="text/javascript">
//全局的变量，用来保存原始的角色名称
var orignal_name = $("#rolename").val();
$(function(){
	//alert(orignal_name);
	$('#rolename').blur(
		function(){
			$("#tip").html("");
			if(orignal_name != $(this).val()){
				check_rolename($(this).val());
			}
	});
})
function check_rolename(rolename) {
	var flag = false;
		if (rolename != "") {
			var $div_tip = $("#tip");
			//清除原来的提示
			$div_tip.html("");
			$.ajax({
				url : 'role/check',
				type : 'get',
				data : {'rolename' : rolename},
				dataType : 'json',
				timeout : 5000,
				async:false,
				error : function() {
					alert("error");
				},
				success : function(data) {
					if (data.msg == "empty") {
						$div_tip.html("<font color='red'> 角色名称为空！！</font>");
					} else if (data.msg == "exist") {
						$div_tip.html("<font color='red'>角色名称已存在！！</font>");
					} else if (data.msg == "no_exist") {
						$div_tip.html("<font color='green'>角色名称可以使用！！</font>");
						flag = true;
					}
				}
			});
		}
	return flag;
}
</script>