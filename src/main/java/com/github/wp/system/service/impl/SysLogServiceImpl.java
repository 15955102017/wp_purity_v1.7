package com.github.wp.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.system.dao.SysLogDao;
import com.github.wp.system.pojo.SysLog;
import com.github.wp.system.service.SysLogService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * 用户日志service实现类
 * @author wangping
 * @version 1.0
 * @since 2015年8月31日, 上午9:27:10
 */
@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogDao sysLogDao;

	/**
	 * 查询用户日志datagrid数据
	 * @param syslog
	 * @param pagination
	 * @return
	 * @author wangping
	 */
	@Override
	public Pager<SysLog> findUserlogDG(SysLog syslog, Pagination pagination) {
		return sysLogDao.findUserlogDG(syslog, pagination);
	}

}
