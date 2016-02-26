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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.service.ResourceService;
import com.github.wp.system.web.bind.method.JsonUtil;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

/**
 * <p>
 * 资源菜单Controller类
 * User: wangping
 * <p>
 * Date: 14-2-14
 * <p>
 * Version: 1.0
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService resourceService;

	@ModelAttribute("types")
	public SysResource.ResourceType[] resourceTypes() {
		return SysResource.ResourceType.values();
	}

	/**
	 * 查询资源菜单列表界面
	 * @param model
	 * @param response
	 * @return
	 * @author wangping
	 */
	@RequestMapping(method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.RES_LIST)
	@RequiresPermissions(Constants.SysPermission.RES_LIST)
	public String list(Model model, HttpServletResponse response) {
		model.addAttribute("sysResource", new SysResource());
		return "system/resource/list";
	}

	/**
	 * 加载菜单treegrid数据
	 * @param model
	 * @param response
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/listData", method = RequestMethod.GET)
	@RequiresPermissions(Constants.SysPermission.RES_LISTDATA)
	public List<Map<String, Object>> listData(Model model,
			HttpServletResponse response) {
		List<?> resources = resourceService.findAll();
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getResourceTG(Constants.RootNode.RESOURCE_FIRST.value(), null, resources);
		return li;
	}

	/**
	 * 获取异步树数据
	 * @param id
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/resourceTree", method = RequestMethod.GET)	
	@RequiresPermissions(Constants.SysPermission.RES_RESOURCETREE)
	public List<Map<String, Object>> resourceTree(Long id) {
		List<?> resource = resourceService.findChildByRoot(id);
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		li = JsonUtil.getAsynResTree(resource);
		return li;
	}

	/**
	 * 查询菜单详细信息
	 * @param resourceId 菜单id
	 * @param model
	 * @return
	 * @author wangping
	 */
	@RequestMapping(value = "/resourceDetail", method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.RES_RESOURCEDETAIL)
	@RequiresPermissions(Constants.SysPermission.RES_RESOURCEDETAIL)
	public String resourceDetail(Long resourceId, Model model) {
		model.addAttribute("sysResource", resourceService.findOne(resourceId));
		return "system/resource/resourceDetail";
	}

	/**
	 * 添加资源菜单，进入添加资源菜单界面
	 * @param model
	 * @param parentId
	 * @return
	 * @author wangping
	 */
	@RequestMapping(value = "/resourceAdd", method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.RES_RESOURCEADD)
	@RequiresPermissions(Constants.SysPermission.RES_RESOURCEADD)
	public String resourceAdd(Model model, Long parentId) {
		SysResource resource = new SysResource();
		resource.setSysResource(new SysResource(parentId));
		model.addAttribute("sysResource", resource);
		return "system/resource/resourceDetail";
	}

	/**
	 * 保存或更新资源菜单信息
	 * @param resource
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate")
	@SystemLog(description=UserOperationLog.RES_SAVEORUPDATE)
	@RequiresPermissions(Constants.SysPermission.RES_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(SysResource resource) {
		resourceService.saveOrUpdate(resource);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_VERSION.value(), resource.getVersion());
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.RESOURCE_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}

	/**
	 * 删除指定的资源菜单数据
	 * @param id 指定的资源菜单id
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}/deleteOne", method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.RES_DELETEONE)
	@RequiresPermissions(Constants.SysPermission.RES_DELETEONE)
	public Map<String, Object> deleteOne(@PathVariable("id") Long id) {
		resourceService.deleteResource(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.RESOURCE_DELETEONE_SUCESS.getInfo());
		return map;
	}

}
