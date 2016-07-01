package com.mds.passbook.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.user.dao.PassRegistrationsDao;
import com.mds.passbook.data.repository.user.dao.UserPassDao;

public interface GolfPassRegistrationsRepository extends CrudRepository<PassRegistrationsDao, Long>{
		List<PassRegistrationsDao> findByPassTypeIdAndPass(String passTypeId, UserPassDao pass);
		PassRegistrationsDao findBySerialNumberAndPassTypeId(String serialNumber, String passTypeId);
}
