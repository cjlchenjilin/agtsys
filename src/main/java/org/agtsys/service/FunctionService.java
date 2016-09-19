package org.agtsys.service;

import java.util.List;

import org.agtsys.dao.FunctionMapper;
import org.agtsys.domain.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionService implements IFunctionService {
	@Autowired
	private FunctionMapper functionMapper;
	@Override
	public List<Function> getFunctionList(Function function) {
		return functionMapper.getFunctionList(function);
	}

	@Override
	public Function getFunctionById(Function function) {
		return functionMapper.getFunctionById(function);
	}

	public void setFunctionMapper(FunctionMapper functionMapper) {
		this.functionMapper = functionMapper;
	}

}
