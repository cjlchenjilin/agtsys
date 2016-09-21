/**
 * 
 */
	//新增函数
	function addSystemconfig() {
		$("#formbox").dialog({
			title : "新增",
			width : 400,
			height : 300,
			resizable : true,
			modal : true,
			href : "systemconfig/add/"+configtype,
			cache : false,
			buttons : [ {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					//提交数据 form插件  
					$('#addBoxForm').form('submit', {
						url : "systemconfig/add/"+configtype,
						onSubmit : function() {
							var isValid = $(this).form('validate');
							var configtypename = $("#configtypename").val();
							var flag = check_systemconfig(configtype,configtypename);
							return isValid && flag; // 如果isValid&& flag == false 则阻止表单提交，否则提交表单
						},
						success : function(data) {
							var data = $.parseJSON(data);
							if (data.msg == 1) {//提交成功
								//提示用户新增成功
								$.messager.alert('提示','新增成功','info');
								$('#dg').datagrid('reload');//刷新数据 	 
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
	function editSystemconfig() {
		//判断是否选择了数据记录
		var row = $("#dg").datagrid('getSelected');
		if (row == null) {
			$.messager.alert('提示','你还没有选择任何数据记录','error');
			return;
		}
		var id = row.id;
		var original_typename = row.configtypename;
		$("#formbox").dialog({
			title : "修改",
			width : 400,
			height : 300,
			resizable : false,
			modal : true,
			href : "systemconfig/load/" + id,
			cache : false,
			buttons : [ {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					$('#editBoxForm').form('submit', {
						url : "systemconfig/update/"+id,
						onSubmit : function() {
							var isValid = $(this).form('validate');
							var configtypename = $("#configtypename").val();
							var flag = true;
							if(original_typename!=configtypename){
								flag = check_systemconfig(configtype,configtypename);
							}
							return isValid && flag; // 如果isValid == false 则阻止表单提交，否则提交表单
						},
						success : function(data) {
							var data = $.parseJSON(data);
							if (data.msg == 1) {
								//提示用户修改成功
								$.messager.alert('提示','修改成功','info');
								$('#dg').datagrid('reload');//刷新数据 
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
	function delSystemconfig() {
		//判断是否选中
		//$.ajax 删除数据，刷新datagrid 
		//判断是否选择了数据记录
		var row = $("#dg").datagrid('getSelected');
		if (row == null) {
			$.messager.alert('提示','你还没有选择任何数据记录','error');			
			return;
		}
		var id = row.id;
		$.messager.confirm('删除提示', '你确定要删除 {'+row.configtypename+'} 吗?', function(r) {
			if (r) {
				$.get("systemconfig/delete/" + id, function(data) {
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
	//检查是否存在该配置类型
	function check_systemconfig(configtype,configtypename) {
		var flag = false;
		if (configtypename != "") {
			var $div_tip = $("#tip");
			$.ajax({
				url : 'systemconfig/check',
				type : 'get',
				data : {
					'configtype' : configtype,
					'configtypename':configtypename
				},
				dataType : 'json',
				timeout : 5000,
				async : false,
				error : function() {
					alert("error");
				},
				success : function(data) {
					if (data.msg == "empty") {
						$div_tip.html("<font color='red'> 配置类型为空！！</font>");
					} else if (data.msg == "exist") {
						$div_tip.html("<font color='red'>配置类型已存在！！</font>");
					} else if (data.msg == "no_exist") {
						$div_tip.html("<font color='green'>配置类型可以使用！！</font>");
						flag = true;
					}
				}
			});
		}
		return flag;
	}