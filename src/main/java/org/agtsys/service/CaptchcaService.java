package org.agtsys.service;

import java.io.OutputStream;

import org.agtsys.util.Captchca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CaptchcaService implements ICaptchcaService {
	@Autowired
	private Captchca captchca;
	@Override
	public String getCaptchca(OutputStream os) throws Exception {
		return captchca.generate_captchca(os);
	}
	public void setCaptchca(Captchca captchca) {
		this.captchca = captchca;
	}

}
