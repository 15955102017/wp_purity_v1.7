package com.github.wp.system.web.controller;

import java.io.IOException;
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
import com.github.wp.system.pojo.SysDatadic;
import com.github.wp.system.service.DatadicService;
import com.github.wp.system.web.bind.method.JsonUtil;

/**
 * 数据字典管理Controller
 * 
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 上午9:47:26
 */
@Controller
@RequestMapping("/datadic")
public class DatadicController {

	@Autowired
	private DatadicService datadicService;

	/**
	 * 跳转到数据字典列表界面
	 * 
	 * @param model
	 * @param response
	 * @return
	 * @author wangping
	 */
	@RequestMapping(method = RequestMethod.GET)
	@RequiresPermissions(Constants.SysPermission.DIC_LIST)
	public String list(Model model) {
		model.addAttribute("datadic", new SysDatadic());
		return "system/datadic/list";
	}

	/**
	 * 获取数据字典树
	 * 
	 * @param id
	 *            数据字典code，可以为null，如果为null则返回根节点数据
	 * @return
	 * @author wangping
	 */
	@ResponseBody
	@RequestMapping(value = "/datadicTree")
	@RequiresPermissions(Constants.SysPermission.DIC_DATADICTREE)
	public List<Map<String, Object>> datadicTree(String id) {
		List<SysDatadic> sysDatadics = datadicService.findChildByCodingname(id);
		List<Map<String, Object>> map = JsonUtil.getAsynDatadicTree(id,
				sysDatadics);
		return map;
	}

	/**
	 * 数据字典的详细信息
	 * 
	 * @param model
	 * @param codingname
	 *            数据字典编码
	 * @return
	 * @author wangping
	 */
	@RequestMapping(value = "/datadicDetail")
	@RequiresPermissions(Constants.SysPermission.DIC_DATADICDETAIL)
	public String datadicDetail(Model model, String codingname) {
		SysDatadic sysDatadic_ = datadicService.findByCodingname(codingname);
		model.addAttribute("datadic", sysDatadic_);
		return "system/datadic/datadicDetail";
	}

	/**
	 * 新增数据字典
	 * 
	 * @param model
	 * @param codingname
	 * @return
	 * @author wangping
	 */
	@RequestMapping(value = "/datadicAdd")
	@RequiresPermissions(Constants.SysPermission.DIC_DATADICADD)
	public String datadicAdd(Model model, String codingname) {
		SysDatadic sysDatadic_ = new SysDatadic();
		SysDatadic sysDatadic = datadicService.findByCodingname(codingname);
		sysDatadic_.setSysDatadic(sysDatadic);
		model.addAttribute("datadic", sysDatadic_);
		return "system/datadic/datadicDetail";
	}

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate")
	@RequiresPermissions(Constants.SysPermission.DIC_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(SysDatadic sysDatadic) {
		datadicService.saveOrUpdate(sysDatadic);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_VERSION.value(), sysDatadic.getVersion());
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.DATADIC_SAVEORUPDATE_SUCCESS.getInfo());
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/{codingname}/deleteOne")
	@RequiresPermissions(Constants.SysPermission.DIC_DELETEONE)
	public Map<String, Object> deleteOne(@PathVariable("codingname") String codingname) {
		datadicService.deleteOne(codingname);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.DATADIC_DELETEONE_SUCCESS.getInfo());
		return map;
	}

	@RequestMapping(value = "/checkUnique")
	public void checkUnique(HttpServletResponse response, String codingname)
			throws IOException {
		SysDatadic sysDatadic = datadicService.findByCodingname(codingname);
		if (sysDatadic == null) {
			response.getWriter().print(true);
		}
		else {
			response.getWriter().print(false);
		}
	}
}
