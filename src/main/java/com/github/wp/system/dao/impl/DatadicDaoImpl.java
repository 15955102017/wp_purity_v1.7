package com.github.wp.system.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.wp.system.dao.DatadicDao;
import com.github.wp.system.pojo.SysDatadic;

/**
 * <p>
 * Resource: wangping
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
@Repository
public class DatadicDaoImpl extends HibernateDaoSupport implements DatadicDao {

	@javax.annotation.Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	public Session getSuperSession() {
		return this.getSessionFactory().getCurrentSession();
	}

	public List<?> findAll() {
		String hql = "from SysDatadic temp";
		return this.getSuperSession().createQuery(hql).list();
	}

	public SysDatadic findByCodingname(String codingname) {
		String hql = "from SysDatadic temp where temp.codingname = '"
				+ codingname + "'";
		SysDatadic datadic = (SysDatadic) this.getSuperSession()
				.createQuery(hql).uniqueResult();
		return datadic;
	}

	public void saveOrUpdate(SysDatadic sysDatadic) {
		this.getSuperSession().saveOrUpdate(sysDatadic);
	}

	public void deleteOne(String codingname) {
//		String hql = "from SysDatadic temp where temp.codingname = '"
//				+ codingname + "'";
//		SysDatadic datadic = (SysDatadic) this.getSuperSession()
//				.createQuery(hql).uniqueResult();
//		this.getSuperSession().delete(datadic);
		String hql = "update SysDatadic temp set temp.effectflag = 'D' where temp.codingname = '"
				+ codingname + "'";
		this.getSuperSession().createQuery(hql).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysDatadic> findChildByCodingname(String codingname) {
		String hql = "";
		if (codingname == null) {
			hql = "from SysDatadic tempA where tempA.sysDatadic.codingname is null";
		}
		else {
			hql = "from SysDatadic tempA where tempA.sysDatadic.codingname = '"
					+ codingname + "'";
		}
		return this.getSuperSession().createQuery(hql).list();
	}
}
