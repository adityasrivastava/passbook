package com.mds.passkit;

import java.util.List;

public class GolfScore {

	private int score;
	private int holeNumber;
	private int par;
	private int stroke;
	private String teeType;
	private int yards;

	private GolfWallet user;

	public GolfScore() {

	}

	public GolfScore(int score, int holeNumber, int par, int stroke, String teeType, int yards, GolfWallet user) {
		super();
		this.score = score;
		this.holeNumber = holeNumber;
		this.par = par;
		this.stroke = stroke;
		this.teeType = teeType;
		this.yards = yards;
		this.user = user;
	}

	public int getPar() {
		return par;
	}

	public void setPar(int par) {
		this.par = par;
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}

	public String getTeeType() {
		return teeType;
	}

	public void setTeeType(String teeType) {
		this.teeType = teeType;
	}

	public int getYards() {
		return yards;
	}

	public void setYards(int yards) {
		this.yards = yards;
	}

	public GolfWallet getUser() {
		return user;
	}

	public void setUser(GolfWallet user) {
		this.user = user;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHoleNumber() {
		return holeNumber;
	}

	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}

}
