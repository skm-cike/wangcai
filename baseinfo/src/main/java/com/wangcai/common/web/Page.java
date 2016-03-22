package com.wangcai.common.web;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 封装分页数据
 */
public class Page {
	private static final Logger logger = LoggerFactory.getLogger(Page.class);
	private int page; // 当前页码
	private int rows; // 每页行数
	private int totalRecord; // 总记录数
	private int totalPage; // 总页数
	
	private Page() {
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLimitStart() {
		return (page - 1) * rows ;
	}

	public int getLimitEnd() {
		return rows;
	}
	
	public RowBounds getRowBounds() {
		return new RowBounds(this.getLimitStart(), this.getLimitEnd());
	}
}