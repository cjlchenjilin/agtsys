<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>用户管理 - 代理商管理系统</title>
<%@include file="head.html" %>
<script type="text/javascript" src="js/systemconfig.js"></script>
<script type="text/javascript">
//全局变量：记录当前的配置类型
var configtype = ${configtype};
$(function() {
	$("#dg").datagrid(
					{
						url : "systemconfig/list/"+configtype,
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
									field : 'configtype',
									title : 'configtype',
									hidden: true
								},
								{
									field : 'configtypename',
									title : '配置类型'
								},
								{
									field : 'configtypevalue',
									hidden: true
								},
								<c:if test="${configtype==2 or configtype==3 or configtype==4 or configtype==7}">
									{
										field : 'configvalue',
										title : '配置数值',
									},
								</c:if>
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
							handler : addSystemconfig
						}, '-', {
							text : '修改',
							iconCls : 'icon-edit',
							handler : editSystemconfig
						}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : delSystemconfig
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
</script>
</head>
<body>
	<table id="dg"></table>
	<div id="formbox"></div>
</body>
</html>