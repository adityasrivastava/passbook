package com.mds.passbook.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
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
public class PassbookServiceBean implements PassbookService {

	@Autowired
	GolfService golfService;
	
	@Autowired
	Environment env;
	
	String fileName;

	File newPass;
	InputStream fileInputStream = null;

	public GolfUser updateUserDetails(Map<String, String> passbookDetails, GolfUserDao userDao) {
		GolfUser user;

		user = new GolfUser();

		user.setUserId(userDao.getUserId());
		user.setAge(Integer.parseInt(passbookDetails.get("age")));
		user.setGender(passbookDetails.get("gender"));
		user.setHandicap(Integer.parseInt(passbookDetails.get("handicap")));
		user.setName(passbookDetails.get("name"));
		user.setPass(new GolfPass());

		user = golfService.updateUser(user);

		return user;
	}

	public List<GolfScoreDao> persistNewGame(Map<String, String> passbookDetails, GolfUserDao userDao) {

		GolfGame golfGame;
		List<GolfScoreDao> dao = null;

		golfGame = new GolfGame();

		golfGame.setCourseId(Long.valueOf(passbookDetails.get("golf_course")));
		golfGame.setHoleTypeId(Long.valueOf(passbookDetails.get("hole_type")));
		golfGame.setTeeTypeId(Long.valueOf(passbookDetails.get("tee_type")));
		golfGame.setUserId(userDao.getUserId());
		golfGame.setPassTypeId("pass.com.mds.passbookapp");

		dao = golfService.addGolf(golfGame);

		return dao;

	}

	public List<com.mds.passkit.GolfScore> generateGolfScore(GolfDao golfDao, List<GolfScoreDao> scoreDaoList) {

		List<com.mds.passkit.GolfScore> scores;
		GolfWallet wallet;

		wallet = new GolfWallet();

		scores = new ArrayList<com.mds.passkit.GolfScore>();

		wallet.setSerialNumber("" + golfDao.getId());
		wallet.setUserName(golfDao.getUsersId().getName());
		wallet.setUserGender(golfDao.getUsersId().getGender());
		wallet.setUserAge("" + golfDao.getUsersId().getAge());
		wallet.setGolfCourseName(golfDao.getGolfCoursesId().getCourseName());
		wallet.setGolfHoleType("" + golfDao.getHoleTypesId().getHoles());

		for (GolfScoreDao scoreDao : scoreDaoList) {
			scores.add(new com.mds.passkit.GolfScore(scoreDao.getScore(), scoreDao.getHoleNumber(),
					scoreDao.getGolfTeeDetailsId().getPar(), scoreDao.getGolfTeeDetailsId().getStroke(),
					scoreDao.getGolfTeeDetailsId().getGolfTee().getColor(), scoreDao.getGolfTeeDetailsId().getYards(),
					wallet));
		}

		return scores;
	}
	
	@Override
	public InputStream updatePassbook(String serialNumber,String passTypeIdentifier,Map<String, Object> payload){
		InputStream passInputStream = null;
		
		List<GolfScoreDao> golfDaoList = null;
		List<com.mds.passkit.GolfScore> scores = null;
		GolfWallet wallet;
		
		String fileNamePath = "passes/"+fileName;


		wallet = new GolfWallet();

		golfDaoList = golfService.getScoresById(Long.valueOf(serialNumber));

		GolfDao golfDao = golfDaoList.get(0).getGolf();

		scores = generateGolfScore(golfDao, golfDaoList);

		generatePass(fileNamePath, scores);

		// Get new generated pass
		passInputStream = readPassFile(fileNamePath);
		
		return passInputStream;

	}

	@Override
	public InputStream createPassbook(Map<String, String> passbookDetails, GolfUserDao userDao) {

		List<GolfScoreDao> scoreDao = null;

		GolfDao golfDao;
		InputStream passInputStream = null;
		
		String fileNamePath = "passes/"+fileName;

		List<com.mds.passkit.GolfScore> scores;

		updateUserDetails(passbookDetails, userDao);

		scoreDao = persistNewGame(passbookDetails, userDao);

		golfDao = scoreDao.get(0).getGolf();

		scores = generateGolfScore(golfDao, scoreDao);

		generatePass(fileNamePath, scores);

		passInputStream = readPassFile(fileNamePath);

		return passInputStream;
	}

	@Override
	public long getFileSize() {
		if (newPass != null) {
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

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName+".pkpass";
	}
	
	

}
