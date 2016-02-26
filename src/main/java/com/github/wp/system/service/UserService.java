package com.github.wp.system.service;

import java.util.List;
import java.util.Set;

import com.github.wp.system.pojo.SysOrganization;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

/**
 * 用户管理service
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 下午4:39:25
 */
public interface UserService {

	/**
	 * 创建用户操作
	 * @param user
	 * @author wangping
	 */
    public void createUser(SysUser user);

	/**
	 * 保存或更新用户
	 * @param user
	 * @author wangping
	 */
    public void updateUser(SysUser user);

    /**
	 * 删除用户
	 * @param userId
	 * @author wangping
	 */
    public void deleteUser(Long[] ids);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword);

    /**
	 * 根据用户id查询用户
	 * @param userId
	 * @return
	 * @author wangping
	 */
    public SysUser findOne(Long userId);

    /**
	 * 查询所有用户
	 * @return
	 * @author wangping
	 */
    public List<?> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public SysUser findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username);

    /**
	 * 根据角色id查询用户
	 * @param roleId
	 * @return
	 * @author wangping
	 */
	public Pager<?> findUserByRoleId(Long roleId, Pagination pagination);

	/**
	 * 保存或更新用户
	 * @param sysUser
	 * @return
	 * @author wangping
	 */
	public SysUser saveOrUpdateUser(SysUser sysUser);

	/**
	 * 保存或更新用户菜单资源
	 * @param sysUser
	 * @author wangping
	 */
	public SysUser saveOrUpdateResource(SysUser sysUser);

	/**
	 * 保存或更新用户管辖组织机构信息
	 * @param sysUser
	 * @author wangping
	 */
	public SysUser saveOrUpdateOrg(SysUser sysUser);

	/**
	 * 批量激活用户
	 * @param id
	 * @author wangping
	 */
	public void enableUser(Long[] id);

	/**
	 * 查询用户分页数据
	 * @param sysUser
	 * @param pagination
	 * @return
	 * @author wangping
	 */
	public Pager<SysUser> findPage(SysUser sysUser, Pagination pagination);

	/**
	 * 批量冻结用户
	 * @param id
	 * @author wangping
	 */
	public void disableUser(Long[] id);
	
	public List<SysOrganization> findUserOrgsByName(String username);

}
