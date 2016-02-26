package com.github.wp.system.service;

import java.util.List;

import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysUser;

/**
 * <p>
 * User: wangping
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public interface OrganizationService {

	public void deleteOrganization(Long organizationId);

	public SysOrganization findOne(Long organizationId);

	public List<?> findAll();

	public void saveOrUpdate(SysOrganization sysOrganization);

	public List<?> findUserOrgs(SysUser sysUser);

	public List<?> findChildByRoot(Long id);
}
