package com.github.wp.system.util.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.web.bind.annotation.CurrentUser;

public class CommonUtil {
	
	/**
	 * 对象转换为map方法
	 * @param obj 任意对象
	 * @return
	 * @throws Exception
	 * @author wangping
	 */
	public static Map<String, Object> Object2Map(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PropertyDescriptor pd = null;
		Method method = null;
		for (int i = 0; i < obj.getClass().getDeclaredFields().length; i++) {
			String FieldName = obj.getClass().getDeclaredFields()[i].getName();
			if (FieldName.equals("serialVersionUID"))
				continue;
			pd = new PropertyDescriptor(FieldName, obj.getClass());
			method = pd.getReadMethod();
			Object v = method.invoke(obj);
			map.put(FieldName, v);
		}
		return map;
	}

	/**
	 * 获取文件的扩展名
	 * @param filename
	 * @return
	 * @author wangping
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1).toUpperCase();
			}
		}
		return null;
	}

	/**
	 * 获取当前用户名
	 * @return
	 * @author wangping
	 */
	public static String getCurrentUserName() {
		Subject currentUser = SecurityUtils.getSubject();
		Object username = currentUser.getPrincipal();
		if (username != null) {
			return username.toString();
		}
		else
			return null;
	}

	/**
	 * 获取当前用户的组织机构数组
	 * @return
	 * @author wangping
	 */
	public static String getCUserOrgsStr() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		SysUser user = (SysUser) session.getAttribute(CurrentUser.User.CURRENT_USER.getInfo());
		List<SysOrganization> orgs = user.getSysOrganizations();
		String hqlCondition = "(";
		for(SysOrganization org : orgs) {
			hqlCondition += org.getId() + ",";
		}
		hqlCondition +="null)";
		return hqlCondition;
	}
	
	/**
	 * 获取当前用户的数据权限集合
	 * @return
	 * @author wangping
	 */
	public static List<String> getCUserOrgsList() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		@SuppressWarnings("unchecked")
		List<SysOrganization> orgs = (List<SysOrganization>) session.getAttribute(Constants.Others.CURRENT_USER_ORGS.value());
		List<String> ids = new ArrayList<String>();
		for(SysOrganization org : orgs) {
			ids.add(org.getOrgCode());
		}
		//未防止沒有区域权限的用户可以直接查询所有
		ids.add("null");
		return ids;
	}
	
	/**
	 * 获取机构编码
	 * @param sysOrganization 组织机构对象
	 * @return
	 * @author wangping
	 */
	public static String getOrgCode(SysOrganization sysOrganization) {
		String orgCode = sysOrganization.getId() + "/";
		if(sysOrganization.getSysOrganization() != null) {
			String forgCode = getOrgCode(sysOrganization.getSysOrganization());
			orgCode = forgCode + orgCode;
		}
		return orgCode;
	}
}
