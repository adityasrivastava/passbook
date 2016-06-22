package com.mds.passbook.bean.golf;

import java.util.ArrayList;
import java.util.List;

public class GolfHoles {

	private Long holeTypeId;
	private int holes;

	private List<Golf> golf = new ArrayList<Golf>();

	public GolfHoles() {

	}
	
	public GolfHoles(Long holeTypeId){
		this.holeTypeId = holeTypeId;
	}

	public GolfHoles(Long holeTypeId, int holes) {
		this.holeTypeId = holeTypeId;
		this.holes = holes;
	}

	public List<Golf> getGolf() {
		return golf;
	}

	public void setGolf(List<Golf> golf) {
		this.golf = golf;
	}

	public Long getHoleTypeId() {
		return holeTypeId;
	}

	public void setHoleTypeId(Long holeTypeId) {
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
