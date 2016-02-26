package com.github.wp.system.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysLog;
import com.github.wp.system.service.SysLogService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * 用户日志管理Controller
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 上午9:41:17
 */
@Controller
@RequestMapping("/userlog")
public class UserlogController {
	
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 跳转到用户日志列表界面
	 * @return
	 * @author wangping
	 */
	@RequestMapping()
	@SystemLog(description=UserOperationLog.LOG_LIST)
	@RequiresPermissions(Constants.SysPermission.LOG_LIST)
	public String list() {
		return "system/userlog/list";
	}

	/**
	 * 查询用户日志datagrid数据
	 * @param syslog
	 * @param pagination
	 * @return
	 * @author wangping
	 */
	@ResponseBody
    @RequestMapping(value = "/findUserlogDG")
	@SystemLog(description=UserOperationLog.LOG_FINDUSERLOGDG)
	@RequiresPermissions(Constants.SysPermission.LOG_FINDUSERLOGDG)
    public Pager<SysLog> findUserlogDG(SysLog syslog, Pagination pagination) {
    	Pager<SysLog> log = sysLogService.findUserlogDG(syslog, pagination);
    	return log;
    }
}
