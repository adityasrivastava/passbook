package com.mds.passbook.bean;

import java.util.ArrayList;
import java.util.List;

public class GolfUser {
	

	private int userId;
	private String name;
	private int age;
	private String gender;
	private int handicap;
	private GolfPass pass;
	
	private List<Golf> golf = new ArrayList<Golf>();

	public List<Golf> getGolf() {
		return golf;
	}

	public void setGolf(List<Golf> golf) {
		this.golf = golf;
	}



	public GolfUser(){
		
	}
	
	

	
	public GolfUser(String name, int age, String gender, int handicap, GolfPass pass) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.handicap = handicap;
		this.pass = pass;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getHandicap() {
		return handicap;
	}
	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	public GolfPass getPass() {
		return pass;
	}



	public void setPass(GolfPass pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "GolfUser [userId=" + userId + ", name=" + name + ", age=" + age + ", gender=" + gender + ", handicap="
				+ handicap + ", pass=" + pass + "]";
	}

	
}
