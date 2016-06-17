package com.mds.passbook.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.dao.GolfTeeDao;
import com.mds.passbook.data.repository.dao.GolfTeeDetailsDao;

public interface GolfTeeDetailsRepository extends CrudRepository<GolfTeeDetailsDao, Long> {

	GolfTeeDetailsDao findByGolfTeeAndHoleNumber(GolfTeeDao teeId, int holeNumber); 
}
