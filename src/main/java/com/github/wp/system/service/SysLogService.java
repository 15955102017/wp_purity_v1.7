package com.github.wp.system.service;

import com.github.wp.system.pojo.SysLog;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * 用户日志service接口
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 下午5:47:11
 */
public interface SysLogService {

	/**
	 * 查询用户日志数据
	 * @param syslog
	 * @param pagination
	 * @return
	 * @author wangping
	 */
	public Pager<SysLog> findUserlogDG(SysLog syslog, Pagination pagination);
}
