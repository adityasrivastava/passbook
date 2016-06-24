package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.mds.passbook.bean.golf.Golf;
import com.mds.passbook.data.repository.golf.dao.GolfDao;

@Mapper
public interface GolfMapper extends GolfCourseMapper, GolfHolesMapper, GolfPassMapper, GolfScoreMapper, GolfTeeDetailsMapper, GolfTeeMapper, GolfUserMapper, PassRegistrationsMapper {
	
	GolfMapper INSTANCE = Mappers.getMapper(GolfMapper.class);

	@Mappings({
	@Mapping(source="golfCoursesId", target="golfCoursesId"),
	@Mapping(source="scoresId", target = "scoresId"),
	@Mapping(source="teeTypesId", target="teeTypesId"),
	@Mapping(source="holeTypesId", target="holeTypesId"),
	@Mapping(source="usersId", target="usersId"),
	@Mapping(source="passRegistrationsId", target="passRegistrationsId")
	})
	Golf golfDAOtoGolfDTO(GolfDao golf);
	

	@Mappings({
		@Mapping(source="golfCoursesId", target="golfCoursesId"),
		@Mapping(source="scoresId", target = "scoresId"),
		@Mapping(source="teeTypesId", target="teeTypesId"),
		@Mapping(source="holeTypesId", target="holeTypesId"),
		@Mapping(source="usersId", target="usersId"),
		@Mapping(source="passRegistrationsId", target="passRegistrationsId")
	})
	GolfDao golfDTOtoGolfDAO(Golf golf);

	List<Golf> golfDAOListToGolfDTOList(List<GolfDao> golf);
	
	List<GolfDao> golfDTOListToGolfDAOList(List<Golf> golf);
}
