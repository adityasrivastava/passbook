package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.mds.passbook.bean.GolfPass;
import com.mds.passbook.data.repository.dao.GolfPassDao;

public interface GolfPassMapper {
	
	@Mappings({
		@Mapping(source="passId", target="passId"),
		@Mapping(source="token", target="token"),
		@Mapping(source="deviceId", target="deviceId"),
		@Mapping(source="passAdded", target="passAdded"),
		@Mapping(source="registeredPass", target="registeredPass", ignore=true)
	})
	GolfPass GolfPassDAOtoGolfPassDTO(GolfPassDao pass);
	
	@Mappings({
		@Mapping(source="passId", target="passId"),
		@Mapping(source="token", target="token"),
		@Mapping(source="deviceId", target="deviceId"),
		@Mapping(source="passAdded", target="passAdded"),
		@Mapping(source="registeredPass", target="registeredPass")
	})
	GolfPassDao GolfPassDTOtoGolfPassDAO(GolfPass pass);
	
	List<GolfPass> GolfPassDAOListToGolfPassDTOList(List<GolfPassDao> pass);
	
	List<GolfPassDao> GolfPassDTOListToGolfPassDAOList(List<GolfPass> pass);

}
