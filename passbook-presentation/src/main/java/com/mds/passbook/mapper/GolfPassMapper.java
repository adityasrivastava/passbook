package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mds.passbook.bean.pass.UserPass;
import com.mds.passbook.data.repository.user.dao.UserPassDao;

public interface GolfPassMapper {
	
	@Mappings({
		@Mapping(source="passId", target="passId"),
		@Mapping(source="token", target="token"),
		@Mapping(source="deviceId", target="deviceId"),
		@Mapping(source="passAdded", target="passAdded"),
		@Mapping(source="registeredPass", target="registeredPass", ignore=true)
	})
	UserPass GolfPassDAOtoGolfPassDTO(UserPassDao pass);
	
	@Mappings({
		@Mapping(source="passId", target="passId"),
		@Mapping(source="token", target="token"),
		@Mapping(source="deviceId", target="deviceId"),
		@Mapping(source="passAdded", target="passAdded"),
		@Mapping(source="registeredPass", target="registeredPass")
	})
	UserPassDao GolfPassDTOtoGolfPassDAO(UserPass pass);
	
	List<UserPass> GolfPassDAOListToGolfPassDTOList(List<UserPassDao> pass);
	
	List<UserPassDao> GolfPassDTOListToGolfPassDAOList(List<UserPass> pass);

}
