package com.github.wp.system.pojo;
// Generated 2015-2-11 12:22:30 by Hibernate Tools 3.4.0.CR1

import java.sql.Timestamp;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

/**
 * 组织机构类
 */
@Entity
@Table(name = "sys_organization")
@Where(clause="EFFECTFLAG='E'")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class SysOrganization extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 954518190039552488L;
	private Long id;
	private String name;//机构名称
	private String orgCode;//机构编码
	private Long orgType;//机构类型
	private Long leVel;//机构级别
	private Long stationNumber;//台站号--可以不要
	private Timestamp publishTime;//气压注册时间
	private String comments;//机构描述
	@JsonIgnore
	private List<SysUser> sysUsers = new ArrayList<SysUser>(0);//机构下的用户
	@JsonIgnore
	private List<SysUser> sysUsers_ = new ArrayList<SysUser>(0);//机构权限下的用户
	@JsonIgnore
	private List<SysRole> sysRoles = new ArrayList<SysRole>(0);//权限下的机构
	@JsonIgnore
	private List<SysOrganization> sysOrganizations = new ArrayList<SysOrganization>();//子机构
	@JsonIgnore
	private SysOrganization sysOrganization;//父机构
	
	public SysOrganization() {
	}
	
	public SysOrganization(Long id) {
		this.id = id;
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

	@Column(name = "ORG_CODE", length = 100)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_TYPE", precision = 12, scale = 0)
	public Long getOrgType() {
		return this.orgType;
	}

	public void setOrgType(Long orgType) {
		this.orgType = orgType;
	}

	@Column(name = "LE_VEL", precision = 12, scale = 0)
	public Long getLeVel() {
		return this.leVel;
	}

	public void setLeVel(Long leVel) {
		this.leVel = leVel;
	}

	@Column(name = "STATION_NUMBER", precision = 12, scale = 0)
	public Long getStationNumber() {
		return this.stationNumber;
	}

	public void setStationNumber(Long stationNumber) {
		this.stationNumber = stationNumber;
	}
	
	@Column(name = "PUBLISH_TIME")
	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	
	@Column(name = "COMMENTS", length = 500)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysOrganization")
	@Where(clause="EFFECTFLAG='E'")
	public List<SysUser> getSysUsers() {
		return this.sysUsers;
	}

	public void setSysUsers(List<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_USER_ORG", joinColumns = { @JoinColumn(name = "ORG_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
	public List<SysUser> getSysUsers_() {
		return this.sysUsers_;
	}

	public void setSysUsers_(List<SysUser> sysUsers_) {
		this.sysUsers_ = sysUsers_;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_ROLE_ORG", joinColumns = { @JoinColumn(name = "ORG_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
	public List<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysOrganization")
	@Where(clause="EFFECTFLAG='E'")
	public List<SysOrganization> getSysOrganizations() {
		return this.sysOrganizations;
	}

	public void setSysOrganizations(List<SysOrganization> sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	public SysOrganization getSysOrganization() {
		return this.sysOrganization;
	}
	
	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}
	
	public boolean equals(Object obj) {
		if (id == null)
			return false;
		if (!(obj instanceof SysOrganization))
			return false;
		SysOrganization sysOrganization = (SysOrganization) obj;
		if (sysOrganization.id == null) 
			return false;
		return sysOrganization.id.longValue() == id.longValue();
	}

	public int hashCode() {
		if (id == null)
			return 0;
		int result = 17;
		result = 37 * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
}
