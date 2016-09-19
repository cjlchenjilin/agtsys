package org.agtsys.service;

import java.util.List;
import org.agtsys.domain.Function;

public interface IFunctionService {
	public List<Function> getFunctionList(Function function) ;

	public Function getFunctionById(Function function) ;
}
