package com.github.wp.system.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.wp.system.Constants;
import com.github.wp.system.dao.ResourceDao;
import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysResource.ResourceType;
import com.github.wp.system.pojo.SysUser;

/**
 * <p>User: wangping
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    public void createResource(SysResource resource) {
        resourceDao.createResource(resource);
    }

    public void deleteResource(Long resourceId) {
        resourceDao.deleteResource(resourceId);
    }

    public SysResource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    public List<?> findAll() {
        return resourceDao.findAll();
    }

    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            SysResource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<SysResource> findMenus(Set<String> permissions) {
        List<?> resources = findChildByRoot(Constants.RootNode.RES_SECOND.value());
        List<SysResource> menus = getResources(resources, permissions);
        return menus;
    }
    
    private List<SysResource> getResources(List<?> resources, Set<String> permissions) {
    	List<SysResource> menus = new ArrayList<SysResource>();
    	for(Object obj : resources) {
        	SysResource resource = (SysResource) obj;
            if(resource.getType() != ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
            List<SysResource> childRes = resource.getSysResources();
            if(childRes != null && childRes.size() > 0) {
            	List<SysResource> permissionRes = getResources(childRes, permissions);
            	menus.addAll(permissionRes);
            }
        }
    	return menus;
    }

    private boolean hasPermission(Set<String> permissions, SysResource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

	public List<?> findResourceByRoleId(Long roleId) {
		return resourceDao.findResourceByRoleId(roleId);
	}

	public void saveOrUpdate(SysResource resource) {
		resourceDao.saveOrUpdate(resource);
		
	}

	public List<?> findUserResource(SysUser sysUser) {
		return resourceDao.findUserResource(sysUser);
	}

	@Override
	public List<?> findChildByRoot(Long id) {
		return resourceDao.findChildByRoot(id);
	}
}
