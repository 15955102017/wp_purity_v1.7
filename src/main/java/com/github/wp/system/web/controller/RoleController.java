package com.github.wp.system.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * 角色控制器
 * 
 * @author wangping
 * @version 1.0
 * @since 2014年2月14日, 下午6:03:01
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService organizationService;

	/**
	 * 进入角色管理列表界面
	 * 
	 * @param model
	 * @return
	 * @author wangping
	 */
	@RequestMapping(method = RequestMethod.GET)
	@RequiresPermissions(Constants.SysPermission.ROLE_LIST)
	public String list(Model model) {
		model.addAttribute("roleList", roleService.findAll());
		return "system/role/list";
	}

	/**
	 * 角色数据加载
	 * 
	 * @param model
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/roleLoad")
	@RequiresPermissions(Constants.SysPermission.ROLE_ROLELOAD)
	public List<Map<String, Object>> roleLoad(Model model) {
		List<SysRole> resource = roleService.findAll();
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getRoleTree(0l, null, resource);
		return li;
	}

	/**
	 * 指定角色的资源菜单的treegrid
	 * 
	 * @param model
	 * @param roleId
	 *            角色id
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/resourceTG")
	@RequiresPermissions(Constants.SysPermission.ROLE_RESOURCETG)
	public List<Map<String, Object>> resourceTG(Model model, Long roleId) {
		List<?> roleResources = resourceService.findResourceByRoleId(roleId);
		List<?> resources = resourceService.findAll();
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getResourceTG(Constants.RootNode.RESOURCE_SECOND.value(), roleResources, resources);
		return li;
	}

	/**
	 * 添加角色资源菜单或按钮
	 * 
	 * @param model
	 * @param sysRole
	 *            角色对象-添加的数据
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRoleResource")
	@RequiresPermissions(Constants.SysPermission.ROLE_SAVEROLERESOURCE)
	public Map<String, Object> saveRoleResource(Model model, SysRole sysRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		roleService.saveRoleResource(sysRole);
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ROLE_SAVEROLERES_SUCESS.getInfo());
		return map;
	}

	/**
	 * 角色的数据权限treegrid
	 * 
	 * @param model
	 * @param roleId
	 *            角色id
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/orgTG")
	@RequiresPermissions(Constants.SysPermission.ROLE_ORGTG)
	public List<Map<String, Object>> orgTG(Model model, Long roleId) {
		List<?> roleOrgs = roleService.findOrgByRoleId(roleId);
		List<?> orgs = organizationService.findAll();
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getOrgTreegrid(Constants.RootNode.ORG_SECOND.value(), roleOrgs, orgs);
		return li;
	}

	/**
	 * 保存或更新角色的管辖区域
	 * 
	 * @param model
	 * @param sysRole 保存或更新的数据
	 * 
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRoleOrg")
	@RequiresPermissions(Constants.SysPermission.ROLE_SAVEROLEORG)
	public Map<String, Object> saveRoleOrg(Model model, SysRole sysRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		roleService.saveRoleOrg(sysRole);
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ROLE_SAVEROLEORG_SUCESS.getInfo());
		return map;
	}

	/**
	 * 指定角色下的用户datagrid分页
	 * 
	 * @param model
	 * @param roleId 角色id
	 * @param pagination 分页对象
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/userDatagrid")
	@SystemLog(description=UserOperationLog.ROLE_USERDATAGRID)
	@RequiresPermissions(Constants.SysPermission.ROLE_USERDATAGRID)
	public Pager<?> userDatagrid(Model model, Long roleId, Pagination pagination) {
		Pager<?> users = userService.findUserByRoleId(roleId, pagination);
		return users;
	}

	/**
	 * 查询角色详细信息
	 * @param model
	 * @param roleId 待查询的角色id
	 * @return
	 * @author wangping
	 */
	@RequestMapping(value = "/roleDetail")
	@RequiresPermissions(Constants.SysPermission.ROLE_ROLEDETAIL)
	public String roleDetail(Model model, Long roleId) {
		model.addAttribute("sysRole", roleService.findOne(roleId));
		return "system/role/roleDetail";
	}

	/**
	 * 添加角色下的用户
	 * @param roleId 角色id
	 * @param model
	 * @return
	 * @author wangping
	 */
	@RequestMapping(value = "/userAdd")
	@RequiresPermissions(Constants.SysPermission.ROLE_USERADD)
	public String userAdd(Long roleId, Model model) {
		model.addAttribute("roleId", roleId);
		return "system/role/userAdd";
	}

	/**
	 * 添加角色
	 * @param model
	 * @return
	 * @author wangping
	 */
	@RequestMapping(value = "/roleAdd")
	@RequiresPermissions(Constants.SysPermission.ROLE_ROLEADD)
	public String roleAdd(Model model) {
		return "system/role/roleDetail";
	}

	/**
	 * 提交创建的新角色信息
	 * @param model
	 * @param sysRole
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/createNewRole")
	@RequiresPermissions(Constants.SysPermission.ROLE_CREATENEWROLE)
	public Map<String, Object> createNewRole(SysRole sysRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		roleService.createNewRole(sysRole);
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ROLE_CREATEROLE_SUCESS.getInfo());
		return map;
	}

	/**
	 * 保存或更新角色信息
	 * @param model
	 * @param sysRole
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateRole")
	@RequiresPermissions(Constants.SysPermission.ROLE_SAVEORUPDATEROLE)
	public Map<String, Object> saveOrUpdateRole(Model model, SysRole sysRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		roleService.saveOrUpdateRole(sysRole);
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ROLE_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}

	/**
	 * 删除指定角色
	 * @param id 待删除的角色id
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/deleteOne")
	@RequiresPermissions(Constants.SysPermission.ROLE_DELETEONE)
	public Map<String, Object> deleteOne(@PathVariable("id") Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		roleService.deleteOne(id);
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ROLE_DELETEONE_SUCESS.getInfo());
		return map;
	}

	/**
	 * 查询该角色下没有的用户分页信息
	 * @param roleId
	 * @param username
	 * @param pagination
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/userWithoutRoleId")
	@RequiresPermissions(Constants.SysPermission.ROLE_USERWITHOUTROLEID)
	public Pager<SysUser> userWithoutRoleId(Long roleId, String username,
			Pagination pagination) {
		Pager<SysUser> users = roleService.userWithoutRoleId(roleId, username,
				pagination);
		return users;
	}

	/**
	 * 删除该角色下的用户
	 * @param model
	 * @param sysRole
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/removeUserFromRole")
	@RequiresPermissions(Constants.SysPermission.ROLE_REMOVEUSERFORMROLE)
	public Map<String, Object> removeUserFromRole(Model model, SysRole sysRole) {
		Map<String, Object> map = new HashMap<String, Object>();
		roleService.removeUserFromRole(sysRole);
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ROLE_UPDATEROLEUSER_SUCESS.getInfo());
		return map;
	}

}
