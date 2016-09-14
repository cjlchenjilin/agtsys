package org.agtsys.service;

import java.io.OutputStream;

public interface ICaptchcaService {
	String getCaptchca(OutputStream os) throws Exception;
}
