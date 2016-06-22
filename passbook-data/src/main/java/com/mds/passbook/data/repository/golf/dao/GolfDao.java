package com.mds.passbook.data.repository.golf.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.mds.passbook.data.repository.user.dao.PassRegistrationsDao;


@Entity(name="GOLF")
public class GolfDao extends BaseEntity<Long> {
	
	@Id
	@GeneratedValue
	@Column(name="GOLF_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="GOLF_USER",
				foreignKey=@ForeignKey(name="GOLF_USER_FK"))
	private GolfUserDao usersId;
	
	@ManyToOne
	@JoinColumn(name="GOLF_COURSE",
				foreignKey=@ForeignKey(name="GOLF_COURSE_FK"))
	private GolfCourseDao golfCoursesId;
	
	@ManyToOne
	@JoinColumn(name="GOLF_HOLE",
				foreignKey=@ForeignKey(name="GOLF_HOLE_FK"))
	private GolfHolesDao holeTypesId;
	
	@OneToOne
	@JoinColumn(name="GOLF_TEE")
	private GolfTeeDao teeTypesId;
	
	@OneToOne(mappedBy="golf")
	@JoinColumn(name="GOLF_PASS_REGISTERED")
	private PassRegistrationsDao passRegistrationsId;
	
	
	@OneToMany(mappedBy="golf", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<GolfScoreDao> scoresId = new ArrayList<GolfScoreDao>();

	public GolfDao(){
		
	}
	
	public GolfDao(Long id){
		this.id = id;
	}
	
	

	public GolfDao(GolfUserDao usersId, GolfCourseDao golfCoursesId, GolfHolesDao holeTypesId, GolfTeeDao teeTypesId) {
		super();
		this.usersId = usersId;
		this.golfCoursesId = golfCoursesId;
		this.holeTypesId = holeTypesId;
		this.teeTypesId = teeTypesId;
	}



	public PassRegistrationsDao getPassRegistrationsId() {
		return passRegistrationsId;
	}

	public void setPassRegistrationsId(PassRegistrationsDao passRegistrationsId) {
		this.passRegistrationsId = passRegistrationsId;
	}

	public List<GolfScoreDao> getScoresId() {
		return scoresId;
	}

	public void setScoresId(List<GolfScoreDao> scoresId) {
		this.scoresId = scoresId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public GolfUserDao getUsersId() {
		return usersId;
	}
	public void setUsersId(GolfUserDao usersId) {
		this.usersId = usersId;
	}
	public GolfCourseDao getGolfCoursesId() {
		return golfCoursesId;
	}
	public void setGolfCoursesId(GolfCourseDao golfCoursesId) {
		this.golfCoursesId = golfCoursesId;
	}
	public GolfHolesDao getHoleTypesId() {
		return holeTypesId;
	}
	public void setHoleTypesId(GolfHolesDao holeTypesId) {
		this.holeTypesId = holeTypesId;
	}
	public GolfTeeDao getTeeTypesId() {
		return teeTypesId;
	}
	public void setTeeTypesId(GolfTeeDao teeTypesId) {
		this.teeTypesId = teeTypesId;
	}


	@Override
	public String toString() {
		return "Golf [id=" + id + ", usersId=" + usersId + ", golfCoursesId=" + golfCoursesId + ", holeTypesId="
				+ holeTypesId + ", teeTypesId=" + teeTypesId + "]";
	}
	
	

}
