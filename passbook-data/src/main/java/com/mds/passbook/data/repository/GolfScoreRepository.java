package com.mds.passbook.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.dao.GolfDao;
import com.mds.passbook.data.repository.dao.GolfScoreDao;

public interface GolfScoreRepository extends CrudRepository<GolfScoreDao, Long> {

	public List<GolfScoreDao> findByGolf(GolfDao golf);
	public GolfScoreDao findByGolfAndHoleNumber(GolfDao golfGameId, int holeNumber);
}
