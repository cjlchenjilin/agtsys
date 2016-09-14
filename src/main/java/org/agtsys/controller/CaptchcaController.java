package org.agtsys.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.agtsys.constant.WebConstant;
import org.agtsys.service.CaptchcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("captchca/")
public class CaptchcaController {
	@Autowired
	private CaptchcaService captchcaService;
	@RequestMapping("get")
	public void getCaptchca(HttpServletRequest request,HttpServletResponse response) throws Exception{
		OutputStream os = response.getOutputStream();
		String captchca = captchcaService.getCaptchca(os);
		request.getSession().setAttribute(WebConstant.SESSION_CAPTCHCA_KEY, captchca);
		os.flush();
		os.close();
	}
	@ResponseBody
	@RequestMapping("check")
	public String checkCaptchca(String captchca,HttpServletRequest request) throws Exception{
		String session_cap = (String) request.getSession().getAttribute(WebConstant.SESSION_CAPTCHCA_KEY);
		if(captchca.equals(session_cap)){
			return "yes";
		}else{
			return "no";
		}		
	}
	public void setCaptchcaService(CaptchcaService captchcaService) {
		this.captchcaService = captchcaService;
	}
}
