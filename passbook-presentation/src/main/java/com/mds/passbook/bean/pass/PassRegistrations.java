package com.mds.passbook.bean.pass;

import com.mds.passbook.data.repository.golf.dao.GolfDao;

public class PassRegistrations {

	private int registerPassId;

	private String passTypeId;

	private String serialNumber;

	private UserPass pass;
	
	private GolfDao golf;

	public GolfDao getGolf() {
		return golf;
	}

	public void setGolf(GolfDao golf) {
		this.golf = golf;
	}

	public PassRegistrations(String passTypeId, String serialNumber) {
		this.passTypeId = passTypeId;
		this.serialNumber = serialNumber;
	}

	public PassRegistrations(int registerPassId) {
		this.registerPassId = registerPassId;
	}

	public PassRegistrations() {

	}

	public UserPass getPass() {
		return pass;
	}

	public void setPass(UserPass pass) {
		this.pass = pass;
	}

	public int getRegisterPassId() {
		return registerPassId;
	}

	public void setRegisterPassId(int registerPassId) {
		this.registerPassId = registerPassId;
	}

	public String getPassTypeId() {
		return passTypeId;
	}

	public void setPassTypeId(String passTypeId) {
		this.passTypeId = passTypeId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return "PassRegistrations [registerPassId=" + registerPassId + ", passTypeId=" + passTypeId + ", serialNumber="
				+ serialNumber + ", pass=" + pass + "]";
	}

	
}
