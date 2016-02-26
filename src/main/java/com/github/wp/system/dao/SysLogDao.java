package com.github.wp.system.dao;

import com.github.wp.system.pojo.SysLog;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * 系统日志dao接口
 * @author wangping
 * @version 1.0
 * @since 2015年8月31日, 上午9:28:12
 */
public interface SysLogDao {

	/**
	 * 分页查询用户的操作日志
	 * @param syslog 日志对象-查询条件
	 * @param pagination 分页对象-查询条件
	 * @return
	 * @author wangping
	 */
	public Pager<SysLog> findUserlogDG(SysLog syslog, Pagination pagination);

}
