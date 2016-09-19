package org.agtsys.dao;

import java.util.List;

import org.agtsys.domain.Function;

public interface FunctionMapper {
	 
	public List<Function> getFunctionList(Function function) ;

	public Function getFunctionById(Function function) ;
}