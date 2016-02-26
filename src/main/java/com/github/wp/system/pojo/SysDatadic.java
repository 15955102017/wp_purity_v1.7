package com.github.wp.system.pojo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

/**
 * 数据字典类
 */
@Entity
@Table(name = "SYS_DATADIC")
@Where(clause="EFFECTFLAG='E'")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SysDatadic extends BasePojo {

	/** {field's description} */
	private static final long serialVersionUID = -252870724722797869L;
	private String codingname;//编码名称
	private SysDatadic sysDatadic;
	private String cnname;//名称
	private Integer levelno;//排序级别
	private String commnets;//描述
	@JsonIgnore
	private List<SysDatadic> sysDatadics = new ArrayList<SysDatadic>(0);

	public SysDatadic() {
	}

	public SysDatadic(String codingname) {
		this.codingname = codingname;
	}

	@Id
	@Column(name = "CODINGNAME", unique = true, nullable = false, length = 50)
	public String getCodingname() {
		return this.codingname;
	}

	public void setCodingname(String codingname) {
		this.codingname = codingname;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FATHCODINGNAME")
	public SysDatadic getSysDatadic() {
		return this.sysDatadic;
	}

	public void setSysDatadic(SysDatadic sysDatadic) {
		this.sysDatadic = sysDatadic;
	}

	@Column(name = "CNNAME", length = 50)
	public String getCnname() {
		return this.cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	@Column(name = "LEVELNO", precision = 22, scale = 0)
	public Integer getLevelno() {
		return this.levelno;
	}

	public void setLevelno(Integer levelno) {
		this.levelno = levelno;
	}

	@Column(name = "COMMNETS", length = 500)
	public String getCommnets() {
		return this.commnets;
	}

	public void setCommnets(String commnets) {
		this.commnets = commnets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysDatadic")
	@OrderBy("LEVELNO")
//	@Cascade(value = {CascadeType.MERGE})
	@Where(clause="EFFECTFLAG='E'")
	public List<SysDatadic> getSysDatadics() {
		return this.sysDatadics;
	}

	public void setSysDatadics(List<SysDatadic> sysDatadics) {
		this.sysDatadics = sysDatadics;
	}

}
