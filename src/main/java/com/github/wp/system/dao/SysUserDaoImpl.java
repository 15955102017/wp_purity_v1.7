package com.github.wp.system.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.CommonUtil;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * 用户管理实现类
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 下午4:36:08
 */
@Repository
public class SysUserDaoImpl implements UserDao {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 创建用户操作
	 * @param user
	 * @author wangping
	 */
	public void createUser(SysUser SysUser) {
		this.getSuperSession().saveOrUpdate(SysUser);
	}

	/**
	 * 保存或更新用户
	 * @param user
	 * @author wangping
	 */
	public void updateUser(SysUser SysUser) {
		this.getSuperSession().saveOrUpdate(SysUser);
	}

	/**
	 * 删除用户
	 * @param userId
	 * @author wangping
	 */
	public void deleteUser(Long[] ids) {
		for (Long id : ids) {
			String hql = "delete from SysUser temp where temp.id = " + id;
			this.getSuperSession().createQuery(hql).executeUpdate();
		}
	}

	/**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 * @author wangping
	 */
	public SysUser findOne(Long SysUserId) {
		return (SysUser) this.getSuperSession().get(SysUser.class, SysUserId);
	}

	/**
	 * 查询所有用户
	 * @return
	 * @author wangping
	 */
	public List<?> findAll() {
		String hql = "from SysUser";
		return this.getSuperSession().createQuery(hql).list();
	}

	/**
	 * 根据用户名称查询用户
	 * @param username
	 * @return
	 * @author wangping
	 */
	public SysUser findByUsername(String username) {
		String hql = "from SysUser temp where temp.username = '" + username
				+ "'";
		List<?> UserList = this.getSuperSession().createQuery(hql).list();
		if (UserList.size() == 0) {
			return null;
		}
		return (SysUser) UserList.get(0);
	}

	/**
	 * 根据角色id查询用户
	 * @param roleId
	 * @return
	 * @author wangping
	 */
	@SuppressWarnings("unchecked")
	public Pager<?> findUserByRoleId(Long roleId, Pagination pagination) {
		Criteria criteria = this.getSuperSession().createCriteria(SysUser.class);
		if(roleId != null) {
			criteria.createCriteria("sysRoles")
			.add(Restrictions.eq("id", roleId));
		}
		criteria.setProjection(Projections.rowCount());
		Long count = (Long)criteria.uniqueResult();
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());//初始行数   
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<SysUser> pager = new Pager<SysUser>();
		pager.setTotal(count.intValue());
		pager.setRows(criteria.list());
		return pager;
	}

	/**
	 * 保存或更新用户
	 * @param sysUser
	 * @return
	 * @author wangping
	 */
	public SysUser saveOrUpdateUser(SysUser sysUser) {
		if (sysUser.getId() != null) {
			SysUser user = (SysUser) this.getSuperSession().get(SysUser.class,
					sysUser.getId());
			sysUser.setSysOrganizations(user.getSysOrganizations());
			sysUser.setSysResources(user.getSysResources());
			this.getSuperSession().clear();
		}
		this.getSuperSession().saveOrUpdate(sysUser);
		return sysUser;
	}

	/**
	 * 保存或更新用户菜单资源
	 * @param sysUser
	 * @author wangping
	 */
	public SysUser saveOrUpdateResource(SysUser sysUser) {
		SysUser sysUser_ = (SysUser) this.getSuperSession().get(SysUser.class,
				sysUser.getId());
		sysUser_.setSysResources(sysUser.getSysResources());
		return sysUser_;
	}

	/**
	 * 保存或更新用户管辖组织机构信息
	 * @param sysUser
	 * @author wangping
	 */
	public SysUser saveOrUpdateOrg(SysUser sysUser) {
		SysUser sysUser_ = (SysUser) this.getSuperSession().get(SysUser.class,
				sysUser.getId());
		sysUser_.setSysOrganizations(sysUser.getSysOrganizations());
		return sysUser_;
	}

	/**
	 * 批量激活用户
	 * @param id
	 * @author wangping
	 */
	@Override
	public void enableUser(Long[] id) {
		for (Long id_ : id) {
			SysUser sysUser = (SysUser) this.getSuperSession().get(
					SysUser.class, id_);
			sysUser.setLocked(Constants.EfeectFlag.EFFECT_FLAG.value());
		}
	}

	/**
	 * 查询用户分页数据
	 * @param sysUser
	 * @param pagination
	 * @return
	 * @author wangping
	 */
	@SuppressWarnings("unchecked")
	@Override
	// 查询所有，不要key,默认以方法名+参数值+内容 作为key  
//    @Cacheable(value = "serviceCache")  
	public Pager<SysUser> findPage(SysUser sysUser, Pagination pagination) {
		Criteria criteria = this.getSuperSession()
				.createCriteria(SysUser.class);
		if (sysUser != null && sysUser.getUsername() != null
				&& !sysUser.getUsername().equals("")) {
			criteria.add(Restrictions.like("username",
					"%" + sysUser.getUsername() + "%"));
		}
		criteria = criteria.createCriteria("sysOrganization");
		List<String> ids = CommonUtil.getCUserOrgsList();
		Criterion[] disjs = new Criterion[ids.size()];
		for(int i = 0; i < ids.size(); i++) {
			Criterion disj = Restrictions.like("orgCode", ids.get(i) + "%");
			disjs[i] = disj;
		}
		criteria.add(Restrictions.or(disjs));
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		criteria.setProjection(null);
		criteria.setFirstResult(pagination.getFirstSize());// 初始行数
		criteria.setMaxResults(pagination.getMaxSize());
		Pager<SysUser> pager = new Pager<SysUser>();
		pager.setTotal(count.intValue());
		pager.setRows(criteria.list());
		return pager;
	}

	/**
	 * 批量冻结用户
	 * @param id
	 * @author wangping
	 */
	@Override
	public void disableUser(Long[] id) {
		for (Long id_ : id) {
			SysUser sysUser = (SysUser) this.getSuperSession().get(
					SysUser.class, id_);
			sysUser.setLocked(Constants.EfeectFlag.DISEFFECT_FLAG.value());
			this.getSuperSession().saveOrUpdate(sysUser);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysOrganization> findUserOrgsByName(String username) {
		String hql = "select tempA from SysOrganization tempA join tempA.sysUsers_ "
				+ "tempB where tempB.username = '" + username + "'";
		String hql_ = "select tempA from SysOrganization tempA join tempA.sysRoles "
				+ "tempB join tempB.sysUsers tempC where tempC.username = '" + username + "'";
		List<SysOrganization> set = new ArrayList<SysOrganization>();
		List<SysOrganization> list = (List<SysOrganization>) this.getSuperSession().createQuery(hql).list();
		List<SysOrganization> list_ = (List<SysOrganization>) this.getSuperSession().createQuery(hql_).list();
		set.addAll(list);
		set.addAll(list_);
		return set;
	}
}
