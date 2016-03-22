package com.wangcai.common.web.easyui;

import java.util.List;

import com.github.pagehelper.Page;

public class GridJson {
	private long total;
	private List  rows;
	public GridJson() {

	}
	
	public GridJson(long total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List  getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
		try {
			this.total = ((Page) rows).getTotal();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
