package com.wangcai.member.base.vo;

public class MemRoleForCheckBox {
	private static final long serialVersionUID = 3306493505442117200L;
	private MemRole role;
	private boolean isCheck = false;
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public MemRole getRole() {
		return role;
	}
	public void setRole(MemRole role) {
		this.role = role;
	}
}
