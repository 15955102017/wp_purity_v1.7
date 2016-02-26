package com.github.wp.system.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.wp.system.dao.UserDao;
import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysResource;
import com.github.wp.system.pojo.SysRole;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * 用户管理service实现类
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 下午4:42:14
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    /**
	 * 创建用户操作
	 * @param user
	 * @author wangping
	 */
    public void createUser(SysUser user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        userDao.createUser(user);
    }

    /**
	 * 保存或更新用户
	 * @param user
	 * @author wangping
	 */
    public void updateUser(SysUser user) {
        userDao.updateUser(user);
    }

    /**
   	 * 删除用户
   	 * @param userId
   	 * @author wangping
   	 */
    public void deleteUser(Long[] ids) {
        userDao.deleteUser(ids);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        SysUser user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }


    /**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 * @author wangping
	 */
    public SysUser findOne(Long userId) {
        return userDao.findOne(userId);
    }

    /**
   	 * 查询所有用户
   	 * @return
   	 * @author wangping
   	 */
    public List<?> findAll() {
        return userDao.findAll();
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        SysUser user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        Set<String> str = new HashSet<String>();
        Iterator<SysRole> it = user.getSysRoles().iterator();
        while(it.hasNext()){
        	str.add(it.next().getDescription());
        }
        return str;
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        SysUser user =findByUsername(username);
        if(user == null) {
            return Collections.emptySet();
        }
        Set<String> str = new HashSet<String>();
        Iterator<SysRole> it = user.getSysRoles().iterator();
        List<SysResource> resource = new ArrayList<SysResource>();
        while(it.hasNext()){
        	resource.addAll(it.next().getSysResources());
        }
        resource.addAll(user.getSysResources());
        HashSet<SysResource> resourceFinal = new HashSet<SysResource>(resource);
        Iterator<SysResource> resourceIterator = resourceFinal.iterator();
        while(resourceIterator.hasNext()){
        	String temp = resourceIterator.next().getPermission();
        	if(StringUtils.isEmpty(temp)) continue;
        	str.add(temp);
        }
        return str;
    }

    /**
	 * 根据角色id查询用户
	 * @param roleId
	 * @return
	 * @author wangping
	 */
	public Pager<?> findUserByRoleId(Long roleId, Pagination pagination) {
		return userDao.findUserByRoleId(roleId, pagination);
	}

	/**
	 * 保存或更新用户
	 * @param sysUser
	 * @return
	 * @author wangping
	 */
	public SysUser saveOrUpdateUser(SysUser sysUser) {
		passwordHelper.encryptPassword(sysUser);
		return userDao.saveOrUpdateUser(sysUser);
	}

	/**
	 * 保存或更新用户菜单资源
	 * @param sysUser
	 * @author wangping
	 */
	public SysUser saveOrUpdateResource(SysUser sysUser) {
		return userDao.saveOrUpdateResource(sysUser);
	}

	/**
	 * 保存或更新用户管辖组织机构信息
	 * @param sysUser
	 * @author wangping
	 */
	public SysUser saveOrUpdateOrg(SysUser sysUser) {
		return userDao.saveOrUpdateOrg(sysUser);
	}

	/**
	 * 批量激活用户
	 * @param id
	 * @author wangping
	 */
	@Override
	public void enableUser(Long[] id) {
		userDao.enableUser(id);
	}

	/**
	 * 查询用户分页数据
	 * @param sysUser
	 * @param pagination
	 * @return
	 * @author wangping
	 */
	@Override
	public Pager<SysUser> findPage(SysUser sysUser, Pagination pagination) {
		return userDao.findPage(sysUser, pagination);
	}

	/**
	 * 批量冻结用户
	 * @param id
	 * @author wangping
	 */
	@Override
	public void disableUser(Long[] id) {
		userDao.disableUser(id);
	}

	@Override
	public List<SysOrganization> findUserOrgsByName(String username) {
		return userDao.findUserOrgsByName(username);
		
	}
}
