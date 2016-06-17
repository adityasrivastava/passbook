package com.mds.passbook.bean;

public class GolfGame {
	
	private Long id;
	private Long courseId;
	private Long holeTypeId;
	private Long teeTypeId;
	private Long userId;
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
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Long getHoleTypeId() {
		return holeTypeId;
	}
	public void setHoleTypeId(Long holeTypeId) {
		this.holeTypeId = holeTypeId;
	}
	public Long getTeeTypeId() {
		return teeTypeId;
	}
	public void setTeeTypeId(Long teeTypeId) {
		this.teeTypeId = teeTypeId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	

}
