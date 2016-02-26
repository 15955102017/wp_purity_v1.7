package com.github.wp.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.system.dao.OrganizationDao;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysUser;

/**
 * <p>User: wangping
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationDao organizationDao;

    public void deleteOrganization(Long organizationId) {
        organizationDao.deleteOrganization(organizationId);
    }

    public SysOrganization findOne(Long organizationId) {
        return organizationDao.findOne(organizationId);
    }

    public List<?> findAll() {
        return organizationDao.findAll();
    }

	public void saveOrUpdate(SysOrganization sysOrganization) {
		organizationDao.saveOrUpdate(sysOrganization);
	}

	@Override
	public List<?> findUserOrgs(SysUser sysUser) {
		return organizationDao.findUserOrgs(sysUser);
	}

	@Override
	public List<?> findChildByRoot(Long id) {
		return organizationDao.findChildByRoot(id);
	}
}
