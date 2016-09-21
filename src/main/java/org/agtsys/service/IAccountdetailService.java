package org.agtsys.service;

import java.util.List;
import org.agtsys.util.PageTool;
import org.agtsys.domain.Accountdetail;

public interface IAccountdetailService {
	public List<Accountdetail> getAccountdetailList(Accountdetail accountdetail,PageTool pt);

	public List<Accountdetail> repostAgentDetail(Accountdetail accountdetail);
	
	public Integer getCount(Accountdetail accountdetail);
	
	public int addAccountdetail(Accountdetail accountdetail);
	
	public int updateAccountdetail(Accountdetail accountdetail);

	public int deleteAccountdetail(Accountdetail accountdetail);
}
