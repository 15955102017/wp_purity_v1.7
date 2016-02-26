package com.github.wp.system.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysRole;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * <p>Role: wangping
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Repository
public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao {

    @javax.annotation.Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	} 
    
    public Session getSuperSession(){
    	return this.getSessionFactory().getCurrentSession();
    }

    public SysRole findOne(Long roleId) {
        return (SysRole)this.getSuperSession().get(SysRole.class, roleId);
    }

    @SuppressWarnings("unchecked")
    public List<SysRole> findAll() {
    	String hql = "from SysRole";
        return this.getSuperSession().createQuery(hql).list();
    }

	public void saveOrUpdateRole(SysRole sysRole) {
		SysRole sysRole_ = (SysRole) this.getSuperSession().get(SysRole.class, sysRole.getId());
		sysRole_.getSysUsers().addAll(sysRole.getSysUsers());
		List<SysResource> list_ = sysRole_.getSysResources();
		List<SysResource> list = sysRole.getSysResources();
		list_.removeAll(list);
		list_.addAll(list);
		sysRole_.setSysResources(list_);
		this.getSuperSession().saveOrUpdate(sysRole_);
	}

	public void deleteOne(Long id) {
		String hql = "delete from SysRole where id = " + id;
		this.getSuperSession().createQuery(hql).executeUpdate();
		
	}

	public void addUserToRole(SysRole sysRole) {
		this.getSuperSession().saveOrUpdate(sysRole);
		
	}

	@SuppressWarnings("unchecked")
	public Pager<SysUser> userWithoutRoleId(Long roleId, String username, Pagination pagination) {
		String hql = "select tempC from SysUser tempC where tempC.id not in(select tempA.id from SysUser tempA join tempA.sysRoles tempB where tempB.id = " + roleId + ")";
		String hqlCount = "select count(tempC) from SysUser tempC where tempC.id not in(select tempA.id from SysUser tempA join tempA.sysRoles tempB where tempB.id = " + roleId + ")";
		Long count = (Long) this.getSuperSession().createQuery(hqlCount).uniqueResult();
		Query query = this.getSuperSession().createQuery(hql);
		query.setFirstResult(pagination.getFirstSize());//初始行数   
		query.setMaxResults(pagination.getMaxSize());
		Pager<SysUser> pager = new Pager<SysUser>();
		pager.setTotal(count.intValue());
		pager.setRows(query.list());
		return pager;
	}

	public void createNewRole(SysRole sysRole) {
		this.getSuperSession().saveOrUpdate(sysRole);
	}

	public void removeUserFromRole(SysRole sysRole) {
		String hql = "from SysRole tempA where tempA.id = " + sysRole.getId();
		SysRole role_ = (SysRole) this.getSuperSession().createQuery(hql).uniqueResult();
		List<SysUser> list_ = role_.getSysUsers(); 
		List<SysUser> list = sysRole.getSysUsers();
		list_.removeAll(list);
		role_.setSysUsers(list_);
		this.getSuperSession().saveOrUpdate(role_);
	}

	@SuppressWarnings("unchecked")
	public List<SysOrganization> findOrgByRoleId(Long roleId) {
		String hql = "select distinct tempA from SysOrganization tempA join tempA.sysRoles tempB where tempB.id = " + roleId;
		return this.getSuperSession().createQuery(hql).list();
	}

	public void saveRoleOrg(SysRole sysRole) {
		SysRole sysRole_ = (SysRole) this.getSuperSession().get(SysRole.class, sysRole.getId());
		List<SysOrganization> list_ = sysRole.getSysOrganizations();
		sysRole_.setSysOrganizations(list_);
		this.getSuperSession().saveOrUpdate(sysRole_);
	}

	public void saveRoleResource(SysRole sysRole) {
		SysRole sysRole_ = (SysRole) this.getSuperSession().get(SysRole.class, sysRole.getId());
		List<SysResource> list_ = sysRole.getSysResources();
		sysRole_.setSysResources(list_);
		this.getSuperSession().saveOrUpdate(sysRole_);
	}

	@Override
	public List<?> findByUser(SysUser sysUser) {
		if(sysUser.getId() != null) {
			String hql = "select distinct tempA from SysRole tempA join tempA.sysUsers tempB where tempB.id = " + sysUser.getId();
		    return this.getSuperSession().createQuery(hql).list();
		} else return null;
	}
}
