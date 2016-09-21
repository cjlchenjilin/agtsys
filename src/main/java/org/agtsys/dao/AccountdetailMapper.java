package org.agtsys.dao;

import java.util.List;
import org.agtsys.util.PageTool;
import org.agtsys.domain.Accountdetail;
import org.apache.ibatis.annotations.Param;

public interface AccountdetailMapper {

	List<Accountdetail> getAccountdetailList(@Param("accountdetail") Accountdetail accountdetail,@Param("pt") PageTool pt);

	public List<Accountdetail> repostAgentDetail(Accountdetail accountdetail) ;
	
	public Integer getCount(Accountdetail accountdetail);
	
	public int addAccountdetail(Accountdetail accountdetail);
	
	public int updateAccountdetail(Accountdetail accountdetail);

	public int deleteAccountdetail(Accountdetail accountdetail);
}