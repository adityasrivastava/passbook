package com.mds.passbook.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mds.passbook.data.repository.dao.GolfDao;
import com.mds.passbook.data.repository.dao.GolfScoreDao;
import com.mds.passbook.data.repository.dao.GolfUserDao;

@Service
public interface PassbookService {
	
	InputStream createPassbook(Map<String,String> passbookDetails, GolfUserDao userDao);
	long getFileSize();
	void generatePass(String absolutePath, List<com.mds.passkit.GolfScore> scores);
	InputStream readPassFile(String relativePath);
	
	void addPassbook(String serialNumber, String deviceId, String passTypeId, String pushToken);
	String updateGolfScore(String hole, String score, String gameId);
	
	List<com.mds.passkit.GolfScore> generateGolfScore(GolfDao golfDao, List<GolfScoreDao> scoreDaoList);

}
