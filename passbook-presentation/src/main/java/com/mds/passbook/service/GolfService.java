package com.mds.passbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mds.passbook.bean.golf.Golf;
import com.mds.passbook.bean.golf.GolfCourse;
import com.mds.passbook.bean.golf.GolfHoles;
import com.mds.passbook.bean.golf.GolfScore;
import com.mds.passbook.bean.golf.GolfTee;
import com.mds.passbook.bean.golf.GolfUser;
import com.mds.passbook.bean.pass.PassRegistrations;
import com.mds.passbook.bean.pass.UserPass;
import com.mds.passbook.data.repository.golf.dao.GolfDao;
import com.mds.passbook.data.repository.golf.dao.GolfScoreDao;
import com.mds.passbook.data.repository.golf.dao.GolfTeeDao;
import com.mds.passbook.data.repository.golf.dao.GolfUserDao;
import com.mds.passbook.data.repository.security.dao.UserProfile;
import com.mds.passbook.data.repository.user.dao.PassRegistrationsDao;

@Service
public interface GolfService {
	
	void createGame(GolfUser user, String golfCourseId, String holeTypeId, String teeTypeId);
	
	
	void addGolfScore(GolfScore score);
	void deleteGolfScore(GolfScore score);
	void updateGolfScore(GolfScore score);
	
	List<GolfScoreDao> addGolf(Golf golf);
	void deleteGolf(Long id);
	
	void addGolfPass(UserPass pass);
	void deleteGolfPass(UserPass pass);
	UserPass updateGolfPass(UserPass pass);
	UserPass findGolfPassById(Long id);
	
	void addGolfTee(GolfTee tee);
	void deleteGolfTee(GolfTee tee);
	void updateGolfTee(GolfTee tee);
	
	void addGolfHole(GolfHoles hole);
	void deleteGoldHole(GolfHoles hole);
	void updateGolfHole(GolfHoles hole);
	
	void addGolfCourse(GolfCourse course);
	void deleteGolfCourse(GolfCourse course);
	void updateGolfCourse(GolfCourse course);

	Iterable<GolfUser> getAllUser();
	GolfUser getUserById(Long id);
	GolfUser addUser(GolfUser user);
	void deleteUser(GolfUser user);
	GolfUser updateUser(GolfUser user);
	
	Iterable<GolfDao> getAllGolf();
	List<Golf> getAllGolf(GolfUser user);
	Golf getGolfById(Long id);
	void addGame(GolfDao golf);
	void deleteGame(GolfDao golf);
	void updateGame(GolfDao golf);
	
	GolfScoreDao updateScore(GolfScore score);
	
	Iterable<GolfTeeDao> getAllTee();
	void addTee(GolfTeeDao tee);
	void deleteTee(GolfTeeDao tee);
	void updateTee(GolfTeeDao tee);
	GolfScoreDao getScoreById(Long id);
	
	PassRegistrations getPassRegisteredBySerialNumberAndPassTypeId(String serialNumber, String passTypeId);
	
	List<GolfScoreDao> getScoresById(Long id);
	
	void deletePassRegistrations(PassRegistrations passRegister);
	
	void updatePassRegistrations(PassRegistrations passRegister);
	
	List<PassRegistrations> findUpdatedPass(String passTypeId, String deviceId);
	
	List<GolfCourse> findAllGolfCourses();
	
	List<GolfHoles> findAllGolfHoles();
	
	List<GolfTee> findAllGolfTee();
	
	List<Golf> findAllGolf();
	
	GolfTeeDao getTeeById(Long id);
	
	GolfUser userProfileByEmailId(UserProfile profile);

}
