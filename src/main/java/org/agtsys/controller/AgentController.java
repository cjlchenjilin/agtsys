package org.agtsys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.agtsys.constant.WebConstant;
import org.agtsys.util.PageTool;
import org.agtsys.domain.Account;
import org.agtsys.domain.Accountdetail;
import org.agtsys.domain.User;
import org.agtsys.service.IAccountService;
import org.agtsys.service.IAccountdetailService;
import org.agtsys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("agent/")
public class AgentController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountdetailService accountdetailService;
	
	@RequestMapping("info")
	public String currentUserInfo(Model model,HttpSession session){
		User agent = (User)session.getAttribute(WebConstant.SESSION_USER_KEY);
		model.addAttribute("agent", agent);
		Account q_account = new Account();
		q_account.setUserid(agent.getId());
		Account account = accountService.getAccount(q_account);
		model.addAttribute("account", account);
		return "agent_info";
	}
	
	@RequestMapping("accountdetail")
	public String accountdetail() throws Exception{
		//本地分页后台生成jsonlist
		/*User agent = (User)session.getAttribute(WebConstant.LOGIN_USER_SESSION);
		Accountdetail accountdetail = new Accountdetail();
		accountdetail.setUserid(agent.getId());
		List<Accountdetail> accountdetails = accountdetailService.getAccountdetailList(accountdetail, null);
		ObjectMapper mapper = new ObjectMapper();  
		String jsonlist = mapper.writeValueAsString(accountdetails); 
		System.out.println(jsonlist);
		model.addAttribute("jsonlist", jsonlist);*/
		return "accountdetail";
	}
	@ResponseBody
	@RequestMapping("accountdetail/list")
	public Object accountdetailList(Integer page,Integer rows,HttpSession session){
		User agent = (User)session.getAttribute(WebConstant.SESSION_USER_KEY);
		Accountdetail accountdetail = new Accountdetail();
		accountdetail.setUserid(agent.getId());
		List<Accountdetail> accountdetails = accountdetailService.getAccountdetailList(accountdetail, new PageTool(page, rows, null));
		Integer total = accountdetailService.getCount(accountdetail);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", accountdetails);
		return map;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public void setAccountdetailService(IAccountdetailService accountdetailService) {
		this.accountdetailService = accountdetailService;
	}
	
}
