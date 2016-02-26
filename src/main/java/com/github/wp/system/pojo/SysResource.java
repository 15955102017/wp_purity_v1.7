package com.github.wp.system.pojo;
// Generated 2015-2-11 12:22:30 by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

/**
 * 资源菜单或按钮类
 */
@Entity
@Table(name = "sys_resource")
@Where(clause="EFFECTFLAG='E'")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class SysResource extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6043930958999907346L;
	private Long id;
	private String name;//资源名称
	private ResourceType type = ResourceType.menu; //资源类型;
	private String url;//资源地址
	private String parentIds;//资源父级id
	private String permission;//资源操作权限
	private String icon = "icon-sys";//资源默认图标
	private String menuorder;//资源排序号
	private SysResource sysResource;//上次资源
	private List<SysResource> sysResources = new ArrayList<SysResource>();//子资源
	private List<SysRole> sysRoles = new ArrayList<SysRole>(0);
	private List<SysUser> sysUsers = new ArrayList<SysUser>(0);
	
	public SysResource() {
	}
	
	public SysResource(Long id) {
		this.id = id;
	}

	public static enum ResourceType {
	    menu("菜单"), button("按钮");
	    private final String info;
	    private ResourceType(String info) {
	        this.info = info;
	    }

	    public String getInfo() {
	        return info;
	    }
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", length = 50)
	@Enumerated(EnumType.STRING)
	public ResourceType getType() {
		return this.type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	@Column(name = "url", length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "parent_ids", length = 100)
	public String getParentIds() {
		return this.parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Column(name = "permission", length = 100)
	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	
	@Column(name = "icon")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name = "MENUORDER")
	public String getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(String menuorder) {
		this.menuorder = menuorder;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_resource_role", joinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public List<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_USER_RESOURCE", joinColumns = { @JoinColumn(name = "RESOURCE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
	public List<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysResource")
	@OrderBy("menuorder")
	@Where(clause="EFFECTFLAG='E'")
	public List<SysResource> getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(List<SysResource> sysResources) {
		this.sysResources = sysResources;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public SysResource getSysResource() {
		return this.sysResource;
	}
	
	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (id == null) 
			return false;
		if (!(obj instanceof SysResource))
			return false;
		SysResource sysResource = (SysResource) obj;
		if (sysResource.id == null) 
			return false;
		return sysResource.id.longValue() == id.longValue();
	}

	@Override
	public int hashCode() {
		if (id == null) 
			return 0;
		int result = 17;
		result = 37 * result + (int) (id ^ (id >>> 32));
		return result;
	}

}
