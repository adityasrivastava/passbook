package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.mds.passbook.bean.GolfTee;
import com.mds.passbook.data.repository.dao.GolfTeeDao;

public interface GolfTeeMapper {


	@Mappings({
		@Mapping(source="teeId", target="teeId"),
		@Mapping(source="color", target="color"),
		@Mapping(source="teeDetails", target="teeDetails")
	})
	GolfTee GolfTeeDAOtoGolfTeeDTO(GolfTeeDao tee);
	

	@Mappings({
		@Mapping(source="teeId", target="teeId"),
		@Mapping(source="color", target="color"),
		@Mapping(source="teeDetails", target="teeDetails")
	})
	GolfTeeDao GolfTeeDTOtoGolfTeeDAO(GolfTee tee);
	
	List<GolfTee> GolfTeeDAOListToGolfTeeDTOList(List<GolfTeeDao> tee);
	
	List<GolfTeeDao> GolfTeeDTOListToGolfTeeDAOList(List<GolfTee> tee);
}
