package com.mds.passbook.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.user.dao.PassRegistrationsDao;

public interface GolfPassRegistrationsRepository extends CrudRepository<PassRegistrationsDao, Long>{
		List<PassRegistrationsDao> findByPassTypeId(String passTypeId);
		PassRegistrationsDao findBySerialNumberAndPassTypeId(String serialNumber, String passTypeId);
}
