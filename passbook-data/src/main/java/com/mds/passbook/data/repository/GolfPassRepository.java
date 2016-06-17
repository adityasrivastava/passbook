package com.mds.passbook.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.dao.GolfPassDao;

public interface GolfPassRepository extends CrudRepository<GolfPassDao, Long>{
		GolfPassDao findByDeviceId(String deviceId);
}
