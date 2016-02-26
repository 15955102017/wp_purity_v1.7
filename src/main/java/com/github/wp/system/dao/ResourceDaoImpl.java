package com.github.wp.system.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysUser;

/**
 * <p>Resource: wangping
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class ResourceDaoImpl extends HibernateDaoSupport implements ResourceDao {

    @javax.annotation.Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	} 
    
    public Session getSuperSession(){
    	return this.getSessionFactory().getCurrentSession();
    }
    
    public void createResource(SysResource resource) {
    	this.getSuperSession().saveOrUpdate(resource);;
    }

    public void deleteResource(Long resourceId) {
        this.getSuperSession().delete(findOne(resourceId));
    }


    public SysResource findOne(Long resourceId) {
        return (SysResource) this.getSuperSession().get(SysResource.class, resourceId);
    }

    public List<?> findAll() {
    	String hql = "from SysResource";
    	return this.getSuperSession().createQuery(hql).list();
    }

	public List<?> findResourceByRoleId(Long roleId) {
		String hql = "select distinct tempA from SysResource tempA join tempA.sysRoles tempB where tempB.id = " + roleId;
		return this.getSuperSession().createQuery(hql).list();
	}

	public void saveOrUpdate(SysResource resource) {
		if(resource.getId() != null) {
			SysResource resource_ = (SysResource) this.getSuperSession().get(SysResource.class, resource.getId());
			resource_.setIcon(resource.getIcon());
			resource_.setMenuorder(resource.getMenuorder());
			resource_.setName(resource.getName());
			resource_.setPermission(resource.getPermission());
			resource_.setType(resource.getType());
			resource_.setUrl(resource.getUrl());
			resource_.setVersion(resource.getVersion());
		} else
		    this.getSuperSession().saveOrUpdate(resource);
	}

	public List<?> findUserResource(SysUser sysUser) {
		if(sysUser.getId() != null) {
			String hql = "select distinct tempA from SysResource tempA join tempA.sysUsers tempB where tempB.id = '" + sysUser.getId() + "'";
		    return this.getSuperSession().createQuery(hql).list();
		} else return null;
	}

	@Override
	public List<?> findChildByRoot(Long id) {
		String hql = "from SysResource tempA";
		if (id == null)
			hql += " where tempA.sysResource.id is null order by tempA.menuorder";
		else
			hql += " where tempA.sysResource.id = " + id + " order by tempA.menuorder";
		return this.getSuperSession().createQuery(hql).list();
	}
}
