package com.mds.passbook.controller;

public class PassbookStatus {
	
	private static final PassbookStatus instance = new PassbookStatus();
	private static Boolean updateStatus = false;
	
	private PassbookStatus(){
		
	}
	
	public static PassbookStatus getInstance(){
		return instance;
	}

	public static Boolean getUpdateStatus() {
		return PassbookStatus.updateStatus;
	}

	public static void setUpdateStatus(Boolean updateStatus) {
		PassbookStatus.updateStatus = updateStatus;
	}

}
