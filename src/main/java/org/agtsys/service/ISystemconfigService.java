package org.agtsys.service;

import java.util.List;
import org.agtsys.domain.Systemconfig;
import org.agtsys.util.PageTool;

public interface ISystemconfigService {
	public List<Systemconfig> getSystemconfigList(Systemconfig systemconfig,PageTool pt);

	public Systemconfig getSystemconfig(Systemconfig systemconfig);
	
	int getMaxTypeValueByType(int type);

	public int addSystemconfig(Systemconfig systemconfig);
	
	public int updateSystemconfig(Systemconfig systemconfig);

	public int deleteSystemconfig(Systemconfig systemconfig);

	public int getCount(Systemconfig systemconfig);
}
