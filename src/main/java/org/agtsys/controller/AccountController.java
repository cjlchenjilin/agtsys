package org.agtsys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.agtsys.constant.SystemConfigConstant;
import org.agtsys.constant.WebConstant;
import org.agtsys.util.PageTool;
import org.agtsys.domain.Account;
import org.agtsys.domain.Accountdetail;
import org.agtsys.domain.Logs;
import org.agtsys.domain.Systemconfig;
import org.agtsys.domain.User;
import org.agtsys.service.IAccountService;
import org.agtsys.service.IAccountdetailService;
import org.agtsys.service.ILogsService;
import org.agtsys.service.ISystemconfigService;
import org.agtsys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("account/")
public class AccountController {
	@Autowired
	private ISystemconfigService systemconfigService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountdetailService accountDetailService;

	public static final String OPERATE_INFO_USER_ACCESS_ACCOUNTDETAIL = "用户进行账户明细查询";

	public void setAccountDetailService(
			IAccountdetailService accountDetailService) {
		this.accountDetailService = accountDetailService;
	}

	@Autowired
	private ILogsService logsService;

	@RequestMapping("manage")
	public String accountManage(Model model) {
		Systemconfig systemconfig = new Systemconfig();
		systemconfig.setConfigtype(SystemConfigConstant.AccountConfigType);
		List<Systemconfig> accountTypeList = systemconfigService
				.getSystemconfigList(systemconfig, null);
		model.addAttribute("accountTypeList", accountTypeList);
		return "account_manage";
	}

	/*@ResponseBody
	@RequestMapping("usercode/load")
	public Object usercodeLoad(User user) {
		return userService.getUserCodeList(user, null);
	}*/

	@ResponseBody
	@RequestMapping("money")
	public Object getAccount(HttpSession session) {
		String result = "failed";
		Account account = new Account();
		User user = (User) session.getAttribute(WebConstant.SESSION_USER_KEY);
		account.setUserid(user.getId());
		account = accountService.getAccount(account);
		if (account != null) {
			result = account.getMoney().toString();
		}
		return result;
	}

	/*@ResponseBody
	@RequestMapping("operation")
	public Object accountOperation(Accountdetail accountdetail) {
		boolean flag = false;
		// 定义一个账户用来查询
		Account account = new Account();
		account.setUserid(accountdetail.getUserid());
		// 查询旧账号
		Account oldAccount = accountService.getAccount(account);
		if (null != oldAccount) {
			accountdetail.setDetaildatetime(new Date());
			if (accountService.tx_operationAccount(oldAccount, accountdetail)) {
				flag = true;
			}
		}
		return flag;
	}
*/
	// 预付款页面
	@RequestMapping("advancepay")
	public String advancepay(Model model) {
		Systemconfig query = new Systemconfig();
		query.setConfigtype(SystemConfigConstant.AccountConfigType);
		List<Systemconfig> accountConfigList = systemconfigService
				.getSystemconfigList(query, null);
		model.addAttribute("accountConfigList", accountConfigList);
		return "advance_payment";
	}

	// 预付款查询
	@ResponseBody
	@RequestMapping("advancepay/list")
	public Object advancepayList(Integer page, Integer rows,
			Accountdetail accountDetail, HttpSession session) throws Exception {
		User user = (User) session.getAttribute(WebConstant.SESSION_USER_KEY);
		accountDetail.setUserid(user.getId());
		List<Accountdetail> accountdetailList = accountDetailService
				.getAccountdetailList(accountDetail, new PageTool(page, rows,
						null));
		Integer total = accountDetailService.getCount(accountDetail);
		this.setLog(user, OPERATE_INFO_USER_ACCESS_ACCOUNTDETAIL);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", accountdetailList);
		return map;
	}

	// add logs 
	private void setLog(User user, String operateInfo) throws Exception {
		Logs logs = new Logs();
		logs.setUserId(user.getId());
		logs.setUserName(user.getUsercode());
		logs.setOperateInfo(operateInfo);
		logs.setOperateDatetime(new Date());
		logsService.addLogs(logs);
	}

	public void setSystemconfigService(ISystemconfigService systemconfigService) {
		this.systemconfigService = systemconfigService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public void setLogsService(ILogsService logsService) {
		this.logsService = logsService;
	}

}
