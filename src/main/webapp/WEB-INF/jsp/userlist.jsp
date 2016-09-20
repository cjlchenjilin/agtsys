<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>用户管理 - 代理商管理系统</title>
<%@ include file="head.html"%>
<script type="text/javascript">
	$(function() {
		$("#dg")
				.datagrid(
						{
							url : "user/list",
							columns : [ [
									{
										checkbox : true
									},
									{
										field : 'id',
										title : 'ID',
										hidden: true
									},
									{
										field : 'usercode',
										title : '登录账号'
									},
									{
										field : 'username',
										title : '用户名称'
									},
									{
										field : 'rolename',
										title : '角色'
									},
									{
										field : 'creationtime',
										title : '创建时间',
										formatter :  function(value) {
											return formatDate(value);
										}
									},
									{
										field : 'isstart',
										title : '是否启用',
										align : 'center',
										formatter : function(value, row, index) {
											//formatter  表格格式化函数，返回3个参数：字段值，该行数据，该行索引
											switch (value) {
											case 0:
												return "未启用";
												break;
											case 1:
												return "启用";
												break;
											default:
												return "-";
												break;
											}
										},
										styler : function(value, row, index) {
											if (value == 0) {
												return 'background-color:#ffee00;color:red;';
											}
										}
									}] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : addUser
							}, '-', {
								text : '修改',
								iconCls : 'icon-edit',
								handler : editUser
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : delUser
							}, ],
							fitColumns : true,//自适应宽度 
							striped : true,//列表是否有间隔底色
							resizable : true,//列尺寸可调节
							singleSelect : true,
							loadMsg : '正在努力加载中..',
							rownumbers : true, //行前显示行号
							idField : 'id', //指定选中时返回的维度字段名，如id

							pagination : true,//是否显示分页
							pageSize : 5,//每页条数 
							pageList : [ 5,10,15 ]
						});
	});
	//格式化日期
	function formatDate(value){
		if(value==null)
			return;
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
	}
	//新增函数
	function addUser() {
		$("#formbox").dialog({
			title : "新增",
			width : 400,
			height : 300,
			resizable : true,
			modal : true,
			href : "user/add",
			cache : false,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					//ajax提交数据 form插件  
					$('#addBoxForm').form('submit', {
						url : "user/add",
						onSubmit : function() {
							//验证表单
							var isValid = $(this).form('validate');
							var usercode = $("#usercode").val();
							var flag = check_usercode(usercode);
							return isValid && flag; // 如果isValid == false 则阻止表单提交，否则提交表单
						},
						success : function(data) {
							//状态的判断
							//1，提交成功，关闭 进度条， 提示新增成功，刷新页面
							//2，提交失败，关闭进度条，提示提交失败
							var data = $.parseJSON(data);
							if (data.msg == 1) {//提交成功
								//提示用户新增成功
								$.messager.show({
									title : '提示',
									msg : "新增成功"
								});
								search_user();//刷新数据 	 
							} else {
								//提示用户新增失败
								$.messager.show({
									title : '提示',
									msg : "新增失败"
								});
							}
							$("#formbox").dialog("close");
						}
					});
				}
			}, {
				text : '取消',
				iconCls : 'icon-undo',
				handler : function() {
					$("#formbox").dialog("close");
				}
			} ]
		});
	}
	//修改函数
	function editUser() {
		//判断是否选择了数据记录
		var row = $("#dg").datagrid('getSelected');
		if (row == null) {
			$.messager.alert('提示','你还没有选择任何数据记录','warning');
			return;
		}
		//选中一条记录
		var id = row.id;
		$("#formbox").dialog({
			title : "修改",
			width : 300,
			height : 260,
			resizable : false,
			modal : true,
			href : "user/load/" + id,
			cache : false,
			buttons : [ {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					//提交数据 form插件  
					$('#editBoxForm').form('submit', {
						url : "user/update/"+id,
						onSubmit : function() {
							var isValid = $(this).form('validate');
							//提交表单时名称
							/* var usercode = $("#usercode").val();
							var flag = true; //默认原来名称，通过验证
							if (orignal_name != usercode) {
								flag = check_usercode(usercode);
							} */
							return isValid; // 如果isValid == false 则阻止表单提交，否则提交表单
						},
						success : function(data) {
							var data = $.parseJSON(data);
							if (data.msg == 1) {//提交成功
								//提示用户修改成功
								$.messager.alert('提示','修改成功','info');
								search_user();//刷新数据 
								//取消所有页面中元素的选中状态
								$('#dg').datagrid('clearChecked');
							} else {
								//提示用户修改失败
								$.messager.alert('提示','修改失败','error');
							}
							$("#formbox").dialog("close");
						}
					});
				}
			}, {
				text : '取消',
				iconCls : 'icon-undo',
				handler : function() {
					$("#formbox").dialog("close");
				}
			} ],
		});
	}
	//delRole 删除函数
	function delUser() {
		//判断是否选中
		//$.ajax 删除数据，刷新datagrid 
		//判断是否选择了数据记录
		var row = $("#dg").datagrid('getSelected');
		if (row == null) {
			$.messager.alert('提示','你还没有选择任何数据记录','warning');
			return;
		}
		var id = row.id;
		$.messager.confirm('删除提示', '你确定要删除'+row.usercode+'吗?', function(r) {
			if (r) {
				$.get("user/delete/" + id, function(data) {
					var data = $.parseJSON(data);
					if (data.msg == "1") {
						$.messager.alert('提示','删除成功','info');
						search_user(); //刷新当前页面
					} else {
						$.messager.alert("提示", "删除失败", "error");
					}
				});
				//取消所有页面中元素的选中状态
				$('#dg').datagrid('clearChecked');
			}
		});
	}
//搜索方法
function search_user(){
	var user = {
			username:$('#username').val(),
			roleid:$('#roleid').val(),
			isstart:$('#isstart').val()
	};
	$('#dg').datagrid('options').queryParams = user;  	   
    $("#dg").datagrid('reload');  
}
</script>
</head>
<body>
	<div class="easyui-panel" data-options="title:'查询',width:'100%',height:100,collapsible:true">
	    <form:form id="search_form" action="" modelAttribute="roles">
	        <table class="searchTb">
	            <tr>
	                <th>用户名称：</th>
	                <td><input type="text" id="username" name="username" /> </td>
	                <th>角色：</th>
	                <td><form:select path="" id="roleid" name="roleid"> 
	                		<option selected="selected" value="">--请选择--</option>
	                		<form:options items="${roles}" itemLabel="rolename" itemValue="id" />
	                	</form:select>
	                </td>
	                <th>是否启用：</th>
	                <td>
	                	<select name="isstart" id="isstart">
							<option value="" selected="selected">--请选择--</option>			
			 				<option value="1">启用</option>
			 				<option value="0">未启用</option>
	 				</select>
	                </td>
	                <td>
	                    &nbsp;<a href="javascript:search_user();" class="easyui-linkbutton m10" title="查询" data-options="iconCls:'icon-search'">查询</a>
	                </td>
	            </tr>
	        </table>
	    </form:form>
	</div>
	<table id="dg"></table>
	<div id="formbox"></div>
</body>
</html>