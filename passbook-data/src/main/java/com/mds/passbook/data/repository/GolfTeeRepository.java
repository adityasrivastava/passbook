package com.mds.passbook.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.golf.dao.GolfTeeDao;

public interface GolfTeeRepository extends CrudRepository<GolfTeeDao, Long>{

}
