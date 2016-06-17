package com.mds.passbook.bean;

public class GolfScore {
	
	private Long scoreId;
	private int score;
	private int holeNumber;
	private GolfTeeDetails golfTeeDetailsId;
	private Golf golf;
	
	
	
	public int getHoleNumber() {
		return holeNumber;
	}
	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}
	public Long getScoreId() {
		return scoreId;
	}
	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}
	public GolfTeeDetails getGolfTeeDetailsId() {
		return golfTeeDetailsId;
	}
	public void setGolfTeeDetailsId(GolfTeeDetails golfTeeDetailsId) {
		this.golfTeeDetailsId = golfTeeDetailsId;
	}
	public Golf getGolf() {
		return golf;
	}
	public void setGolf(Golf golf) {
		this.golf = golf;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "GolfScore [scoreId=" + scoreId + ", score=" + score + ", golf=" + golf
				+"]";
	}

}
