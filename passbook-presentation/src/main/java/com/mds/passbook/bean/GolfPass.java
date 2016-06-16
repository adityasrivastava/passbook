package com.mds.passbook.bean;

import java.util.List;

public class GolfPass {

	private int passId;
	private String token;
	private String deviceId;
	private boolean passAdded;
	private List<PassRegistrations> registeredPass;

	public GolfPass() {

	}

	public GolfPass(String token, boolean passAdded) {
		this.token = token;
		this.passAdded = passAdded;
	}

	public int getPassId() {
		return passId;
	}

	public void setPassId(int passId) {
		this.passId = passId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public List<PassRegistrations> getRegisteredPass() {
		return registeredPass;
	}

	public void setRegisteredPass(List<PassRegistrations> registeredPass) {
		this.registeredPass = registeredPass;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isPassAdded() {
		return passAdded;
	}

	public void setPassAdded(boolean passAdded) {
		this.passAdded = passAdded;
	}

	@Override
	public String toString() {
		return "GolfPass [passId=" + passId + ", token=" + token + ", passAdded=" + passAdded + "]";
	}

}
