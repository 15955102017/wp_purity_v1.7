package com.github.wp.system.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.system.dao.SysLogDao;
import com.github.wp.system.pojo.SysLog;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * 操作日志类
 * @author wangping
 * @version 1.0
 * @since 2015年8月31日, 上午9:29:10
 */
@Repository
public class SysLogDaoImpl implements SysLogDao {
	
	@Resource
	public SessionFactory sessionFactory;
	
	public Session getSuperSession(){
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<SysLog> findUserlogDG(SysLog syslog, Pagination pagination) {
		Criteria criteria = this.getSuperSession().createCriteria(SysLog.class);
		if(pagination != null && pagination.getInstantStartTime() != null) {
		    criteria.add(Restrictions.ge("createdate", pagination.getInstantStartTime()));
		}
		if(pagination != null && pagination.getInstantEndTime() != null) {
		    criteria.add(Restrictions.le("createdate", pagination.getInstantEndTime()));
		}
		if(syslog != null && syslog.getLoginName() != null
				&& !syslog.getLoginName().equals("")) {
		    criteria.add(Restrictions.like("loginName", "%" + syslog.getLoginName() + "%"));
		}
		criteria.addOrder(Order.desc("createdate"));
		criteria.addOrder(Order.desc("id"));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long)criteria.uniqueResult();
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());//初始行数   
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<SysLog> pager = new Pager<SysLog>();
		pager.setTotal(count.intValue());
		pager.setRows(criteria.list());
		return pager;
	}

}
