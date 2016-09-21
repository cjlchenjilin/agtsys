package org.agtsys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.agtsys.domain.Systemconfig;
import org.agtsys.service.ISystemconfigService;
import org.agtsys.util.PageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("systemconfig/")
public class SystemconfigController {
	@Autowired
	private ISystemconfigService systemconfigService;
	
	@RequestMapping("manage/{configtype}")
	public String getSystemconfigManager(@PathVariable("configtype") Integer configtype,Model model){
		model.addAttribute("configtype", configtype);
		return "systemconfig_list";
	}
	
	@ResponseBody
	@RequestMapping("list/{configtype}")
	public Object getSystemconfigs(@PathVariable("configtype") Integer configtype,Systemconfig systemconfig ,Integer page,Integer rows ){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Systemconfig> systemconfigs = systemconfigService.getSystemconfigList(systemconfig, new PageTool(page, rows, null));
		Integer total = systemconfigService.getCount(systemconfig);
		map.put("total", total);
		map.put("rows", systemconfigs);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "check", method = RequestMethod.GET)
	public Object checkSystemconfig(Systemconfig systemconfig) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (systemconfig.getConfigtypename() == null || systemconfig.getConfigtypename().trim().equals("")) {
			data.put("msg", "empty");
			return data;
		}
		Systemconfig ret_systemconfig = systemconfigService.getSystemconfig(systemconfig);
		if (ret_systemconfig != null) {
			data.put("msg", "exist");
			return data;
		} else {
			data.put("msg", "no_exist");
			return data;
		}
	}
	@RequestMapping(value="add/{configtype}",method=RequestMethod.GET)
	public String addSystemconfig(@PathVariable("configtype") Integer configtype,Model model){
		model.addAttribute("configtype", configtype);
		return "systemconfig_add";
	}
	@ResponseBody
	@RequestMapping(value="add/{configtype}",method=RequestMethod.POST)
	public Object addSystemconfigSubmit(@PathVariable("configtype") Integer configtype,Systemconfig systemconfig){
		if(systemconfigService.addSystemconfig(systemconfig)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}

	@RequestMapping(value = "load/{id}", method = RequestMethod.GET)
	public String loadSystemconfig(@PathVariable("id") Long id, Systemconfig systemconfig,Model model) {
		systemconfig = systemconfigService.getSystemconfig(systemconfig);
		model.addAttribute("systemconfig", systemconfig);
		return "systemconfig_edit";
	}
	@ResponseBody
	@RequestMapping(value="update/{id}")
	public Object updateSystemconfig(@PathVariable("id") Long id,Systemconfig systemconfig){
		if(systemconfigService.updateSystemconfig(systemconfig)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
	@ResponseBody
	@RequestMapping(value="delete/{id}")
	public Object deleteSystemconfig(@PathVariable("id") Long id,Systemconfig systemconfig){
		if(systemconfigService.deleteSystemconfig(systemconfig)==1){
			return "{\"msg\":1}";
		}
		return "{\"msg\":0}";
	}
}
