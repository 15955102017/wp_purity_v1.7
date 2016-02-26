package com.github.wp.system.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.service.ResourceService;
import com.github.wp.system.service.UserService;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.bind.method.JsonUtil;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * <p>
 * User: wangping
 * <p>
 * Date: 14-2-14
 * <p>
 * Version: 1.0
 */
@Controller
public class IndexController{

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private UserService userService;

	/**
	 * 进入首页index.jsp
	 * @return
	 * @author wangping
	 */
	@SystemLog(description=UserOperationLog.INDEX_INDEX)
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	/**
	 * 加载当前用户的权限菜单
	 * @param loginUser
	 * @param model
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping("/indexLoadData")
	public List<Map<String, Object>> index(@CurrentUser SysUser loginUser, Model model) {
		Set<String> permissions = userService.findPermissions(loginUser.getUsername());
		List<?> menus = resourceService.findMenus(permissions);
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getResourceMenu(Constants.RootNode.RESOURCE_SECOND.value(), menus);
		model.addAttribute("menus", menus);
		return li;
	}

	/**
	 * 进入欢迎界面--已弃用
	 * @return
	 * @author wangping
	 */
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	/**
	 * 进入系统出错界面
	 * @return
	 * @author wangping
	 */
	@SystemLog(description=UserOperationLog.INDEX_ERROR)
	@RequestMapping("/error")
	public String error() {
		return "error";
	}

}
