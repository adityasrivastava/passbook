package com.mds.passbook.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.dao.GolfUserDao;

public interface GolfUserRepository extends CrudRepository<GolfUserDao, Integer>{
	
	GolfUserDao findByName(String username);

}
