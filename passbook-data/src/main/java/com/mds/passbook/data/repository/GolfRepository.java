package com.mds.passbook.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mds.passbook.data.repository.golf.dao.GolfCourseDao;
import com.mds.passbook.data.repository.golf.dao.GolfDao;
import com.mds.passbook.data.repository.golf.dao.GolfUserDao;

public interface GolfRepository extends CrudRepository<GolfDao, Long>{
	
	public GolfDao findByGolfCoursesId(GolfCourseDao golfCourseId);
	public List<GolfDao> findByUsersId(GolfUserDao usersId);
	
}
