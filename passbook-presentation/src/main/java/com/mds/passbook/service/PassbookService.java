package com.mds.passbook.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mds.passbook.bean.http.GolfHttpUpdates;
import com.mds.passbook.data.repository.golf.dao.GolfDao;
import com.mds.passbook.data.repository.golf.dao.GolfScoreDao;
import com.mds.passbook.data.repository.golf.dao.GolfUserDao;

@Service
public interface PassbookService {

	Long persistPassbook(Map<String, String> passbookDetails, GolfUserDao userDao);
	
	InputStream createPassbook(Long gameId);

	InputStream updatePassbook(String serialNumber, String passTypeIdentifier, Map<String, Object> payload);

	long getFileSize();

	void generatePass(String absolutePath, List<com.mds.passkit.GolfScore> scores);

	InputStream readPassFile(String relativePath);

	void addPassbook(String serialNumber, String deviceId, String passTypeId, String pushToken);

	String updateGolfScore(String hole, String score, String gameId);

	List<com.mds.passkit.GolfScore> generateGolfScore(List<GolfScoreDao> scoreDaoList);

	String getFileName();

	void setFileName(String fileName);

	GolfHttpUpdates getListOfUpdatePass(String deviceId, String passTypeId, String updateSinceDate);

}
