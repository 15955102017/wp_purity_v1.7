package com.github.wp.system.web.spring.aspectj;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

	// String description() default "";

	public enum UserOperationLog {
		// 用户管理
		USER_LIST("操作：点击用户管理菜单"), 
		USER_USERADD("操作：用户管理-点击添加用户按钮"), 
		USER_SAVEORUPDATEUSER("操作：用户管理-新增/编辑用户-点击保存或更新用户信息"), 
		USER_SAVEORUPDATERESOURCE("操作：用户管理-新增/编辑用户-点击保存或更新用户资源菜单"), 
		USER_SAVEORUPDATEORG("操作：用户管理-新增/编辑用户-点击保存或更新用户管辖区域"), 
		USER_UPDATE("操作：用户管理-点击编辑用户"),
		USER_DELETE("操作：用户管理-点击删除"),
		USER_SHOWCHANGEPASSWORDFORM("操作：点击修改密码按钮"), 
		USER_CHANGEPASSWORD("操作：提交保存修改后的密码"), 
		USER_ENABLEUSER("操作：用户管理-点击激活用户按钮"), 
		USER_DISABLEUSER("操作：用户管理-点击冻结用户按钮"),
		
		// 机构管理
		ORG_LIST("操作：点击机构管理菜单"),
		ORG_ORGANIZATIONDETAIL("操作：机构管理-点击机构节点"),
		ORG_ORGANIZATIONADD("操作：机构管理-点击新建机构按钮"),
		ORG_SAVEORUPDATE("操作：机构管理-点击保存或更新机构按钮"),
		ORG_DELETEONE("操作：机构管理-右键删除机构节点"),

		// 资源菜单管理
        RES_LIST("操作：点击菜单管理菜单"),
        RES_RESOURCEDETAIL("操作：菜单管理-点击菜单节点"),
		RES_RESOURCEADD("操作：菜单管理-点击新建菜单按钮"),
		RES_SAVEORUPDATE("操作：菜单管理-点击保存或更新菜单信息按钮"),
		RES_DELETEONE("操作：菜单管理-右键删除菜单节点"),
		
		// 角色管理
        ROLE_LIST("操作：点击角色管理菜单"),
        ROLE_SAVEROLERESOURCE("操作：角色管理-点击更新角色菜单按钮"),
        ROLE_SAVEROLEORG("操作：角色管理-点击更新角色管辖区域按钮"),
        ROLE_USERADD("操作：角色管理-点击添加用户按钮"),
        ROLE_ROLEADD("操作：角色管理-点击新建角色节点"),
        ROLE_USERDATAGRID("操作：角色管理-点击查询用户按钮"),
        ROLE_CREATENEWROLE("操作：角色管理-点击保存或更新角色节点按钮"),
        ROLE_SAVEORUPDATEORLE("操作：角色管理-点击保存或更新角色节点按钮"),
        ROLE_DELETEONE("操作：角色管理-点击删除角色节点按钮"),
        ROLE_REMOVEUSERFROMROLE("操作：角色管理-点击保存删除角色下的用户按钮"),
		// 回话管理
        SESSION_LIST("操作：点击回话管理菜单"),
        SESSION_SESSIONDATAGRID("操作：回话管理-点击查询按钮"),
        SESSION_FORCELOGOUT("操作：回话管理-点击强制退出按钮"),
        
		// 用户日志管理
        LOG_LIST("操作：点击用户日志管理菜单"),
        LOG_FINDUSERLOGDG("操作：用户日志管理-点击查询按钮"),
        
		// 其它
        INDEX_INDEX("操作：进入系统首页"),
        INDEX_ERROR("引导：进入系统出错界面");
        
		private final String info;

		private UserOperationLog(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

	/**
	 * 用户管理操作日志描述
	 * 
	 * @return
	 */
	UserOperationLog description() default UserOperationLog.USER_LIST;

}
