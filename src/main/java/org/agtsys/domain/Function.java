package org.agtsys.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Function {
    private Long id;

    private String functioncode;

    private String functionname;

    private Date creationtime;

    private String createdby;

    private Date lastupdatetime;

    private String funcurl;

    private Integer isstart;

    private Long parentid;
    

    //封装子功能
  	private List<Function> childrens = new ArrayList<Function>();
  	//定义当前这个功能是否属于某个角色
  	private boolean check = false;

    public boolean getCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctioncode() {
        return functioncode;
    }

    public void setFunctioncode(String functioncode) {
        this.functioncode = functioncode == null ? null : functioncode.trim();
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname == null ? null : functionname.trim();
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getFuncurl() {
        return funcurl;
    }

    public void setFuncurl(String funcurl) {
        this.funcurl = funcurl == null ? null : funcurl.trim();
    }

    public Integer getIsstart() {
        return isstart;
    }

    public void setIsstart(Integer isstart) {
        this.isstart = isstart;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

	public List<Function> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Function> childrens) {
		this.childrens = childrens;
	}

	
}