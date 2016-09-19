package org.agtsys.domain;

import java.util.List;
import java.util.Map;

public class EasyUITreeNode {
	private Integer id;
	private String text;
	private String state;
	private Boolean checked;
	private String iconCls;
	private Map<String,Object> attributes;
	private List<EasyUITreeNode> children;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Map<String,Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String,Object> attributes) {
		this.attributes = attributes;
	}
	public List<EasyUITreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITreeNode> children) {
		this.children = children;
	}
}
