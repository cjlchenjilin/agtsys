/**
 * 
 */
$(function() {
	//系统菜单
	$('#mm').menu({
		onClick : function(item) {
			if (item.text == "退出系统") {
				window.location.href = "user/logout"
			} else if (item.text = "修改密码") {
				$('#formbox').dialog({
					title : '修改密码',
					width : 300,
					height : 200,
					closed : false,
					cache : false,
					href : 'html/update_pwd.html',
					modal : true,
					buttons : [{
						text : '修改密码',
						handler : function() {
							modifypassword();
						}
					},
					{
					text : '取消',
					handler : function() {
						//关闭对话框
						$("#formbox").dialog('close');
					}
				} ]
				});
			}
		}
	});
	//加载树形菜单
	$('#tt').tree({method : "get",url:'main/tree',
		onClick : function(node) {
			// 通过这个node的属性获取到 url 然后加载到tabs这个插件里面 在在中间区域打开
			// 通过 url = node.attributes.url
			// 创建tabs
			var url = "";
			if (node.hasOwnProperty("attributes")
					&& node.attributes.url) {
				url = node.attributes.url;
				$(node.target).addClass("links")// .attr("href",url);
			}
			// 自定义的一个方法
			createTab({
				title : node.text,
				hasUrl : url,
				content : '<iframe src="'
						+ url
						+ '" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>'
			});
		}
	});
})
// 创建一个tab函数
function createTab(options) {
	var Mtabs = $("#mainTabs");
	var defaults = {
		fit : true,
		border : false,
		closable : true,
		selected : true,
		content : "",
		title : "",
		tools : [ {
			iconCls : 'icon-mini-refresh', // 刷新
			handler : function() {
				var tab = Mtabs.tabs('getSelected');
				Mtabs.tabs('update', {
					tab : tab,
					options : {
						href : ops.href
					}
				})
			}
		} ]
	};
	var ops = $.extend({}, defaults, options);// 扩展参数
	if (ops.hasUrl == "") {
		//$.messager.alert("提示", "请为该菜单绑定url", "error");
		return;
	}
	//先判断是否存在，不存在就创建新的
	if (Mtabs.tabs("exists", ops.title)) {
		Mtabs.tabs("select", ops.title)
	} else {
		Mtabs.tabs('add', ops);
	}
}
function modifypassword(){
	//前提表单使用了validatebox插件
	if ($("#update_pwd_form").form('validate')) {
		//验证密码
		if(!check_pwd($('#old_pwd').val()))
			return;
		//ajax
		$("#update_pwd_form").form('submit',
				{url : 'user/pwd/update',
				success : function(data) {
					if (data == "ok") {
						$.messager.show({
							title : '提示',
							msg : "修改成功"
						});
						//关闭对话框
						$("#formbox").dialog("close");
					} else if (data == "empty") {
						$div_error.html("新密码为空！！");
					} else if (data == "two-empty") {
						$div_error.html("确认密码为空！！");
					} else if (data == "not-equals") {
						$div_error.html("2次密码不相同！！");
					} else {
						$.messager.show({
							title : '提示',
							msg : "修改失败"
						});
					}
				}
		});
	}
}
//验证旧密码
function check_pwd(pwd) {
	//默认不通过
	var flag = false;
	var $div_error = $("#error");
	$.ajax({
		url : 'user/pwd/check',
		type : 'get',
		//是否同步,需要同步
		async : false,
		data : {'pwd' : pwd},
		dataType : 'text',
		timeout : 5000,
		error : function() {
			alert("error");
		},
		success : function(data) {
			if (data == "empty") {
				$div_error.html("密码为空！！");
			} else if (data == "ok") {
				$div_error.html("<font style='color:green'>密码正确！！</font>");
				flag = true;
			} else if (data == "error") {
				$div_error.html("密码错误！！");
			}
		}
	});
	return flag;
}