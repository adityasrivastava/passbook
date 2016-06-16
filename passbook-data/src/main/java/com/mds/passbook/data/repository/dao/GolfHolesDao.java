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

@Entity(name="GOLF_HOLES")
public class GolfHolesDao extends AbstractDateStampEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="HOLDE_TYPE_ID")
	private int holeTypeId;
	
	@Column(name="HOLES")
	private int holes;
	
	@OneToMany(mappedBy="holeTypesId", cascade=CascadeType.ALL)
	private List<GolfDao> golf = new ArrayList<GolfDao>();
	
	public GolfHolesDao(){
		
	}

	public GolfHolesDao(int holeTypeId) {
		super();
		this.holeTypeId = holeTypeId;
	}
	public List<GolfDao> getGolf() {
		return golf;
	}
	public void setGolf(List<GolfDao> golf) {
		this.golf = golf;
	}
	public int getHoleTypeId() {
		return holeTypeId;
	}
	public void setHoleTypeId(int holeTypeId) {
		this.holeTypeId = holeTypeId;
	}
	
	public int getHoles() {
		return holes;
	}
	public void setHoles(int holes) {
		this.holes = holes;
	}
	@Override
	public String toString() {
		return "GolfHoles [holeTypeId=" + holeTypeId + ", holes=" + holes + "]";
	}

	
}
