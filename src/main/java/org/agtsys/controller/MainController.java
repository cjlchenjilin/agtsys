package org.agtsys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.agtsys.constant.WebConstant;
import org.agtsys.domain.EasyUITreeNode;
import org.agtsys.domain.Function;
import org.agtsys.domain.Permission;
import org.agtsys.domain.User;
import org.agtsys.service.IFunctionService;
import org.agtsys.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("main/")
public class MainController {

	@Autowired
	private IPermissionService permissionSerivce;
	@Autowired
	private IFunctionService functionSerivce;

	@ResponseBody
	@RequestMapping(value = "tree", method = RequestMethod.GET)
	public Object tree(HttpSession session) throws Exception {
		//获取当前登录用户
		User user = this.getLoginUser(session);
		Permission query = new Permission();
		query.setRoleid(user.getRoleid());
		//已经启用的功能
		query.setIsstart(1);
		//根据角色得到他的功能
		List<Permission> pers = permissionSerivce.getPermissionByRole(query);
		// 查询所有功能
		List<Function> fs = functionSerivce.getFunctionList(new Function());
		// 获取当前用户的功能
		for (int i = 0; i < fs.size(); i++) {
			for (Permission p : pers) {
				// 查找符合当前p的id
				if (p.getFunctionid() == fs.get(i).getId()) {
					fs.get(i).setCheck(true);
				}
			}
		}
		//获取当前角色的功能菜单
		List<Function> menus = getMenuFunctions(fs);
		System.out.println(menus);
		//获取tree----json
		List<EasyUITreeNode> nodes = getTreeNodes(menus);
		System.out.println(nodes);
		return nodes;
	}
	
	//返回用户菜单
	private List<Function> getMenuFunctions(List<Function> fs) {
		//系统所有功能菜单，已标识是否是当前用户的菜单
		List<Function> ret_fs = new ArrayList<Function>();	
		if (fs.size() > 0) {
			//获取所有一级菜单
			for(int i = 0; i<fs.size();i++){
				if(fs.get(i).getParentid()==0){
					ret_fs.add(fs.get(i));
				}
			}
			//获取parentid != 0 的2级菜单
			for (int j = 0; j < ret_fs.size(); j++) {
				Function f = ret_fs.get(j);
				for (int i = 0; i < fs.size(); i++) {
					// 是否有子功能
					if (f.getId() == fs.get(i).getParentid()) {
						f.getChildrens().add(fs.get(i));
					}
				}
			}
		}
		//删除没有开启子功能的父菜单,切记一定要用迭代器循环删除list元素
		if(ret_fs.size()>0){
			Iterator<Function> ite = ret_fs.iterator();
			while(ite.hasNext()){
				Function f = ite.next();
				//查找标记
				boolean flag = false;
				Iterator<Function> ite2 = f.getChildrens().iterator();
				while(ite2.hasNext()){
					if(ite2.next().getCheck())
						flag= true;
				}
				if(!flag){
					ite.remove();
				}
			}
		}
		return ret_fs;
	}
	//完成菜单向节点对象的转换
	private List<EasyUITreeNode> getTreeNodes(List<Function> menu){
		List<EasyUITreeNode> nodes = new ArrayList<EasyUITreeNode>();
		if(menu.size()>0){
			for(int i=0; i<menu.size();i++){
				nodes.add(getTreeNodeByFunction(menu.get(i),i+1));
			}
		}
		return nodes;
	}
	//nodeid从1开始
	private EasyUITreeNode getTreeNodeByFunction(Function f,Integer nodeId){
		EasyUITreeNode node = new EasyUITreeNode();
		node.setId(nodeId);
		//node.setIconCls("icon-ok");
		node.setState("open");
		node.setText(f.getFunctionname());
		Map<String,Object> attrs = new HashMap<String,Object>();
		attrs.put("url", f.getFuncurl());
		node.setAttributes(attrs);
		List<Function> childs = f.getChildrens();
		if(childs.size()>0){
			node.setChildren(new ArrayList<EasyUITreeNode>());
			for(int i=0;i<childs.size();i++){
				//如果是用户的功能
				if(childs.get(i).getCheck())
					node.getChildren().add(getTreeNodeByFunction(childs.get(i),nodeId*10+i));
			}
		}
		return node;
	}
	

	private User getLoginUser(HttpSession session) {
		return (User) session.getAttribute(WebConstant.SESSION_USER_KEY);
	}

	public void setPermissionSerivce(IPermissionService permissionSerivce) {
		this.permissionSerivce = permissionSerivce;
	}

	public void setFunctionSerivce(IFunctionService functionSerivce) {
		this.functionSerivce = functionSerivce;
	}

}
