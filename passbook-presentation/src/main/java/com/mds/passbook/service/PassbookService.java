package com.mds.passbook.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mds.passbook.data.repository.dao.GolfUserDao;

@Service
public interface PassbookService {
	
	InputStream createPassbook(String name, String age, String gender, String golf_course, String hole_type, String tee_type, String handicap, GolfUserDao userDao);
	long getFileSize();
	void generatePass(String absolutePath, List<com.mds.passkit.GolfScore> scores);
	InputStream readPassFile(String relativePath);
	
	void addPassbook(String serialNumber, String deviceId, String passTypeId, String pushToken);
	String updateGolfScore(String hole, String score, String gameId);
	

}
