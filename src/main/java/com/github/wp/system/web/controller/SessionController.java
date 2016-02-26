package com.github.wp.system.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysSession;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * <p>
 * User: wangping
 * <p>
 * Date: 14-3-16
 * <p>
 * Version: 1.0
 */

@Controller
@RequestMapping("/sessions")
public class SessionController {
	@Autowired
	private SessionDAO sessionDAO;

	@RequestMapping()
	@SystemLog(description=UserOperationLog.SESSION_LIST)
	@RequiresPermissions(Constants.SysPermission.SESSION_LIST)
	public String list(Model model) {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		model.addAttribute("sessionCount", sessions.size());
		return "system/sessions/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/sessionDataGrid", method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.SESSION_SESSIONDATAGRID)
	@RequiresPermissions(Constants.SysPermission.SESSION_SESSIONDATAGRID)
	public List<SysSession> sessionDataGrid(Model model,
			HttpServletResponse response) {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		List<SysSession> sysSessions = new ArrayList<SysSession>();
		for (Session session : sessions) {
			SysSession sysSession = new SysSession();
			sysSession.setId(session.getId());
			SimplePrincipalCollection username = (SimplePrincipalCollection) session
					.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
			if (username != null && username.getPrimaryPrincipal() != null)
				sysSession.setUsername(username.getPrimaryPrincipal()
						.toString());
			sysSession.setHost(session.getHost());
			sysSession.setLastAccessTime(session.getLastAccessTime());
			sysSession.setStartTimestamp(session.getStartTimestamp());
			sysSession.setTmieout(session.getTimeout());
			sysSessions.add(sysSession);
		}
		return sysSessions;
	}

	@RequestMapping("/forceLogout")
	@SystemLog(description=UserOperationLog.SESSION_FORCELOGOUT)
	@RequiresPermissions(Constants.SysPermission.SESSION_FORCELOGOUT)
	public void forceLogout(String[] sessions, HttpServletResponse response) {
		for (String session : sessions) {
			Session session_ = sessionDAO.readSession(session);
			if (session_ != null) {
				session_.setAttribute(Constants.Others.SESSION_FORCE_LOGOUT_KEY.value(),
						Boolean.TRUE);
			}
		}
	}

}
