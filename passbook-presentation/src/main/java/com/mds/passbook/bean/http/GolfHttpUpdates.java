package com.mds.passbook.bean.http;

import java.sql.Timestamp;
import java.util.List;

public class GolfHttpUpdates {
	
	private String[] serialNumbers;
	private String lastUpdated;
	
	public String[] getSerialNumbers() {
		return serialNumbers;
	}
	public void setSerialNumbers(String[] serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public String currentTimeStamp(){
		return ""+new Timestamp(System.currentTimeMillis() - (1000 * 60 * 60));
	}

}
