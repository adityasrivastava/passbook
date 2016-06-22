package com.mds.passbook.data.repository.user.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.mds.passbook.data.repository.golf.dao.BaseEntity;
import com.mds.passbook.data.repository.golf.dao.GolfDao;


@Entity(name="PASS_REGISTRATION")
public class PassRegistrationsDao extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REGISTER_PASS_ID")
	private Long registerPassId;

	@Column(name = "PASS_TYPE_ID")
	private String passTypeId;

	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;

	@OneToOne
	@JoinColumn(name="GOLF_ID",
				foreignKey=@ForeignKey(name="GOLF_ID_FK"))
	private GolfDao golf;
	
	@ManyToOne
	@JoinColumn(name="GOLF_PASS_ID",
	foreignKey=@ForeignKey(name="GOLF_PASS_ID_FK"))
	private UserPassDao pass;

	public PassRegistrationsDao(String passTypeId, String serialNumber, UserPassDao pass) {
		this.passTypeId = passTypeId;
		this.serialNumber = serialNumber;
		this.pass = pass;
	}

	public PassRegistrationsDao() {

	}

	public GolfDao getGolf() {
		return golf;
	}

	public void setGolf(GolfDao golf) {
		this.golf = golf;
	}

	public UserPassDao getPass() {
		return pass;
	}

	public void setPass(UserPassDao pass) {
		this.pass = pass;
	}

	public Long getRegisterPassId() {
		return registerPassId;
	}

	public void setRegisterPassId(Long registerPassId) {
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
		return "PassRegistrationsDao [registerPassId=" + registerPassId + ", passTypeId=" + passTypeId
				+ ", serialNumber=" + serialNumber + ", pass=" + pass + "]";
	}

	@Override
	public Long getId() {
		return registerPassId;
	}
	
	

}
