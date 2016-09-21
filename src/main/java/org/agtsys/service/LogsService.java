package org.agtsys.service;

import java.util.List;
import org.agtsys.util.PageTool;
import org.agtsys.dao.LogsMapper;
import org.agtsys.domain.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsService implements ILogsService {

	@Autowired
	private LogsMapper logsMapper;
	
	@Override
	public List<Logs> getList(Logs logs,PageTool pt)  throws Exception{
		return logsMapper.getList(logs,pt);
	}

	@Override
	public int addLogs(Logs logs)  throws Exception{
		return logsMapper.addLogs(logs);
	}

	@Override
	public int getCount(Logs logs) throws Exception {
		return logsMapper.getCount(logs);
	}

	public void setLogsMapper(LogsMapper logsMapper) {
		this.logsMapper = logsMapper;
	}

}
