<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>角色管理 - 代理商管理系统</title>
<%@include file="head.html" %>
<script type="text/javascript">
	$(function() {
		$("#dg")
				.datagrid(
						{
							url : "role/list",
							columns : [ [
									{
										checkbox : true
									},
									{
										field : 'id',
										title : 'ID'
									},
									{
										field : 'rolename',
										title : '角色名称'
									},
									{
										field : 'createdby',
										title : '创建者'
									},
									{
										field : 'lastupdatetime',
										title : '上次修改时间',
										formatter : function(value) {
											if (value == null)
												return;
											var date = new Date(value);
											var year = date.getFullYear()
													.toString();
											var month = (date.getMonth() + 1);
											var day = date.getDate().toString();
											var hour = date.getHours()
													.toString();
											var minutes = date.getMinutes()
													.toString();
											var seconds = date.getSeconds()
													.toString();
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
											return year + "-" + month + "-"
													+ day + " " + hour + ":"
													+ minutes + ":" + seconds;
										}
									},
									{
										field : 'creationtime',
										title : '创建时间',
										formatter : function(value, row, index) {
											var unixTimestamp = new Date(value);
											return unixTimestamp
													.toLocaleString();
										}
									},
									{
										field : 'isstart',
										title : '是否激活',
										align : 'center',
										formatter : function(value, row, index) {
											//formatter  表格格式化函数，返回3个参数：字段值，该行数据，该行索引
											switch (value) {
											case 0:
												return "冻结";
												break;
											case 1:
												return "正常";
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
									}, ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : addRole
							}, '-', {
								text : '修改',
								iconCls : 'icon-edit',
								handler : editRole
							}, '-', {
								text : '删除',
								iconCls : 'icon-remove',
								handler : delRole
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
							pageList : [ 5, 10, 15 ]
						});
	});
	//新增函数
	function addRole() {
		$("#formbox").dialog({
			title : "新增",
			width : 300,
			height : 200,
			resizable : true,
			modal : true,
			href : "html/role_add.html",
			cache : false,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					//提交数据 form插件  
					$('#addBoxForm').form('submit', {
						url : "role/add",
						onSubmit : function() {
							var isValid = $(this).form('validate');
							var rolename = $("#rolename").val();
							var flag = check_rolename(rolename);
							return isValid && flag;
						},
						success : function(data) {
							var data = $.parseJSON(data);
							if (data.msg == 1) {//提交成功
								//提示用户新增成功
								$.messager.alert('提示','新增成功','info');
								$("#dg").datagrid("reload");//刷新数据 	 
							} else {
								//提示用户新增失败
								$.messager.alert('提示','新增失败','error');
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
	function editRole() {
		//判断是否选择了数据记录
		var row = $("#dg").datagrid('getSelected');
		if (row == null) {
			$.messager.alert('提示','你还没有选择任何数据记录','warning');
			return;
		}
		var id = row.id;
		$("#formbox").dialog({
			title : "修改",
			width : 300,
			height : 200,
			resizable : true,
			modal : true,
			href : "role/load/" + id,
			cache : false,
			buttons : [ {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					//提交数据 form插件  
					$('#editBoxForm').form('submit', {
						url : "role/" + id + "/update",
						onSubmit : function() {
							var isValid = $(this).form('validate');
							//提交表单时名称
							var rolename = $("#rolename").val();
							var flag = true; //默认原来名称，通过验证
							if (orignal_name != rolename) {
								flag = check_rolename(rolename);
							}
							if (!(isValid && flag)) {
								$.messager.progress('close'); //如果验证不通过，则隐藏进度条  
							}
							return isValid && flag; // 如果isValid == false 则阻止表单提交，否则提交表单
						},
						success : function(data) {
							var data = $.parseJSON(data);
							if (data.msg == 1) {//提交成功
								//提示用户修改成功
								$.messager.alert('提示','修改成功','info');
								$("#dg").datagrid("reload");//刷新数据 
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
		/* onLoad:function(){
			//form 插件里面的一个加载数据一个方法
			//load 
			//$(select).form('load',url)
			$('#editBoxForm').form('load',"role/"+id);
			orignal_name = $("#rolename").val();
			alert("load");
			alert(orignal_name);
		} */
		});
	}
	//delRole 删除函数
	function delRole() {
		//判断是否选中
		//$.ajax 删除数据，刷新datagrid 
		//判断是否选择了数据记录
		var row = $("#dg").datagrid('getSelected');
		if (row == null) {
			$.messager.alert('提示','你还没有选择任何数据记录','warning');			
			return;
		}
		var id = row.id;
		$.messager.confirm('删除提示', '你确定要删除<'+row.rolename+'>吗?', function(r) {
			if (r) {
				$.get("role/delete/" + id, function(data) {
					var data = $.parseJSON(data);
					if (data.msg == "1") {
						$.messager.alert('提示','删除成功','info');		
						$('#dg').datagrid('reload'); //刷新当前页面
					} else {
						$.messager.alert("提示", "删除失败", "error");
					}
				});
				//取消所有页面中元素的选中状态
				$('#dg').datagrid('clearChecked');
			}
		});
	}
</script>
</head>
<body>
	<table id="dg"></table>
	<div id="formbox"></div>
</body>
</html>