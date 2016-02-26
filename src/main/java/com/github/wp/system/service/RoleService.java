package com.github.wp.system.service;

import java.util.List;
import java.util.Set;

import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysRole;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface RoleService {

	public SysRole findOne(Long roleId);

	public List<SysRole> findAll();

	/**
	 * 根据角色编号得到角色标识符列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findRoles(Long... roleIds);

	/**
	 * 根据角色编号得到权限字符串列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findPermissions(Long[] roleIds);

	public void saveOrUpdateRole(SysRole sysRole);

	public void deleteOne(Long id);

	public void addUserToRole(SysRole sysRole);

	public Pager<SysUser> userWithoutRoleId(Long roleId, String username, Pagination pagination);

	public void createNewRole(SysRole sysRole);

	public void removeUserFromRole(SysRole sysRole);

	public List<SysOrganization> findOrgByRoleId(Long roleId);

	public void saveRoleOrg(SysRole sysRole);

	public void saveRoleResource(SysRole sysRole);

	public List<?> findByUser(SysUser sysUser);

}
