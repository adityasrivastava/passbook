package com.mds.passbook.bean;

import java.util.ArrayList;
import java.util.List;

public class GolfCourse {

	private Long golfCourseId;
	private String courseName;
	private List<Golf> golf = new ArrayList<Golf>();

	public GolfCourse() {

	}

	public GolfCourse(Long golfCourseId, String courseName) {
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

	public Long getGolfCourseId() {
		return golfCourseId;
	}

	public void setGolfCourseId(Long golfCourseId) {
		this.golfCourseId = golfCourseId;
	}

	@Override
	public String toString() {
		return "GolfCourse [golfCourseId=" + golfCourseId + ", courseName=" + courseName + "]";
	}

}
