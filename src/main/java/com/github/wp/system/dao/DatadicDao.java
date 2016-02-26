package com.github.wp.system.dao;

import java.util.List;

import com.github.wp.system.pojo.SysDatadic;

/**
 * <p>author: wangping
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface DatadicDao {

    /**
     * 查询所有Datadic对象
     * @return
     * @author wangping
     */
    public List<?> findAll();

    /**
     * 根据编码名称查询Datadic对象
     * @param codingname 编码名称
     * @return
     * @author wangping
     */
    public SysDatadic findByCodingname(String codingname);

    /**
     * 保存或更新参数字典
     * @param sysDatadic 需要添加或者更新的参数字典
     * @author wangping
     */
    public void saveOrUpdate(SysDatadic sysDatadic);

    /**
     * 通过编码名称删除参数字段对象数据
     * @param codingname 编码名称
     * @author wangping
     */
    public void deleteOne(String codingname);

	/**
	 * 根据编码名称查询子对象
	 * @param codingname 编码名称
	 * @return
	 * @author wangping
	 */
	public List<SysDatadic> findChildByCodingname(String codingname);

}
