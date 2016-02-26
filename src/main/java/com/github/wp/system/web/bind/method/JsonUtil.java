package com.github.wp.system.web.bind.method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.wp.system.pojo.SysDatadic;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysRole;

public class JsonUtil {

	/**
	 * 获取菜单资源树
	 * 
	 * @param id
	 * @param menus
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getResourceMenu(Long id,
			List<?> menus) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : menus) {
			SysResource resource = (SysResource) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			if ((resource.getSysResource() == null && id == null)
					|| (resource.getSysResource() != null && resource
							.getSysResource().getId() == id)) {
				map.put("menuid", resource.getId());
				map.put("menuname", resource.getName());
				map.put("icon", resource.getIcon());
				map.put("url", resource.getUrl());
				List<Map<String, Object>> mapChild = getResourceMenu(
						resource.getId(), menus);
				if (mapChild.size() > 0) {
					map.put("menus", mapChild);
				}
				li.add(map);
			}
		}
		return li;
	}

	/**
	 * 获取资源树treegrid
	 * 
	 * @param id
	 * @param roleResources
	 * @param resources
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getResourceTG(Long id,
			List<?> roleResources, List<?> resources) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : resources) {
			SysResource resource = (SysResource) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			// 如果id为null表示资源为根节点
			if ((resource.getSysResource() == null && id == null)
					|| (resource.getSysResource() != null && resource
							.getSysResource().getId() == id)) {
				map.put("id", resource.getId());
				map.put("name", resource.getName());
				map.put("url", resource.getUrl());
				map.put("permission", resource.getPermission());
				if (roleResources != null && roleResources.contains(resource)) {
					map.put("isChecked", true);
				}
				else {
					map.put("isChecked", false);
				}
				List<Map<String, Object>> mapChild = getResourceTG(
						resource.getId(), roleResources, resources);
				if (mapChild.size() > 0) {
					map.put("children", mapChild);
				}
				li.add(map);
			}
		}
		return li;
	}

	/**
	 * 获取资源的cobotree
	 * 
	 * @param id
	 * @param resources
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getResrouceCT(Long id,
			List<?> resources) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : resources) {
			SysResource resource = (SysResource) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			// 如果id为null表示资源为根节点
			if ((resource.getSysResource() == null && id == null)
					|| (resource.getSysResource() != null && resource
							.getSysResource().getId() == id)) {
				map.put("id", resource.getId());
				map.put("text", resource.getName());
				List<Map<String, Object>> mapChild = getResrouceCT(
						resource.getId(), resources);
				if (mapChild.size() > 0) {
					map.put("children", mapChild);
				}
				li.add(map);
			}
		}
		return li;
	}

	/**
	 * 获取异步资源tree
	 * 
	 * @param resources
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getAsynResTree(List<?> resources) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : resources) {
			SysResource sysResource = (SysResource) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", sysResource.getId());
			map.put("text", sysResource.getName());
			map.put("iconCls", sysResource.getIcon());
			if (sysResource.getSysResource() == null) {
				List<Map<String, Object>> mapChild = getAsynResTree(sysResource
						.getSysResources());
				if (mapChild.size() > 0) {
					map.put("children", mapChild);
				}
				else
					map.put("state", "closed");
			}
			else if (sysResource.getSysResources() != null
					&& sysResource.getSysResources().size() > 0) {
				map.put("state", "closed");
			}
			li.add(map);
		}
		return li;
	}

	/**
	 * 获取角色tree
	 * 
	 * @param id
	 *            角色根节点
	 * @param userRoles
	 *            选中的角色对象
	 * @param roles
	 *            所有的角色对象
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getRoleTree(Long id,
			List<?> userRoles, List<?> roles) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : roles) {
			SysRole role = (SysRole) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", role.getId());
			map.put("text", role.getDescription());
			if (userRoles != null && userRoles.contains(role)) {
				map.put("checked", true);
			}
			li.add(map);
		}
		return li;
	}

	/**
	 * 获取组织机构树
	 * 
	 * @param id
	 *            机构根节点id
	 * @param organizations
	 *            所有的机构对象
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getOrgTree(Long id,
			List<?> organizations) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : organizations) {
			SysOrganization organization = (SysOrganization) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			if ((organization.getSysOrganization() == null && id == null)
					|| (organization.getSysOrganization() != null && organization
							.getSysOrganization().getId() == id)) {
				map.put("id", organization.getId());
				map.put("text", organization.getName());
				List<Map<String, Object>> mapChild = getOrgTree(
						organization.getId(),
						organization.getSysOrganizations());
				if (mapChild.size() > 0) {
					map.put("children", mapChild);
				}
				li.add(map);
			}
		}
		return li;
	}

	/**
	 * 获取机构的异步树
	 * 
	 * @param organizations
	 *            用户选则的一个节点的机构对象集合
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getAsynOrgTree(List<?> organizations) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : organizations) {
			SysOrganization organization = (SysOrganization) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", organization.getId());
			map.put("text", organization.getName());
			if (organization.getSysOrganization() == null) {
				List<Map<String, Object>> mapChild = getAsynOrgTree(organization
						.getSysOrganizations());
				if (mapChild.size() > 0) {
					map.put("children", mapChild);
				}
				else
					map.put("state", "closed");
			}
			else if (organization.getSysOrganizations() != null
					&& organization.getSysOrganizations().size() > 0) {
				map.put("state", "closed");
			}
			li.add(map);
		}
		return li;
	}

	/**
	 * 获取组织机构的treegrid
	 * 
	 * @param id
	 *            组织机构根节点id
	 * @param specifyOrgs
	 *            选中的机构对象
	 * @param orgs
	 *            所有的机构对象
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getOrgTreegrid(Long id,
			List<?> specifyOrgs, List<?> orgs) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : orgs) {
			SysOrganization org = (SysOrganization) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			if ((org.getSysOrganization() == null && id == null)
					|| (org.getSysOrganization() != null && org
							.getSysOrganization().getId() == id)) {
				map.put("id", org.getId());
				map.put("name", org.getName());
				if (specifyOrgs != null && specifyOrgs.contains(org)) {
					map.put("isChecked", true);
				}
				else {
					map.put("isChecked", false);
				}
				List<Map<String, Object>> mapChild = getOrgTreegrid(
						org.getId(), specifyOrgs, orgs);
				if (mapChild.size() > 0) {
					map.put("children", mapChild);
				}
				li.add(map);
			}
		}
		return li;
	}

	/**
	 * 异步获取数据字典树
	 * @param codingname
	 * @param organizations
	 * @return
	 * @author wangping
	 */
	public static List<Map<String, Object>> getAsynDatadicTree(
			String codingname, List<?> organizations) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		for (Object obj : organizations) {
			SysDatadic datadic = (SysDatadic) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", datadic.getCodingname());
			map.put("text", datadic.getCnname());
			if (codingname == null) {
				List<Map<String, Object>> mapChild = getAsynDatadicTree(
						datadic.getCodingname(), datadic.getSysDatadics());
				if (mapChild.size() > 0) {
					map.put("children", mapChild);
				} 
				else 
					map.put("state", "closed");
			}
			else {
				List<?> dics = datadic.getSysDatadics();
				if (dics.size() > 0) {
					map.put("state", "closed");
				}
			}
			li.add(map);
		}
		return li;
	}
}
