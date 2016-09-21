package org.agtsys.dao;

import java.util.List;
import org.agtsys.util.PageTool;
import org.agtsys.domain.Systemconfig;
import org.apache.ibatis.annotations.Param;

public interface SystemconfigMapper {
 
	public List<Systemconfig> getSystemconfigList(@Param("systemconfig") Systemconfig systemconfig,@Param("pt") PageTool pt);

	public Systemconfig getSystemconfig(Systemconfig systemconfig);
	
	int getMaxTypeValueByType(int type);

	public int addSystemconfig(Systemconfig systemconfig);
	
	public int updateSystemconfig(Systemconfig systemconfig);

	public int deleteSystemconfig(Systemconfig systemconfig);

	public int getCount(Systemconfig systemconfig);
}