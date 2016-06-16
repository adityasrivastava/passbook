package com.mds.passbook.bean;

public class PassRegistrations {

	private int registerPassId;

	private String passTypeId;

	private String serialNumber;

	private GolfPass pass;

	public PassRegistrations(String passTypeId, String serialNumber) {
		this.passTypeId = passTypeId;
		this.serialNumber = serialNumber;
	}

	public PassRegistrations(int registerPassId) {
		this.registerPassId = registerPassId;
	}

	public PassRegistrations() {

	}

	public GolfPass getPass() {
		return pass;
	}

	public void setPass(GolfPass pass) {
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
