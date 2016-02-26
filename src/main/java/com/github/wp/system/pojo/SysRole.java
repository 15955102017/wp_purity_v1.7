package com.github.wp.system.pojo;
// Generated 2015-2-11 12:22:30 by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

/**
 * 角色类
 */
@Entity
@Table(name = "sys_role")
@Where(clause="EFFECTFLAG='E'")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class SysRole extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3566369464431317891L;
	private Long id;
	private String role;//角色名称
	private String description;//角色描述
	@JsonIgnore
	private List<SysUser> sysUsers = new ArrayList<SysUser>(0);
	@JsonIgnore
	private List<SysResource> sysResources = new ArrayList<SysResource>(0);
	@JsonIgnore
	private List<SysOrganization> sysOrganizations = new ArrayList<SysOrganization>(0);

	public SysRole() {
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

	@Column(name = "role", length = 100)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	public List<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_resource_role", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "resource_id", nullable = false, updatable = false) })
	public List<SysResource> getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(List<SysResource> sysResources) {
		this.sysResources = sysResources;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_ROLE_ORG", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ORG_ID", nullable = false, updatable = false) })
	public List<SysOrganization> getSysOrganizations() {
		return this.sysOrganizations;
	}

	public void setSysOrganizations(List<SysOrganization> sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}
}
