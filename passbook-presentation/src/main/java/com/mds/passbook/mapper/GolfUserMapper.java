package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.mds.passbook.bean.GolfUser;
import com.mds.passbook.data.repository.dao.GolfUserDao;


public interface GolfUserMapper {

	@Mappings({
		@Mapping(source="userId", target="userId"),
		@Mapping(source="name", target="name"),
		@Mapping(source="age", target="age"),
		@Mapping(source="gender", target="gender"),
		@Mapping(source="handicap", target="handicap"),
		@Mapping(source="pass", target="pass"),
		@Mapping(source="golf", target="golf", ignore=true)
	})
	GolfUser GolfUserDAOtoGolfUserDTO(GolfUserDao user);
	
	@Mappings({
		@Mapping(source="userId", target="userId"),
		@Mapping(source="name", target="name"),
		@Mapping(source="age", target="age"),
		@Mapping(source="gender", target="gender"),
		@Mapping(source="handicap", target="handicap"),
		@Mapping(source="pass", target="pass"),
		@Mapping(source="golf", target="golf")
	})
	GolfUserDao GolfUserDTOtoGolfUserDAO(GolfUser user);
	
	List<GolfUser> GolfUserDAOListToGolfUserDTOList(List<GolfUserDao> user);
	
	List<GolfUserDao> GolfUserDTOListToGolfUserDAOList(List<GolfUser> user);

}
