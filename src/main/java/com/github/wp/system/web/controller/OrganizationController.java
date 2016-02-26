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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.service.OrganizationService;
import com.github.wp.system.web.bind.method.JsonUtil;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * 组织机构Controller
 * @author wangping
 * @version 1.0
 * @since 2014年2月14日, 下午5:55:03
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	/**
	 * 进入组织机构管理界面
	 * @param model
	 * @return
	 * @author wangping
	 */
	@RequestMapping(method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.ORG_LIST)
	@RequiresPermissions(Constants.SysPermission.ORG_LIST)
	public String list(Model model) {
		return "system/organization/list";
	}

	/**
	 * 组织机构数据加载，通过异步树的形式加载，id可以为null，null表示查询的机构为顶级节点。
	 * @param id 组织机构id
	 * @param model
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/organizationLoad")
	@RequiresPermissions(Constants.SysPermission.ORG_ORGANIZATIONLOAD)
	public List<Map<String, Object>> organizationLoad(Long id, Model model) {
		List<?> organization = organizationService.findChildByRoot(id);
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getAsynOrgTree(organization);
		return li;
	}

	/**
	 * 组织机构的详细界面
	 * @param model
	 * @param organizationId 查询的组织机构id
	 * @param response
	 * @return 界面url
	 * @author wangping
	 */
	@RequestMapping(value = "/organizationDetail")
	@SystemLog(description=UserOperationLog.ORG_ORGANIZATIONDETAIL)
	@RequiresPermissions(Constants.SysPermission.ORG_ORGANIZATIONDETAIL)
	public String organizationDetail(Model model, Long organizationId,
			HttpServletResponse response) {
		SysOrganization organization = organizationService
				.findOne(organizationId);
		model.addAttribute("organization", organization);
		return "system/organization/organizationDetail";
	}

	/**
	 * 进入组织机构tree界面
	 * @param model
	 * @return 界面url
	 * @author wangping
	 */
	@RequestMapping(value = "/tree")
	@RequiresPermissions(Constants.SysPermission.ORG_SHOWTREE)
	public String showTree(Model model) {
		model.addAttribute("organizationList", organizationService.findAll());
		return "system/organization/tree";
	}

	/**
	 * 进入组织机构添加界面
	 * @param model
	 * @param parentId 组织机构的父级节点
	 * @return 界面url
	 * @author wangping
	 */
	@RequestMapping(value = "/organizationAdd")
	@SystemLog(description=UserOperationLog.ORG_ORGANIZATIONADD)
	@RequiresPermissions(Constants.SysPermission.ORG_ORGANIZATIONADD)
	public String organizationAdd(Model model, Long parentId) {
		SysOrganization organization = new SysOrganization();
		organization.setSysOrganization(new SysOrganization(parentId));
		model.addAttribute("organization", organization);
		return "system/organization/organizationAdd";
	}

	/**
	 * 保存或更新组织机构信息
	 * @param model
	 * @param sysOrganization 组织机构数据对象
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.ORG_SAVEORUPDATE)
	@RequiresPermissions(Constants.SysPermission.ORG_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(Model model, SysOrganization sysOrganization) {
		organizationService.saveOrUpdate(sysOrganization);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_VERSION.value(), sysOrganization.getVersion());
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ORG_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}

	/**
	 * 根据机构id删除组织机构对象
	 * @param id 机构id
	 * @return json
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/deleteOne")
	@SystemLog(description=UserOperationLog.ORG_DELETEONE)
	@RequiresPermissions(Constants.SysPermission.ORG_DELETEONE)
	public Map<String, Object> deleteOne(@PathVariable("id") Long id) {
	    organizationService.deleteOrganization(id);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.ORG_DELETEONE_SUCESS.getInfo());
	    return map;
	}

}
