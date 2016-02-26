package com.github.wp.system.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.github.wp.system.util.common.CommonUtil;

/**
 * 基本的实体类
 * @author wangping
 * @version 1.0
 * @since 2016年1月4日, 下午4:36:53
 */
@MappedSuperclass
public class BasePojo implements java.io.Serializable {

	/** {field's description} */
	private static final long serialVersionUID = -5777573872130575583L;
	
	protected String createuser;//创建人
	protected Timestamp createdate;//创建时间
	protected String lstmntuser;//最近修改人
	protected Timestamp lstmntdate;//最近修改时间
	protected Character effectflag = 'E';//有效标识，'E'标识有效，'D'标识无效
	protected Integer version = 1;//版本控制

	@Column(name = "CREATEUSER", length = 20, updatable = false)
	public String getCreateuser() {
		if (createuser == null) {
			Object obj = CommonUtil.getCurrentUserName();
			if (obj != null) {
				createuser = obj.toString();
			}
		}
		return this.createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	@Column(name = "CREATEDATE", updatable = false)
	public Timestamp getCreatedate() {
		if (createdate == null) {
			createdate = new Timestamp(new java.util.Date().getTime());
		}
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@Column(name = "LSTMNTUSER", length = 20)
	public String getLstmntuser() {
		if (lstmntuser == null) {
			Object obj = CommonUtil.getCurrentUserName();
			if (obj != null) {
				lstmntuser = obj.toString();
			}
		}
		return this.lstmntuser;
	}

	public void setLstmntuser(String lstmntuser) {
		this.lstmntuser = lstmntuser;
	}

	@Column(name = "LSTMNTDATE")
	public Timestamp getLstmntdate() {
		if (lstmntdate == null) {
			lstmntdate = new Timestamp(new java.util.Date().getTime());
		}
		return this.lstmntdate;
	}

	public void setLstmntdate(Timestamp lstmntdate) {
		this.lstmntdate = lstmntdate;
	}

	@Column(name = "EFFECTFLAG", length = 1)
	public Character getEffectflag() {
		return this.effectflag;
	}

	public void setEffectflag(Character effectflag) {
		this.effectflag = effectflag;
	}

	@Version
	@Column(name = "VERSONS", precision = 22, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
