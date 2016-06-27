package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mds.passbook.bean.golf.GolfHoles;
import com.mds.passbook.data.repository.golf.dao.GolfHolesDao;


public interface GolfHolesMapper {

	@Mappings({
		@Mapping(source="holeTypeId", target="holeTypeId"),
		@Mapping(source="holes", target="holes"),
		@Mapping(source="golf", target="golf", ignore=true)
	})
	GolfHoles golfHolesDAOtoGolfHolesDTO(GolfHolesDao holes);
	
	@Mappings({
		@Mapping(source="holeTypeId", target="holeTypeId"),
		@Mapping(source="holes", target="holes"),
		@Mapping(source="golf", target="golf", ignore=true)
	})
	GolfHolesDao golfHolesDTOtoGolfHolesDAO(GolfHoles holes);
	
	List<GolfHoles> golfHolesDAOListToGolfHolesDTOList(List<GolfHolesDao> holes);
	
	List<GolfHolesDao> golfHolesDTOListToGolfHolesDAOList(List<GolfHoles> holes);

}
