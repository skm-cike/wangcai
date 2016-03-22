package com.wangcai.common.web.easyui;

public class SuccJson {
	private boolean success;
	private String msg;
	
	public SuccJson() {
	}
	
	public SuccJson(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	public SuccJson(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
