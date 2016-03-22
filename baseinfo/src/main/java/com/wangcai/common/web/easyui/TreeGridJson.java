package com.wangcai.common.web.easyui;

import java.util.List;

public class TreeGridJson {
	private long total;
	private List<?> rows;
	private List<?> footer;
	public TreeGridJson() {
	}
	
	public TreeGridJson(long total, List<?> rows, List<?> footer) {
		super();
		this.total = total;
		this.rows = rows;
		this.footer = footer;
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public List<?> getFooter() {
		return footer;
	}
	public void setFooter(List<?> footer) {
		this.footer = footer;
	}
}
