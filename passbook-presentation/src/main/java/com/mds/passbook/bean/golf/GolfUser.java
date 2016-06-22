package com.mds.passbook.bean.golf;

import java.util.ArrayList;
import java.util.List;

import com.mds.passbook.bean.pass.UserPass;

public class GolfUser {

	private Long userId;
	private String name;
	private int age;
	private String gender;
	private int handicap;
	private UserPass pass;

	private List<Golf> golf = new ArrayList<Golf>();

	public List<Golf> getGolf() {
		return golf;
	}

	public void setGolf(List<Golf> golf) {
		this.golf = golf;
	}

	public GolfUser() {

	}

	public GolfUser(Long userId) {
		this.userId = userId;
	}

	public GolfUser(String name, int age, String gender, int handicap, UserPass pass) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.handicap = handicap;
		this.pass = pass;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public UserPass getPass() {
		return pass;
	}

	public void setPass(UserPass pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "GolfUser [userId=" + userId + ", name=" + name + ", age=" + age + ", gender=" + gender + ", handicap="
				+ handicap + ", pass=" + pass + "]";
	}

}
