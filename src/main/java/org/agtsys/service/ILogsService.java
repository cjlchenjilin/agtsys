package org.agtsys.service;

import java.util.List;

import org.agtsys.util.PageTool;
import org.agtsys.domain.Logs;

public interface ILogsService {
	
	public List<Logs> getList(Logs logs,PageTool pt) throws Exception;
	
	public int addLogs(Logs logs) throws Exception;
	
	public int getCount(Logs logs) throws Exception;
}
