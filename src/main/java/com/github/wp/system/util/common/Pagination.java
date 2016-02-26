package com.github.wp.system.util.common;

import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

/**
 * easyui 控件分页类
 * 
 * @author wangping
 * @version 1.0
 * @since 2015年8月21日, 下午3:58:22
 */
public class Pagination {
	
	private int rows = 10;//每页显示记录数
	private int page = 1;//页面下标值
	
	private Timestamp instantStartTime; //开始时间
	private Timestamp instantEndTime;   //结束时间

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public int getFirstSize() {
		return (page-1)*rows;
	}
	
	public int getMaxSize() {
		return this.rows;
	}

	public Timestamp getInstantStartTime() {
		return instantStartTime;
	}

	public void setInstantStartTime(String instantStartTime) {
		if(instantStartTime != null && StringUtils.isNotBlank(instantStartTime))
			this.instantStartTime = Timestamp.valueOf(instantStartTime);
	}

	public Timestamp getInstantEndTime() {
		return instantEndTime;
	}

	public void setInstantEndTime(String instantEndTime) {
		if(instantEndTime != null && StringUtils.isNotBlank(instantEndTime))
		this.instantEndTime = Timestamp.valueOf(instantEndTime);
	}
}
