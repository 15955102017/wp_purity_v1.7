package com.github.wp.system;


/**
 * <p>
 * User: wangping
 * <p>
 * Date: 14-2-15
 * <p>
 * Version: 1.5
 */
public class Constants {

	// 逻辑删除
	public static enum EfeectFlag {
		EFFECT_FLAG('E'),
		DISEFFECT_FLAG('D')
		;
        private final Character value;
	    
	    private EfeectFlag(Character value) {
	        this.value = value;
	    }
	    
	    public Character value() {
	        return value;
	    }
	}
	
	//其他一些变量
	public static enum Others {
		CURRENT_USER_ORGS("orgs"),
		SESSION_FORCE_LOGOUT_KEY("session.force.logout"),
		SYSTEM_EXCEPTION("error"),
		RESPONSE_MSG("msg"),
		RESPONSE_VERSION("version"),
		;
        private final String value;
	    
	    private Others(String value) {
	        this.value = value;
	    }
	    
	    public String value() {
	        return value;
	    }
	}
	
	//树形数据的根节点
	public static enum RootNode {
		//菜单节点信息
		RESOURCE_SECOND(0L),
		RESOURCE_FIRST(null),
		//组织机构节点信息
		ORG_SECOND(0L),
		ORG_FIRST(null),
		//角色节点信息
		ROLE_SECOND(0L),
		ROLE_FIRST(null),
		//菜单节点信息
		RES_SECOND(0L),
		RES_FIRST(null),
		;
		
        private final Long value;
	    
	    private RootNode(Long value) {
	        this.value = value;
	    }

	    public Long value() {
	        return value;
	    }
	}
	
	// 返回请求提示
	public static enum UserNotice {
		//参数字典
		DATADIC_SAVEORUPDATE_SUCCESS("新增或更新参数成功"), 
		DATADIC_DELETEONE_SUCCESS("删除参数成功"), 
		//用户管理
		USER_SAVEORUPDATE_SUCCESS("新增或更新用户信息成功"),
		USER_UPDATERESOURCE_SUCCESS("更新用户资源信息成功"),
		USER_UPDATEUSERORG_SUCCESS("更新用户管辖区域成功"),
		//机构管理
		ORG_SAVEORUPDATE_SUCESS("新增或更新组织机构成功"),
		ORG_DELETEONE_SUCESS("删除组织机构成功"),
		//资源菜单管理
		RESOURCE_SAVEORUPDATE_SUCESS("保存或更新资源菜单成功"),
		RESOURCE_DELETEONE_SUCESS("删除资源菜单成功"),
		//角色管理
		ROLE_SAVEROLERES_SUCESS("更新角色资源菜单成功"),
		ROLE_SAVEROLEORG_SUCESS("更新角色管辖区域成功"),
		ROLE_CREATEROLE_SUCESS("创建角色成功"),
		ROLE_SAVEORUPDATE_SUCESS("保存或更新角色信息成功"),
		ROLE_DELETEONE_SUCESS("删除角色成功"),
		ROLE_UPDATEROLEUSER_SUCESS("更新角色用户信息成功")
		;
	    private final String value;
	    
	    private UserNotice(String value) {
	        this.value = value;
	    }

	    public String getInfo() {
	        return value;
	    }
	}

	public static interface SysPermission {
		//用户管理
		public static String USER_LIST = "user:view";
		public static String USER_USERLISTDG = "user:view";
		public static String USER_LOADUSERROLES = "user:view";
		public static String USER_USERAdd = "user:create";
		public static String USER_ORGCOMBOTREE = "user:view";
		public static String USER_USERORGSTG = "user:view";
		public static String USER_USERROLET = "user:view";
		public static String USER_USERRESOURCETG = "user:view";
		public static String USER_SAVEORUPDATEUSER = "user:create";
		public static String USER_SAVEORUPDATERESOURCE = "user:create";
		public static String USER_SAVEORUPDATEORG = "user:create";
		public static String USER_CREATE = "user:create";
		public static String USER_UPDATE = "user:update";
		public static String USER_DELETE = "user:delete";
		public static String USER_ENABLEUSER = "user:update";
		public static String USER_DISABLEUSER = "user:update";
		//日志管理
		public static String LOG_LIST = "log:view";
		public static String LOG_FINDUSERLOGDG = "log:view";
		//回话管理
		public static String SESSION_LIST = "session:view";
		public static String SESSION_SESSIONDATAGRID = "session:view";
		public static String SESSION_FORCELOGOUT = "session:update";
		//角色管理
		public static String ROLE_LIST = "role:view";
		public static String ROLE_ROLELOAD = "role:view";
		public static String ROLE_RESOURCETG = "role:view";
		public static String ROLE_SAVEROLERESOURCE = "role:update";
		public static String ROLE_ORGTG = "role:view";
		public static String ROLE_SAVEROLEORG = "role:update";
		public static String ROLE_USERDATAGRID = "role:update";
		public static String ROLE_ROLEDETAIL = "role:view";
		public static String ROLE_USERADD = "role:update";
		public static String ROLE_ROLEADD = "role:create";
		public static String ROLE_CREATENEWROLE = "role:create";
		public static String ROLE_SAVEORUPDATEROLE = "role:create";
		public static String ROLE_DELETEONE = "role:delete";
		public static String ROLE_USERWITHOUTROLEID = "role:update";
		public static String ROLE_REMOVEUSERFORMROLE = "role:update";
		//资源菜单管理
		public static String RES_LIST = "resource:view";
		public static String RES_LISTDATA = "resource:view";
		public static String RES_RESOURCETREE = "resource:view";
		public static String RES_RESOURCEDETAIL = "resource:view";
		public static String RES_RESOURCEADD = "resource:create";
		public static String RES_SAVEORUPDATE = "resource:create";
		public static String RES_DELETEONE = "resource:delete";
		//机构管理
		public static String ORG_LIST = "organization:view";
		public static String ORG_ORGANIZATIONLOAD = "organization:view";
		public static String ORG_ORGANIZATIONDETAIL = "organization:view";
		public static String ORG_SHOWTREE = "organization:view";
		public static String ORG_ORGANIZATIONADD = "organization:create";
		public static String ORG_SAVEORUPDATE = "organization:create";
		public static String ORG_DELETEONE = "organization:delete";
		//数据字典管理
		public static String DIC_LIST = "datadic:view";
		public static String DIC_DATADICTREE = "datadic:view";
		public static String DIC_DATADICDETAIL = "datadic:view";
		public static String DIC_DATADICADD = "datadic:create";
		public static String DIC_SAVEORUPDATE = "datadic:create";
		public static String DIC_DELETEONE = "datadic:delete";
	}
	
}
