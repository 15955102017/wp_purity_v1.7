package com.github.wp.system.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.system.dao.RoleDao;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysRole;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * <p>User: wangping
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceService resourceService;

    public SysRole findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    public List<SysRole> findAll() {
        return roleDao.findAll();
    }

    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            SysRole role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

	public Set<String> findPermissions(Long[] roleIds) {
		Set<Long> resourceIds = new HashSet<Long>();
		for (Long roleId : roleIds) {
			SysRole role = findOne(roleId);
			List<SysResource> resources = role.getSysResources();
			for (SysResource resource : resources) {
				resourceIds.add(resource.getId());
			}
		}
		return resourceService.findPermissions(resourceIds);
	}

	public void saveOrUpdateRole(SysRole sysRole) {
		roleDao.saveOrUpdateRole(sysRole);
		
	}

	public void deleteOne(Long id) {
		roleDao.deleteOne(id);
		
	}

	public void addUserToRole(SysRole sysRole) {
		roleDao.addUserToRole(sysRole);
	}

	public Pager<SysUser> userWithoutRoleId(Long roleId, String username, Pagination pagination) {
		return roleDao.userWithoutRoleId(roleId, username, pagination);
	}

	public void createNewRole(SysRole sysRole) {
		roleDao.createNewRole(sysRole);
		
	}

	public void removeUserFromRole(SysRole sysRole) {
		roleDao.removeUserFromRole(sysRole);
		
	}

	public List<SysOrganization> findOrgByRoleId(Long roleId) {
		return roleDao.findOrgByRoleId(roleId);
	}

	@Override
	public void saveRoleOrg(SysRole sysRole) {
		roleDao.saveRoleOrg(sysRole);
	}

	@Override
	public void saveRoleResource(SysRole sysRole) {
		roleDao.saveRoleResource(sysRole);		
	}

	@Override
	public List<?> findByUser(SysUser sysUser) {
		return roleDao.findByUser(sysUser);
	}
}
