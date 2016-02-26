package com.github.wp.system.dao;

import java.util.List;

import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysUser;

/**
 * <p>
 * Organization: wangping
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface OrganizationDao {

	/**
	 * 根据机构id删除组织机构对象
	 * @param organizationId 组织机构id
	 * @author wangping
	 */
	public void deleteOrganization(Long organizationId);

	/**
	 * 根据组织机构id查询组织机构对象
	 * @param organizationId 组织机构id
	 * @return
	 * @author wangping
	 */
	public SysOrganization findOne(Long organizationId);

	/**
	 * 查询所有组织机构列表
	 * @return
	 * @author wangping
	 */
	public List<?> findAll();

	/**
	 * 更新或保存组织机构信息
	 * @param sysOrganization 需添加或者更新的组织机构对象
	 * @author wangping
	 */
	public void saveOrUpdate(SysOrganization sysOrganization);

	/**
	 * 查询用户的数据权限-管辖机构
	 * @param sysUser 用户对象
	 * @return
	 * @author wangping
	 */
	public List<?> findUserOrgs(SysUser sysUser);

	/**
	 * 查询机构下的所有子机构
	 * @param id 机构id
	 * @return
	 * @author wangping
	 */
	public List<?> findChildByRoot(Long id);
}
