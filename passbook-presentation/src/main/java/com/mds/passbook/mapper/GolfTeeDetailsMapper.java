package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mds.passbook.bean.golf.GolfTeeDetails;
import com.mds.passbook.data.repository.golf.dao.GolfTeeDetailsDao;


public interface GolfTeeDetailsMapper {
	
	@Mappings({
		@Mapping(source="teeTypeId", target="teeTypeId"),
		@Mapping(source="holeNumber", target="holeNumber"),
		@Mapping(source="yards", target="yards"),
		@Mapping(source="par", target="par"),
		@Mapping(source="stroke", target="stroke"),
		@Mapping(source="golfTee", target="golfTee", ignore=true)
	})
	GolfTeeDetails GolfTeeDetailsDAOtoGolfTeeDetailsDTO(GolfTeeDetailsDao teeDetails);
	

	@Mappings({
		@Mapping(source="teeTypeId", target="teeTypeId"),
		@Mapping(source="holeNumber", target="holeNumber"),
		@Mapping(source="yards", target="yards"),
		@Mapping(source="par", target="par"),
		@Mapping(source="stroke", target="stroke"),
		@Mapping(source="golfTee", target="golfTee")
	})
	GolfTeeDetailsDao GolfTeeDetailsDTOtoGolfTeeDetailsDAO(GolfTeeDetails teeDetails);
	
	
	List<GolfTeeDetails> GolfTeeDetailsDAOListToGolfTeeDetailsDTOList(List<GolfTeeDetailsDao> teeDetails);
	
	List<GolfTeeDetailsDao> GolfTeeDetailsDTOListToGolfTeeDetailsDAOList(List<GolfTeeDetails> teeDetails);

}
