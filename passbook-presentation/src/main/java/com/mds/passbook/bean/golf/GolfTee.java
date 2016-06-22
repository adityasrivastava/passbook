package com.mds.passbook.bean.golf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GolfTee implements Serializable {

	private Long teeId;
	private String color;
	private List<GolfTeeDetails> teeDetails = new ArrayList<GolfTeeDetails>();

	public GolfTee() {

	}

	public GolfTee(Long teeId, String color, List<GolfTeeDetails> teeDetails) {
		this.teeId = teeId;
		this.color = color;
		this.teeDetails = teeDetails;
	}

	public GolfTee(Long teeId) {
		this.teeId = teeId;
	}

	public List<GolfTeeDetails> getTeeDetails() {
		return teeDetails;
	}

	public void setTeeDetails(List<GolfTeeDetails> teeDetails) {
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
		return "GolfTee [teeId=" + teeId + ", color=" + color + ", teeDetails=" + teeDetails + "]";
	}

}
