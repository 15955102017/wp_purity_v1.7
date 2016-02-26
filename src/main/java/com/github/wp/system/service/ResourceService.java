package com.github.wp.system.service;

import java.util.List;
import java.util.Set;

import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysUser;

/**
 * <p>
 * User: wangping
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface ResourceService {

	public void createResource(SysResource resource);

	public void deleteResource(Long resourceId);

	public SysResource findOne(Long resourceId);

	public List<?> findAll();

	/**
	 * 得到资源对应的权限字符串
	 * 
	 * @param resourceIds
	 * @return
	 */
	public Set<String> findPermissions(Set<Long> resourceIds);

	/**
	 * 根据用户权限得到菜单
	 * 
	 * @param permissions
	 * @return
	 */
	public List<?> findMenus(Set<String> permissions);

	public List<?> findResourceByRoleId(Long roleId);

	public void saveOrUpdate(SysResource resource);

	public List<?> findUserResource(SysUser sysUser);

	public List<?> findChildByRoot(Long id);

}
