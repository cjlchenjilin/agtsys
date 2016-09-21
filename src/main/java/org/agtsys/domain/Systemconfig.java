package org.agtsys.domain;

public class Systemconfig {
    private Long id;

    private Integer configtype;

    private String configtypename;

    private Integer configtypevalue;

    private String configvalue;

    private Integer isstart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConfigtype() {
        return configtype;
    }

    public void setConfigtype(Integer configtype) {
        this.configtype = configtype;
    }

    public String getConfigtypename() {
        return configtypename;
    }

    public void setConfigtypename(String configtypename) {
        this.configtypename = configtypename == null ? null : configtypename.trim();
    }

    public Integer getConfigtypevalue() {
        return configtypevalue;
    }

    public void setConfigtypevalue(Integer configtypevalue) {
        this.configtypevalue = configtypevalue;
    }

    public String getConfigvalue() {
        return configvalue;
    }

    public void setConfigvalue(String configvalue) {
        this.configvalue = configvalue == null ? null : configvalue.trim();
    }

    public Integer getIsstart() {
        return isstart;
    }

    public void setIsstart(Integer isstart) {
        this.isstart = isstart;
    }
}