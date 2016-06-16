package com.mds.passbook.data.repository.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "GOLF_SCORE")
public class GolfScoreDao extends AbstractDateStampEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SCORE_ID")
	private int scoreId;

	@Column(name = "SCORE")
	private int score;

	@Column(name = "HOLE_NUMBER")
	private int holeNumber;

	@OneToOne
	@JoinColumn(name = "GOLF_TEE_DETAIL", foreignKey = @ForeignKey(name = "GOLF_TEE_DETAIL_FK") )
	private GolfTeeDetailsDao golfTeeDetailsId;

	@ManyToOne
	@JoinColumn(name = "GOLF_ID", foreignKey = @ForeignKey(name = "GOLF_ID_FK") )
	private GolfDao golf;

	public GolfScoreDao() {

	}

	public GolfScoreDao(int score, int holeNumber, GolfDao golf, GolfTeeDetailsDao golfTeeDetailsId) {
		this.score = score;
		this.holeNumber = holeNumber;
		this.golf = golf;
		this.golfTeeDetailsId = golfTeeDetailsId;
	}

	public int getHoleNumber() {
		return holeNumber;
	}

	public void setHoleNumber(int holeNumber) {
		this.holeNumber = holeNumber;
	}

	// public GolfScoreDao(GolfTeeDetailsDao golfTeeDetailsId) {
	//
	// this.golfTeeDetailsId = golfTeeDetailsId;
	// }
	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public GolfTeeDetailsDao getGolfTeeDetailsId() {
		return golfTeeDetailsId;
	}

	public void setGolfTeeDetailsId(GolfTeeDetailsDao golfTeeDetailsId) {
		this.golfTeeDetailsId = golfTeeDetailsId;
	}

	public GolfDao getGolf() {
		return golf;
	}

	public void setGolf(GolfDao golf) {
		this.golf = golf;
	}

	public int getId() {
		return scoreId;
	}

	public void setId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "GolfScore [scoreId=" + scoreId + ", score=" + score + ", golf=" + golf + "]";
	}

}
