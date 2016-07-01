package com.mds.passbook.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.user.dao.UserPassDao;

public interface GolfPassRepository extends CrudRepository<UserPassDao, Long>{
	List<UserPassDao> findByDeviceId(String deviceId);
}
