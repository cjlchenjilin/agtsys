package org.agtsys.service;

import java.util.List;
import org.agtsys.util.PageTool;
import org.agtsys.dao.AccountdetailMapper;
import org.agtsys.domain.Accountdetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AccountdetailService implements IAccountdetailService {
	@Autowired
	private AccountdetailMapper accountdetailMapper;
	@Override
	public List<Accountdetail> getAccountdetailList(
			Accountdetail accountdetail, PageTool pt) {
		return accountdetailMapper.getAccountdetailList(accountdetail, pt);
	}

	@Override
	public List<Accountdetail> repostAgentDetail(Accountdetail accountdetail) {
		return accountdetailMapper.repostAgentDetail(accountdetail);
	}

	@Override
	public Integer getCount(Accountdetail accountdetail) {
		return accountdetailMapper.getCount(accountdetail);
	}

	@Override
	public int addAccountdetail(Accountdetail accountdetail) {
		return accountdetailMapper.addAccountdetail(accountdetail);
	}

	@Override
	public int updateAccountdetail(Accountdetail accountdetail) {
		return accountdetailMapper.updateAccountdetail(accountdetail);
	}

	@Override
	public int deleteAccountdetail(Accountdetail accountdetail) {
		return accountdetailMapper.deleteAccountdetail(accountdetail);
	}

	public void setAccountdetailMapper(AccountdetailMapper accountdetailMapper) {
		this.accountdetailMapper = accountdetailMapper;
	}	
}
