package com.mds.passbook.data.repository.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity(name="PASS_REGISTRATION")
public class PassRegistrationsDao extends AbstractDateStampEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REGISTER_PASS_ID")
	private int registerPassId;

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
	private GolfPassDao pass;

	public PassRegistrationsDao(String passTypeId, String serialNumber, GolfPassDao pass) {
		this.passTypeId = passTypeId;
		this.serialNumber = serialNumber;
		this.pass = pass;
	}

	public PassRegistrationsDao() {

	}

	public GolfPassDao getPass() {
		return pass;
	}

	public void setPass(GolfPassDao pass) {
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
		return "PassRegistrationsDao [registerPassId=" + registerPassId + ", passTypeId=" + passTypeId
				+ ", serialNumber=" + serialNumber + ", pass=" + pass + "]";
	}
	
	

}
