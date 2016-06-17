package com.mds.passbook.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mds.passbook.bean.Golf;
import com.mds.passbook.bean.GolfGame;
import com.mds.passbook.bean.GolfPass;
import com.mds.passbook.bean.GolfScore;
import com.mds.passbook.bean.GolfUser;
import com.mds.passbook.bean.PassRegistrations;
import com.mds.passbook.data.repository.dao.GolfDao;
import com.mds.passbook.data.repository.dao.GolfScoreDao;
import com.mds.passbook.data.repository.dao.GolfUserDao;
import com.mds.passkit.GeneratePass;
import com.mds.passkit.GolfWallet;
import com.mds.passbook.service.PassbookService;

@Component
public class PassbookServiceBean implements PassbookService{
	
	@Autowired
	GolfService golfService;
	
	File newPass;
	InputStream fileInputStream = null;

	@Override
	public InputStream createPassbook(String name, String age, String gender, String golf_course, String hole_type, String tee_type, String handicap, GolfUserDao userDao){

		GolfUser user;
		GolfGame golfGame;
		List<GolfScoreDao>  dao = null;
		GolfWallet wallet;
		GolfDao golfDao;
		InputStream passInputStream = null;

		List<com.mds.passkit.GolfScore> scores;
		
		wallet = new GolfWallet();
		user = new GolfUser();
		golfGame = new GolfGame();
		
		scores = new ArrayList<com.mds.passkit.GolfScore>();
		
		user.setUserId(userDao.getUserId());
		user.setAge(Integer.parseInt(age));
		user.setGender(gender);
		user.setHandicap(Integer.parseInt(handicap));
		user.setName(name);
		user.setPass(new GolfPass());

		user = golfService.updateUser(user);

		golfGame.setCourseId(Long.valueOf(golf_course));
		golfGame.setHoleTypeId(Long.valueOf(hole_type));
		golfGame.setTeeTypeId(Long.valueOf(tee_type));
		golfGame.setUserId(user.getUserId());
		golfGame.setPassTypeId("pass.com.mds.passbookapp");

		dao = golfService.addGolf(golfGame);
		
		golfDao = dao.get(0).getGolf();

		wallet.setSerialNumber(""+golfDao.getId());
		wallet.setUserName(golfDao.getUsersId().getName());
		wallet.setUserGender(golfDao.getUsersId().getGender());
		wallet.setUserAge(""+golfDao.getUsersId().getAge());
		wallet.setGolfCourseName(golfDao.getGolfCoursesId().getCourseName());
		wallet.setGolfHoleType(""+golfDao.getHoleTypesId().getHoles());

		
		for(GolfScoreDao scoreDao: dao){
			scores.add(new com.mds.passkit.GolfScore(scoreDao.getScore(), 
					scoreDao.getHoleNumber(), 
					scoreDao.getGolfTeeDetailsId().getPar(), 
					scoreDao.getGolfTeeDetailsId().getStroke(), 
					scoreDao.getGolfTeeDetailsId().getGolfTee().getColor(), 
					scoreDao.getGolfTeeDetailsId().getYards() , 
					wallet)
					);
		}
		
		// Create Pass
		generatePass("passes/file3.pkpass", scores);
		
		passInputStream = readPassFile("passes/file3.pkpass");
		
		return passInputStream;
	}

	@Override
	public long getFileSize() {
		if(newPass != null){
			return newPass.length();
		}
		
		return 0L;
	}

	@Override
	public void generatePass(String absolutePath, List<com.mds.passkit.GolfScore> scores) {
		
		GeneratePass generatePass;
		generatePass = new GeneratePass();
		
		try {
			generatePass.createGenericPass(absolutePath, scores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public InputStream readPassFile(String relativePath) {
		newPass = new File(relativePath);
		
		try {
			fileInputStream = new FileInputStream(newPass);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return fileInputStream;
	}

	@Override
	public void addPassbook(String serialNumber, String deviceId, String passTypeId, String pushToken) {
		
		// Get the Pass Registration with serial number 
		
		PassRegistrations passRegi;
		GolfPass golfPass;
		
		passRegi = golfService.getPassRegisteredBySerialNumberAndPassTypeId(serialNumber, passTypeId);
	
		Long userPassId = passRegi.getPass().getPassId();
		
		// Update pass table with devid and push token
		
		golfPass = golfService.findGolfPassById(userPassId);

		golfPass.setDeviceId(deviceId);
		golfPass.setToken(pushToken);
		golfPass.setPassAdded(true);
		
		golfPass = golfService.updateGolfPass(golfPass);

		
	}

	@Override
	public String updateGolfScore(String hole, String score, String gameId) {
		
		GolfScore golfScore;
		golfScore = new GolfScore();
		golfScore.setHoleNumber(Integer.parseInt(hole));
		golfScore.setGolf(new Golf(Long.valueOf(gameId)));
		golfScore.setScore(Integer.parseInt(score));

		GolfScoreDao scoreUpdated = golfService.updateScore(golfScore);
		
		Golf golf = golfService.getGolfById(scoreUpdated.getGolf().getId());
		
		GolfPass pass = golf.getUsersId().getPass();
		
		return pass.getToken();
	}
	
	

}
