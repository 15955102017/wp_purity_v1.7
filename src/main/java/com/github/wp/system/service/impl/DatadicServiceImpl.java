package com.github.wp.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.system.dao.DatadicDao;
import com.github.wp.system.pojo.SysDatadic;
import com.github.wp.system.service.DatadicService;

/**
 * <p>User: wangping
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service
public class DatadicServiceImpl implements DatadicService {

    @Autowired
    private DatadicDao datadicDao;

    public List<?> findAll() {
        return datadicDao.findAll();
    }

	public SysDatadic findByCodingname(String codingname) {
		return datadicDao.findByCodingname(codingname);
	}

	public void saveOrUpdate(SysDatadic sysDatadic) {
		datadicDao.saveOrUpdate(sysDatadic);
	}

	public void deleteOne(String codingname) {
		datadicDao.deleteOne(codingname);
	}

	public List<?> findStationDatadic() {
//		return datadicDao.findStationDatadic();
		return null;
	}

	@Override
	public List<SysDatadic> findChildByCodingname(String codingname) {
		return datadicDao.findChildByCodingname(codingname);
	}
}
