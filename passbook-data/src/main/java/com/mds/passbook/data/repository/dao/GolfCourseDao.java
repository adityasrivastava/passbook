package com.mds.passbook.data.repository.dao;

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

@Entity(name="GOLF_COURSE")
public class GolfCourseDao extends BaseEntity<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GOLF_COURSE_ID")
	private Long golfCourseId;
	
	@Column(name="COURSE_NAME")
	private String courseName;
	
	@OneToMany(mappedBy="golfCoursesId", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<GolfDao> golf = new ArrayList<GolfDao>();
	
	public GolfCourseDao(){
		
	}

	public GolfCourseDao(Long golfCourseId) {
		super();
		this.golfCourseId = golfCourseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public List<GolfDao> getGolf() {
		return golf;
	}
	public void setGolf(List<GolfDao> golf) {
		this.golf = golf;
	}
	public Long getGolfCourseId() {
		return golfCourseId;
	}
	public void setGolfCourseId(Long golfCourseId) {
		this.golfCourseId = golfCourseId;
	}
	
	@Override
	public String toString() {
		return "GolfCourse [golfCourseId=" + golfCourseId + ", courseName=" + courseName + "]";
	}

	@Override
	public Long getId() {
		
		return golfCourseId;
	}

}
