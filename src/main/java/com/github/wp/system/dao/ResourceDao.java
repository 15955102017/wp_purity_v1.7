package com.github.wp.system.dao;

import java.util.List;

import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysUser;

/**
 * <p>
 * Resource: wangping
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface ResourceDao {

	/**
	 * 创建资源菜单或按钮
	 * @param resource 待保存的资源菜单或按钮
	 * @author wangping
	 */
	public void createResource(SysResource resource);

	/**
	 * 删除资源菜单或按钮
	 * @param resourceId 资源菜单或按钮id
	 * @author wangping
	 */
	public void deleteResource(Long resourceId);

	/**
	 * 根据id查询资源菜单或按钮
	 * @param resourceId 资源菜单或按钮id
	 * @return
	 * @author wangping
	 */
	public SysResource findOne(Long resourceId);

	/**
	 * 查询所有的资源菜单或按钮
	 * @return
	 * @author wangping
	 */
	public List<?> findAll();

	/**
	 * 通过角色id查询资源或按钮
	 * @param roleId 资源菜单或按钮id
	 * @return
	 * @author wangping
	 */
	public List<?> findResourceByRoleId(Long roleId);

	/**
	 * 保存或更新资源菜单或按钮
	 * @param resource 资源菜单或按钮对象
	 * @author wangping
	 */
	public void saveOrUpdate(SysResource resource);

	/**
	 * 查询用户的资源菜单或按钮
	 * @param sysUser 用户对象
	 * @return
	 * @author wangping
	 */
	public List<?> findUserResource(SysUser sysUser);

	/**
	 * 查询资源菜单或按钮子数据
	 * @param id 资源菜单或按钮id
	 * @return
	 * @author wangping
	 */
	public List<?> findChildByRoot(Long id);
}
