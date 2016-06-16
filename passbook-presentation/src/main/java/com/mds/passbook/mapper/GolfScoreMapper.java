package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.mds.passbook.bean.GolfScore;
import com.mds.passbook.data.repository.dao.GolfScoreDao;

public interface GolfScoreMapper {
	
	@Mappings({
		@Mapping(source="scoreId", target="scoreId"),
		@Mapping(source="score", target="score"),
		@Mapping(source="holeNumber", target="holeNumber"),
		@Mapping(source="golfTeeDetailsId", target="golfTeeDetailsId", ignore=true),
		@Mapping(source="golf", target="golf", ignore=true)
	})
	GolfScore GolfScoreDAOtoGolfScoreDTO(GolfScoreDao score);

	@Mappings({
		@Mapping(source="scoreId", target="scoreId"),
		@Mapping(source="score", target="score"),
		@Mapping(source="holeNumber", target="holeNumber"),
		@Mapping(source="golfTeeDetailsId", target="golfTeeDetailsId", ignore=true),
		@Mapping(source="golf", target="golf", ignore=true)
	})
	GolfScoreDao GolfScoreDTOtoGolfScoreDAO(GolfScore score);
	
	
	List<GolfScore> GolfScoreDAOListToGolfScoreDTOList(List<GolfScoreDao> score);
	
	List<GolfScoreDao> GolfScoreDTOListToGolfScoreDAOList(List<GolfScore> score);


}
