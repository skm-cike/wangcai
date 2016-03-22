package com.wangcai.common.web.easyui;

import java.util.List;

public class TreeJson {
	private String id;
	private String text;
	private String iconCls;
	private String checked;
	private String state;
	private String attributes;
	private String target;
	private List<TreeJson> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<TreeJson> getChildren() {
		return children;
	}
	public void setChildren(List<TreeJson> children) {
		this.children = children;
	}
}
