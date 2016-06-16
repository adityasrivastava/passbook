package com.mds.passbook.bean;

import java.util.ArrayList;
import java.util.List;

public class GolfCourse {

	private int golfCourseId;
	private String courseName;
	private List<Golf> golf = new ArrayList<Golf>();

	public GolfCourse() {

	}

	public GolfCourse(int golfCourseId, String courseName) {
		this.golfCourseId = golfCourseId;
		this.courseName = courseName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Golf> getGolf() {
		return golf;
	}

	public void setGolf(List<Golf> golf) {
		this.golf = golf;
	}

	public int getGolfCourseId() {
		return golfCourseId;
	}

	public void setGolfCourseId(int golfCourseId) {
		this.golfCourseId = golfCourseId;
	}

	@Override
	public String toString() {
		return "GolfCourse [golfCourseId=" + golfCourseId + ", courseName=" + courseName + "]";
	}

}
