package com.mds.passbook.data.repository.golf.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "GOLF_TEE")
public class GolfTeeDao extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEE_ID")
	private Long teeId;

	@Column(name = "COLOR")
	private String color;

	@OneToMany(mappedBy = "holeId", cascade = CascadeType.ALL)
	private List<GolfCourseDao> courseIds;

	@OneToMany(mappedBy = "golfTee", cascade = CascadeType.ALL)
	private List<GolfTeeDetailsDao> teeDetails = new ArrayList<GolfTeeDetailsDao>();

	public GolfTeeDao() {

	}

	public List<GolfCourseDao> getCourseId() {
		return courseIds;
	}

	public void setCourseId(List<GolfCourseDao> courseIds) {
		this.courseIds = courseIds;
	}

	public GolfTeeDao(Long teeId) {
		this.teeId = teeId;
	}

	public List<GolfTeeDetailsDao> getTeeDetails() {
		return teeDetails;
	}

	public void setTeeDetails(List<GolfTeeDetailsDao> teeDetails) {
		this.teeDetails = teeDetails;
	}

	public Long getTeeId() {
		return teeId;
	}

	public void setTeeId(Long teeId) {
		this.teeId = teeId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "GolfTee [teeId=" + teeId + ", color=" + color + "]";
	}

	@Override
	public Long getId() {
		return teeId;
	}

}
