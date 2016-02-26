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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Where;

/**
 * 用户类
 */
@Entity
@Table(name = "sys_user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Where(clause="EFFECTFLAG='E'")
public class SysUser extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2712726347060842501L;
	private Long id;
	private SysOrganization sysOrganization;
	private String username;//用户名
	private String realName;//真实姓名
	private String password = "111111";//默认密码
	private String salt;
	private Character locked = 'E';//锁定标识，默认为锁定
	private String sex;//性别
	private Timestamp birthday;//生日
	private String phone;//电话号码
	private String cellphone;//手机号码
	private String email;//邮箱
	private List<SysRole> sysRoles = new ArrayList<SysRole>(500);
	@JsonIgnore
	private List<SysOrganization> sysOrganizations = new ArrayList<SysOrganization>();
	@JsonIgnore
	private List<SysResource> sysResources = new ArrayList<SysResource>();

	public SysUser() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	public SysOrganization getSysOrganization() {
		return this.sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	@Column(name = "username", unique = true, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "REAL_NAME", unique = true, length = 100)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "password", length = 100)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "salt", length = 100)
	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Column(name = "locked")
	public Character getLocked() {
		return this.locked;
	}

	public void setLocked(Character locked) {
		this.locked = locked;
	}

	@Column(name = "SEX", length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "BIRTHDAY")
	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	@Column(name = "PHONE", length = 100)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "CELLPHONE", length = 100)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public List<SysRole> getSysRoles() {
		return this.sysRoles;
	}

	public void setSysRoles(List<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_USER_ORG", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ORG_ID", nullable = false, updatable = false) })
	public List<SysOrganization> getSysOrganizations() {
		return this.sysOrganizations;
	}

	public void setSysOrganizations(List<SysOrganization> sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYS_USER_RESOURCE", joinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID", nullable = false, updatable = false) })
	public List<SysResource> getSysResources() {
		return this.sysResources;
	}

	public void setSysResources(List<SysResource> sysResources) {
		this.sysResources = sysResources;
	}

	public boolean equals(Object obj) {
		if (id == null)
			return false;
		if (!(obj instanceof SysUser))
			return false;
		SysUser sysUser = (SysUser) obj;
		if (sysUser.id == null) 
			return false;
		return sysUser.id.longValue() == id.longValue();
	}

	public int hashCode() {
		if (id == null)
			return 0;
		int result = 17;
		result = 37 * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public String toString() {
		String str = "SysUser:[id=" + id + ",sysOrganization=" + sysOrganization +
	    ",username=" + username + "]";
		return str + super.toString();
	}
	
}
