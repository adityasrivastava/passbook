package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.mds.passbook.bean.pass.PassRegistrations;
import com.mds.passbook.data.repository.user.dao.PassRegistrationsDao;


public interface PassRegistrationsMapper {
	
	@Mappings({
		@Mapping(source="registerPassId", target="registerPassId"),
		@Mapping(source="passTypeId", target="passTypeId"),
		@Mapping(source="serialNumber", target="serialNumber"),
		@Mapping(source="pass", target="pass")
	})
	PassRegistrations PassRegistrationsDAOtoPassRegistrationsDTO(PassRegistrationsDao passRegistrations);
	
	@Mappings({
		@Mapping(source="registerPassId", target="registerPassId"),
		@Mapping(source="passTypeId", target="passTypeId"),
		@Mapping(source="serialNumber", target="serialNumber"),
		@Mapping(source="pass", target="pass")
	})
	PassRegistrationsDao PassRegistrationsDTOtoPassRegistrationsDAO(PassRegistrations passRegistrations);
	
	List<PassRegistrations> PassRegistrationsDAOListToPassRegistrationsDTOList(List<PassRegistrationsDao> passRegistrations);

	List<PassRegistrationsDao> PassRegistrationsDTOListToPassRegistrationsDAOList(List<PassRegistrations> passRegistrations);

}
