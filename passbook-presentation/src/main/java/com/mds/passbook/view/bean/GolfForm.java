package com.mds.passbook.view.bean;

import com.mds.passbook.bean.GolfCourse;
import com.mds.passbook.bean.GolfHoles;
import com.mds.passbook.bean.GolfScore;
import com.mds.passbook.bean.GolfTee;
import com.mds.passbook.bean.GolfUser;

public class GolfForm {

	private long formId;
	private String name;
	private int age;
	private GolfCourse course;
	private GolfHoles hole;
	private GolfTee tee;
	private GolfUser user;
	private GolfScore score;

	public GolfForm() {
		this.course = new GolfCourse();
		this.hole = new GolfHoles();
		this.tee = new GolfTee();
		this.user = new GolfUser();
		this.score = new GolfScore();
	}

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public GolfCourse getCourse() {
		return course;
	}

	public void setCourse(GolfCourse course) {
		this.course = course;
	}

	public GolfHoles getHole() {
		return hole;
	}

	public void setHole(GolfHoles hole) {
		this.hole = hole;
	}

	public GolfTee getTee() {
		return tee;
	}

	public void setTee(GolfTee tee) {
		this.tee = tee;
	}

	public GolfUser getUser() {
		return user;
	}

	public void setUser(GolfUser user) {
		this.user = user;
	}

	public GolfScore getScore() {
		return score;
	}

	public void setScore(GolfScore score) {
		this.score = score;
	}

}
