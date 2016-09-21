package org.agtsys.service;

import java.util.List;
import org.agtsys.dao.SystemconfigMapper;
import org.agtsys.domain.Systemconfig;
import org.agtsys.util.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class SystemconfigService implements ISystemconfigService {
	@Autowired
	private SystemconfigMapper systemconfigMapper;
	@Override
	public List<Systemconfig> getSystemconfigList(Systemconfig systemconfig,
			PageTool pt) {
		return systemconfigMapper.getSystemconfigList(systemconfig, pt);
	}

	@Override
	public Systemconfig getSystemconfig(Systemconfig systemconfig) {
		return systemconfigMapper.getSystemconfig(systemconfig);
	}

	@Override
	public int getMaxTypeValueByType(int type) {
		return systemconfigMapper.getMaxTypeValueByType(type);
	}

	@Override
	public int addSystemconfig(Systemconfig systemconfig) {
		int max = systemconfigMapper.getMaxTypeValueByType(systemconfig.getConfigtype());
		systemconfig.setConfigtypevalue(++max);
		return systemconfigMapper.addSystemconfig(systemconfig);
	}

	@Override
	public int updateSystemconfig(Systemconfig systemconfig) {
		return systemconfigMapper.updateSystemconfig(systemconfig);
	}

	@Override
	public int deleteSystemconfig(Systemconfig systemconfig) {
		return systemconfigMapper.deleteSystemconfig(systemconfig);
	}

	@Override
	public int getCount(Systemconfig systemconfig) {
		return systemconfigMapper.getCount(systemconfig);
	}

}
