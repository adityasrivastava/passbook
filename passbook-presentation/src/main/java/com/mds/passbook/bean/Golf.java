package com.mds.passbook.bean;

import java.util.ArrayList;
import java.util.List;

public class Golf {
	
	private int id;
	private GolfUser usersId;
	private GolfCourse golfCoursesId;
	private GolfHoles holeTypesId;
	private GolfTee teeTypesId;
	private List<GolfScore> scoresId = new ArrayList<GolfScore>();

	
	
	public Golf(int id, GolfUser usersId, GolfCourse golfCoursesId, GolfHoles holeTypesId, GolfTee teeTypesId,
			List<GolfScore> scoresId) {
		this.id = id;
		this.usersId = usersId;
		this.golfCoursesId = golfCoursesId;
		this.holeTypesId = holeTypesId;
		this.teeTypesId = teeTypesId;
		this.scoresId = scoresId;
	}
	
	

	public Golf(int id) {
		this.id = id;
	}



	public Golf(){
		
	}

	public List<GolfScore> getScoresId() {
		return scoresId;
	}

	public void setScoresId(List<GolfScore> scoresId) {
		this.scoresId = scoresId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public GolfUser getUsersId() {
		return usersId;
	}
	public void setUsersId(GolfUser usersId) {
		this.usersId = usersId;
	}
	public GolfCourse getGolfCoursesId() {
		return golfCoursesId;
	}
	public void setGolfCoursesId(GolfCourse golfCoursesId) {
		this.golfCoursesId = golfCoursesId;
	}
	public GolfHoles getHoleTypesId() {
		return holeTypesId;
	}
	public void setHoleTypesId(GolfHoles holeTypesId) {
		this.holeTypesId = holeTypesId;
	}
	public GolfTee getTeeTypesId() {
		return teeTypesId;
	}
	public void setTeeTypesId(GolfTee teeTypesId) {
		this.teeTypesId = teeTypesId;
	}

	@Override
	public String toString() {
		return "Golf [id=" + id + ", usersId=" + usersId + ", golfCoursesId=" + golfCoursesId + ", holeTypesId="
				+ holeTypesId + ", teeTypesId=" + teeTypesId + "]";
	}


}
