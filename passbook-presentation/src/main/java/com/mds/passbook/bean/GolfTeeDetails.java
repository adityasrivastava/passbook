package com.mds.passbook.bean;

import java.io.Serializable;

public class GolfTeeDetails implements Serializable{
	
	private Long teeTypeId;
	private int holeNumber;
	private int yards;
	private int par;
	private int stroke;
	
	private GolfTee golfTee;

	public int getHoleNumber() {
		return holeNumber;
	}
	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}
	public GolfTee getGolfTee() {
		return golfTee;
	}
	public void setGolfTee(GolfTee golfTee) {
		this.golfTee = golfTee;
	}
	public int getStroke() {
		return stroke;
	}
	public void setStroke(int stroke) {
		this.stroke = stroke;
	}
	public Long getTeeTypeId() {
		return teeTypeId;
	}
	public void setTeeTypeId(Long teeTypeId) {
		this.teeTypeId = teeTypeId;
	}
	public int getYards() {
		return yards;
	}
	public void setYards(int yards) {
		this.yards = yards;
	}
	public int getPar() {
		return par;
	}
	public void setPar(int par) {
		this.par = par;
	}
	@Override
	public String toString() {
		return "GolfTee [teeTypeId=" + teeTypeId + ", yards=" + yards + ", par=" + par + ", holeNumber="+ holeNumber + ", stroke="+ stroke + "]";
	}


}
