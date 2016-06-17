package com.mds.passbook.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mds.passbook.bean.Golf;
import com.mds.passbook.bean.GolfCourse;
import com.mds.passbook.bean.GolfGame;
import com.mds.passbook.bean.GolfHoles;
import com.mds.passbook.bean.GolfPass;
import com.mds.passbook.bean.GolfScore;
import com.mds.passbook.bean.GolfTee;
import com.mds.passbook.bean.GolfUser;
import com.mds.passbook.bean.PassRegistrations;
import com.mds.passbook.data.repository.dao.GolfDao;
import com.mds.passbook.data.repository.dao.GolfScoreDao;
import com.mds.passbook.data.repository.dao.GolfTeeDao;
import com.mds.passbook.data.repository.dao.GolfUserDao;
import com.mds.passbook.data.repository.dao.PassRegistrationsDao;

@Service
public interface GolfService {
	
	void createGame(GolfUser user, String golfCourseId, String holeTypeId, String teeTypeId);
	
	
	void addGolfScore(GolfScore score);
	void deleteGolfScore(GolfScore score);
	void updateGolfScore(GolfScore score);
	
	List<GolfScoreDao> addGolf(GolfGame golf);
	void deleteGolf(Long id);
	void updateGolf(GolfGame golf);
	
	void addGolfPass(GolfPass pass);
	void deleteGolfPass(GolfPass pass);
	GolfPass updateGolfPass(GolfPass pass);
	GolfPass findGolfPassById(Long id);
	
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
	List<Golf> getAllGolf(GolfUserDao user);
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

}
