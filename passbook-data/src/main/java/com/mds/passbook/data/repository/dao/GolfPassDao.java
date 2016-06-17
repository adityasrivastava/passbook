package com.mds.passbook.data.repository.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "GOLF_PASS")
public class GolfPassDao extends BaseEntity<Long> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PASS_ID")
	private Long passId;

	@Column(name = "PUSH_TOKEN")
	private String token;

	@Column(name = "DEVICE_ID")
	private String deviceId;

	@Column(name = "PASS_ADDED")
	private boolean passAdded;
	
	@OneToMany(mappedBy="pass", cascade=CascadeType.ALL)
	private List<PassRegistrationsDao> registeredPass;

	public GolfPassDao(String token, String deviceId, boolean passAdded) {
		this.token = token;
		this.deviceId = deviceId;
		this.passAdded = passAdded;
	}

	public GolfPassDao(String token, boolean passAdded) {
		super();
		this.token = token;
		this.passAdded = passAdded;
	}
	
	

	public List<PassRegistrationsDao> getRegisteredPass() {
		return registeredPass;
	}

	public void setRegisteredPass(List<PassRegistrationsDao> registeredPass) {
		this.registeredPass = registeredPass;
	}

	public Long getPassId() {
		return passId;
	}

	public void setPassId(Long passId) {
		this.passId = passId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public GolfPassDao() {

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

	@Override
	public Long getId() {
		return passId;
	}

}
