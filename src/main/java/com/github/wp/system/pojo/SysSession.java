package com.github.wp.system.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 回话类，此类没有数据库的表与之对应
 * 
 * @author wangping
 * @version 1.0
 * @since 2016年1月25日, 下午1:08:31
 */
public class SysSession implements Serializable {

	/** {field's description} */
	private static final long serialVersionUID = 1L;
	private Serializable id;
	private String host;
	private String username;
	private Timestamp startTimestamp;
	private Timestamp lastAccessTime;
	private Long tmieout;

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(Date startTimestamp) {
		if(startTimestamp != null)
			this.startTimestamp = new Timestamp(startTimestamp.getTime());
	}

	public Timestamp getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(Date lastAccessTime) {
		if(lastAccessTime != null)
			this.lastAccessTime = new Timestamp(lastAccessTime.getTime());
	}

	public Long getTmieout() {
		return tmieout;
	}

	public void setTmieout(Long tmieout) {
		this.tmieout = tmieout;
	}

}
