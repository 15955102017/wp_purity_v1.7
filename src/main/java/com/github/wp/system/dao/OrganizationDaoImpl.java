package com.github.wp.system.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.CommonUtil;

/**
 * <p>
 * Organization: wangping
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

	@Resource
	private SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void deleteOrganization(Long organizationId) {
		SysOrganization organization = findOne(organizationId);
		organization.setEffectflag(Constants.EfeectFlag.DISEFFECT_FLAG.value());
		this.getSuperSession().saveOrUpdate(organization);
	}

	public SysOrganization findOne(Long organizationId) {
		return (SysOrganization) this.getSuperSession().get(
				SysOrganization.class, organizationId);
	}

	@Cacheable(value = "serviceCache")
	public List<?> findAll() {
		String hql = "from SysOrganization";
		return this.getSuperSession().createQuery(hql).list();
	}

	public void saveOrUpdate(SysOrganization sysOrganization) {
		String orgCode = "";
		if (sysOrganization.getSysOrganization() != null
				&& sysOrganization.getSysOrganization().getId() != null) {
			SysOrganization sysOrganization_ = (SysOrganization) this
					.getSuperSession().get(SysOrganization.class,
							sysOrganization.getSysOrganization().getId());
			orgCode = CommonUtil.getOrgCode(sysOrganization_);
		}
		if (sysOrganization.getId() != null) {
			SysOrganization org = (SysOrganization) this.getSuperSession().get(
					SysOrganization.class, sysOrganization.getId());
			org.setComments(sysOrganization.getComments());
			org.setLeVel(sysOrganization.getLeVel());
			org.setName(sysOrganization.getName());
			org.setOrgType(sysOrganization.getOrgType());
			org.setPublishTime(sysOrganization.getPublishTime());
			org.setStationNumber(sysOrganization.getStationNumber());
			this.getSuperSession().saveOrUpdate(org);
			org.setOrgCode(orgCode + org.getId() + "/");
		} else {
		    this.getSuperSession().saveOrUpdate(sysOrganization);
		    sysOrganization.setOrgCode(orgCode + sysOrganization.getId() + "/");
		}
	}

	public List<?> findUserOrgs(SysUser sysUser) {
		if (sysUser.getId() != null) {
			String hql = "select distinct tempA from SysOrganization tempA join tempA.sysUsers_ tempB where tempB.id = '"
					+ sysUser.getId() + "'";
			return this.getSuperSession().createQuery(hql).list();
		}
		else
			return null;
	}

	@Override
	public List<?> findChildByRoot(Long id) {
		String hql = "from SysOrganization tempA";
		if (id == null)
			hql += " where tempA.sysOrganization.id is null";
		else
			hql += " where tempA.sysOrganization.id = " + id;
		return this.getSuperSession().createQuery(hql).list();
	}
}
