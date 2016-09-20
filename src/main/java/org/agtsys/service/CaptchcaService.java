package org.agtsys.service;

import java.io.OutputStream;
import javax.annotation.Resource;
import org.agtsys.util.Captchca;
import org.springframework.stereotype.Service;
@Service
public class CaptchcaService implements ICaptchcaService {
	@Resource(name="captchca")
	private Captchca captchca;
	@Override
	public String getCaptchca(OutputStream os) throws Exception {
		return captchca.generate_captchca(os);
	}
	public void setCaptchca(Captchca captchca) {
		this.captchca = captchca;
	}

}
