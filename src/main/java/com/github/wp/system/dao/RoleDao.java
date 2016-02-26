package com.github.wp.system.dao;

import java.util.List;

import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysRole;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * <p>
 * User: wangping
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface RoleDao {

	/**
	 * 根据角色id查询指定的角色对象
	 * @param roleId 角色id
	 * @return
	 * @author wangping
	 */
	public SysRole findOne(Long roleId);

	/**
	 * 查询所有角色对象
	 * @return
	 * @author wangping
	 */
	public List<SysRole> findAll();

	/**
	 * 保存或更新角色
	 * @param sysRole 角色对象-可能是添加，也可能是修改，具体看sysRole的id
	 * @author wangping
	 */
	public void saveOrUpdateRole(SysRole sysRole);

	/**
	 * 删除一个角色
	 * @param id 角色id
	 * @author wangping
	 */
	public void deleteOne(Long id);

	/**
	 * 添加角色的用户对象
	 * @param sysRole 角色对象-查询条件
	 * @author wangping
	 */
	public void addUserToRole(SysRole sysRole);

	/**
	 * 分页查询没有该角色的用户对象集合
	 * @param roleId 角色id-查询条件
	 * @param username 用户名-查询条件
	 * @param pagination 分页对象-查询条件
	 * @return
	 * @author wangping
	 */
	public Pager<SysUser> userWithoutRoleId(Long roleId, String username, Pagination pagination);

	/**
	 * 创建新的角色
	 * @param sysRole 角色对象-查询条件
	 * @author wangping
	 */
	public void createNewRole(SysRole sysRole);

	/**
	 * 删除角色下的用户
	 * @param sysRole 角色对象-查询条件
	 * @author wangping
	 */
	public void removeUserFromRole(SysRole sysRole);

	/**
	 * 根据角色id查询组织机构
	 * @param roleId 角色id
	 * @return
	 * @author wangping
	 */
	public List<SysOrganization> findOrgByRoleId(Long roleId);

	/**
	 * 添加角色的管辖区域
	 * @param sysRole 角色对象-查询条件
	 * @author wangping
	 */
	public void saveRoleOrg(SysRole sysRole);

	/**
	 * 添加角色的权限菜单
	 * @param sysRole 角色对象-查询条件
	 * @author wangping
	 */
	public void saveRoleResource(SysRole sysRole);

	/**
	 * 根据用户查询角色对象集合
	 * @param sysUser 用户对象-查询条件
	 * @return
	 * @author wangping
	 */
	public List<?> findByUser(SysUser sysUser);
}
