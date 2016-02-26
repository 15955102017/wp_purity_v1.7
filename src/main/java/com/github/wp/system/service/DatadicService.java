package com.github.wp.system.service;

import java.util.List;

import com.github.wp.system.pojo.SysDatadic;

/**
 * <p>User: wangping
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface DatadicService {

    public List<?> findAll();

    public SysDatadic findByCodingname(String codingname);

    public void saveOrUpdate(SysDatadic sysDatadic);

    public void deleteOne(String codingname);

	public List<?> findStationDatadic();

	public List<SysDatadic> findChildByCodingname(String codingname);
}
