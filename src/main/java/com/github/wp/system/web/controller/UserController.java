package com.github.wp.system.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysRole;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.service.OrganizationService;
import com.github.wp.system.service.ResourceService;
import com.github.wp.system.service.RoleService;
import com.github.wp.system.service.UserService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.method.JsonUtil;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * 用户管理控制器
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 上午9:39:05
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	/**
	 * 跳转到用户列表界面
	 * @param model
	 * @param response
	 * @return 跳转页面地址
	 * @author wangping
	 */
	@RequestMapping(method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.USER_LIST)
	@RequiresPermissions(Constants.SysPermission.USER_LIST)
	public String list(Model model, HttpServletResponse response) {
		model.addAttribute("sysUser", new SysUser());
		return "system/user/list";
	}

	/**
	 * 查询用户列表datagrid
	 * @param sysUser
	 * @param pagination
	 * @return 用户分页对象数据
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/userListDG")
	@RequiresPermissions(Constants.SysPermission.USER_USERLISTDG)
	public Pager<SysUser> userListDG(SysUser sysUser, Pagination pagination) {
		Pager<SysUser> list = userService.findPage(sysUser, pagination);
		return list;
	}
																					
	/**
	 * 用户权限数据加载
	 * @param model
	 * @param response
	 * @return 用户角色列表数据
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/rolesLoad")
	@RequiresPermissions(Constants.SysPermission.USER_LOADUSERROLES)
	public List<SysRole> loadUserRoles(Model model, HttpServletResponse response) {
		List<SysRole> roles = roleService.findAll();
		return roles;
	}
	
	/**
	 * 跳转到新增用户对话框
	 * @param model
	 * @return 跳转界面地址
	 * @author wangping
	 */
	@RequestMapping(value = "/userAdd")
	@SystemLog(description=UserOperationLog.USER_USERADD)
	@RequiresPermissions(Constants.SysPermission.USER_USERAdd)
	public String userAdd(Model model) {
		model.addAttribute("user", new SysUser());
		return "system/user/userAdd";
	}
	
	/**
	 * 组织机构combotree
	 * @param model
	 * @return 组织机构combotree数据
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/orgCombotree")
	@RequiresPermissions(Constants.SysPermission.USER_ORGCOMBOTREE)
	public List<Map<String, Object>> orgCombotree(Long id) {
		if(id == null) id = Constants.RootNode.ORG_SECOND.value();
    	List<?> organization = organizationService.findChildByRoot(id);
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getOrgTree(id, organization);
		return li;
	}

	/**
	 * 用户管辖的组织机构treegrid
	 * @param model
	 * @param sysUser
	 * @return 用户机构 treegird数据
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/userOrgsTG")
	@RequiresPermissions(Constants.SysPermission.USER_USERORGSTG)
	public List<Map<String, Object>> userOrgsTG(Model model, SysUser sysUser) {
		List<?> userOrgs = organizationService.findUserOrgs(sysUser);
		List<?> orgs = organizationService.findAll();
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getOrgTreegrid(Constants.RootNode.ORG_SECOND.value(), userOrgs, orgs);
		return li;
	}

	/**
	 * 获取用户角色tree
	 * @param model
	 * @param sysUser
	 * @return 用户角色tree数据
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/userRoleT")
	@RequiresPermissions(Constants.SysPermission.USER_USERROLET)
	public List<Map<String,Object>> userRoleT(Model model, SysUser sysUser) {
		List<?> userRoles = roleService.findByUser(sysUser);
    	List<?> roles = roleService.findAll();
    	List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
		li = JsonUtil.getRoleTree(Constants.RootNode.ROLE_SECOND.value(), userRoles, roles);
		return li;
	}
	
	/**
	 * 获取用户资源treegrid
	 * @param model
	 * @param sysUser
	 * @return 用户资源treegrid数据
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/userResourceTG")
	@RequiresPermissions(Constants.SysPermission.USER_USERRESOURCETG)
	public List<Map<String,Object>> userResourceTG(Model model, SysUser sysUser) {
		List<?> userResources = resourceService.findUserResource(sysUser);
    	List<?> resources = resourceService.findAll();
    	List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
		li = JsonUtil.getResourceTG(Constants.RootNode.RES_SECOND.value(), userResources, resources);
		return li;
	}
	
	/**
	 * 保存用户信息
	 * @param sysUser
	 * @return 保存用户操作状态  “新增成功”、 “新增失败”等，用户保存的用户信息
	 * @author wangping
	 */
	@ResponseBody
	@SystemLog(description=UserOperationLog.USER_SAVEORUPDATEUSER)
	@RequestMapping(value = "/saveOrUpdateUser")
	@RequiresPermissions(Constants.SysPermission.USER_SAVEORUPDATEUSER)
	public Map<String, Object> saveOrUpdateUser(SysUser sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
    	SysUser sysUser_ = userService.saveOrUpdateUser(sysUser);
    	map.put(Constants.Others.RESPONSE_VERSION.value(), sysUser.getVersion());
    	map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.USER_SAVEORUPDATE_SUCCESS.getInfo());
    	map.put("user", sysUser_);
    	return map;
	}
	
	/**
	 * 新增或更新用户资源菜单
	 * @param sysUser
	 * @return 保存用户资源操作状态  “新增成功”、 “新增失败”等
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateResource")
	@SystemLog(description=UserOperationLog.USER_SAVEORUPDATERESOURCE)
	@RequiresPermissions(Constants.SysPermission.USER_SAVEORUPDATERESOURCE)
	public Map<String, Object> saveOrUpdateResource(SysUser sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser sysUser_ = userService.saveOrUpdateResource(sysUser);
    	map.put(Constants.Others.RESPONSE_VERSION.value(), sysUser_.getVersion());
    	map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.USER_UPDATERESOURCE_SUCCESS.getInfo());
    	return map;
	}
	
	/**
	 * 保存或更新用户管辖机构
	 * @param sysUser
	 * @return 保存用户管辖机构操作状态  “新增成功”、 “新增失败”等
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateOrg")
	@SystemLog(description=UserOperationLog.USER_SAVEORUPDATEORG)
	@RequiresPermissions(Constants.SysPermission.USER_SAVEORUPDATEORG)
	public Map<String, Object> saveOrUpdateOrg(SysUser sysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysUser sysUser_ = userService.saveOrUpdateOrg(sysUser);
		map.put(Constants.Others.RESPONSE_VERSION.value(), sysUser_.getVersion());
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.USER_UPDATEUSERORG_SUCCESS.getInfo());
    	return map;
	}
	
	/**
	 * 跳转到创建用户界面
	 * @param user
	 * @param redirectAttributes
	 * @return 创建用户界面路径
	 * @author wangping
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@RequiresPermissions(Constants.SysPermission.USER_CREATE)
	public String create(SysUser user, RedirectAttributes redirectAttributes) {
		userService.createUser(user);
//		redirectAttributes.addFlashAttribute("msg", "新增成功");
		return "redirect:/user";
	}

	/**
	 * 跳转到更新用户界面
	 * @param id
	 * @param model
	 * @return 更新用户界面路径
	 * @author wangping
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.USER_UPDATE)
	@RequiresPermissions(Constants.SysPermission.USER_UPDATE)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		return "system/user/userAdd";
	}

	/**
	 * 删除用户，同时跳转到用户列表界面
	 * @param id
	 * @param redirectAttributes
	 * @return 用户列表界面路径
	 * @author wangping
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.USER_DELETE)
	@RequiresPermissions(Constants.SysPermission.USER_DELETE)
	public String delete(@RequestParam("id[]") Long[] id) {
		userService.deleteUser(id);
		return "redirect:/user";
	}

	/**
	 * 弹出修改密码对话框界面
	 * @param id
	 * @param model
	 * @return 修改密码对话框界面路径
	 * @author wangping
	 */
	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.USER_SHOWCHANGEPASSWORDFORM)
	public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("op", "修改密码");
		return "system/user/changePassword";
	}

	/**
	 * 更新用户密码操作
	 * @param id
	 * @param newPassword
	 * @param redirectAttributes
	 * @return 用户列表界面路径
	 * @author wangping
	 */
	@RequestMapping(value = "/{id}/changePassword", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.USER_CHANGEPASSWORD)
	public String changePassword(@PathVariable("id") Long id, String newPassword, RedirectAttributes redirectAttributes) {
		userService.changePassword(id, newPassword);
		redirectAttributes.addFlashAttribute(Constants.Others.RESPONSE_MSG.value(), "修改密码成功");
		return "redirect:/user";
	}
	
	/**
	 * 激活用户功能
	 * @param id
	 * @return 激活用户操作状态  “用户激活成功”等
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/enableUser")
	@SystemLog(description=UserOperationLog.USER_ENABLEUSER)
	@RequiresPermissions(Constants.SysPermission.USER_UPDATE)
	public Map<String, Object> enableUser(@RequestParam("id[]") Long[] id) {
		userService.enableUser(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), "用户激活成功");
		return map;
	}
	
	/**
	 * 冻结用户功能
	 * @param id
	 * @return 冻结用户操作状态  “用户冻结成功”等
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/disableUser")
	@SystemLog(description=UserOperationLog.USER_DISABLEUSER)
	@RequiresPermissions(Constants.SysPermission.USER_DISABLEUSER)
	public Map<String, Object> disableUser(@RequestParam("id[]") Long[] id) {
		userService.disableUser(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), "用户冻结成功");
		return map;
	}

}
