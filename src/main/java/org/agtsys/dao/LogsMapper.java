package org.agtsys.dao;

import java.util.List;
import org.agtsys.util.PageTool;
import org.agtsys.domain.Logs;
import org.apache.ibatis.annotations.Param;

public interface LogsMapper {
	
	public List<Logs> getList(@Param("logs")Logs logs,@Param("pt")PageTool pt) throws Exception;
	
	public int addLogs(Logs logs) throws Exception;
	
	public int getCount(Logs logs) throws Exception;
	
}
