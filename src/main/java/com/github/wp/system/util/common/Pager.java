package com.github.wp.system.util.common;

import java.util.List;

/**
 * 数据分页类
 * 
 * @author wangping
 * @version 1.0
 * @since 2015年8月21日, 上午11:37:44
 */
public class Pager<T> {

	private int total; // 数据总数量
	private List<T> rows;//数据信息

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
