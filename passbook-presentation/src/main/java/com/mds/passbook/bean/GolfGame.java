package com.mds.passbook.bean;

public class GolfGame {
	
	private int id;
	private int courseId;
	private int holeTypeId;
	private int teeTypeId;
	private int userId;
	private String passTypeId;
	private GolfPass pass;
	
	public String getPassTypeId() {
		return passTypeId;
	}
	public void setPassTypeId(String passTypeId) {
		this.passTypeId = passTypeId;
	}
	public GolfPass getPass() {
		return pass;
	}
	public void setPass(GolfPass pass) {
		this.pass = pass;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getHoleTypeId() {
		return holeTypeId;
	}
	public void setHoleTypeId(int holeTypeId) {
		this.holeTypeId = holeTypeId;
	}
	public int getTeeTypeId() {
		return teeTypeId;
	}
	public void setTeeTypeId(int teeTypeId) {
		this.teeTypeId = teeTypeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
