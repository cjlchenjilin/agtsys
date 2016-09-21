<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>账户明细- 代理商管理系统</title>
<%@include file="head.html" %>
<script type="text/javascript">
$(function() {
	/* //本地数据分页
	var jsonlist = jQuery.parseJSON('${jsonlist}');
	$("#dg").datagrid(
					{
						data:jsonlist,
						//url : "accountdetail/list/",
						columns : [ [
								{
									field : 'detailtypename',
									title : '账务类型'
								},
								{
									field : 'money',
									title : '账务资金',
								},
								{
									field : 'accountmoney',
									title : '账户余额'
								},
								{
									field : 'memo',
									title: '备注信息'
								},
								{
									field : 'detaildatetime',
									title : '明细时间',
									formatter : function(value, row, index) {
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
								}] ],
						fitColumns : true,//自适应宽度 
						striped : true,//列表是否有间隔底色
						resizable : true,//列尺寸可调节
						singleSelect : true,
						loadMsg : '正在努力加载中..',
						rownumbers : true, //行前显示行号

						pagination : true,//是否显示分页
						pageSize : 5,//每页条数 
						pageList : [ 5,10,15 ]
	});
	var pager = $("#dg").datagrid("getPager");  
    pager.pagination({  
        total:jsonlist.length,  
        onSelectPage:function (pageNo, pageSize) {  
            var start = (pageNo - 1) * pageSize;  
            var end = start + pageSize;  
            $("#dg").datagrid("loadData", jsonlist.slice(start, end));  
            pager.pagination('refresh', {  
                total:jsonlist.length,  
                pageNumber:pageNo  
            });  
        }  
    });  */ 
	$("#dg").datagrid(
			{
				url : "agent/accountdetail/list",
				columns : [ [
						{
							field : 'detailtypename',
							title : '账务类型'
						},
						{
							field : 'money',
							title : '账务资金',
						},
						{
							field : 'accountmoney',
							title : '账户余额'
						},
						{
							field : 'memo',
							title: '备注信息'
						},
						{
							field : 'detaildatetime',
							title : '明细时间',
							formatter : function(value, row, index) {
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
						}] ],
				fitColumns : true,//自适应宽度 
				striped : true,//列表是否有间隔底色
				resizable : true,//列尺寸可调节
				singleSelect : true,
				loadMsg : '正在努力加载中..',
				rownumbers : true, //行前显示行号

				pagination : true,//是否显示分页
				pageSize : 5,//每页条数 
				pageList : [ 5,10,15 ]
		});
});
</script>
</head>
<body>
	<table id="dg"></table>
</body>
</html>