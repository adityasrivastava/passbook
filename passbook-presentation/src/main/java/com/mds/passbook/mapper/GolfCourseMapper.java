package com.mds.passbook.mapper;

import java.util.List;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mds.passbook.bean.golf.GolfCourse;
import com.mds.passbook.data.repository.golf.dao.GolfCourseDao;

public interface GolfCourseMapper {

	@Mappings({
		@Mapping(source="golfCourseId", target="golfCourseId"),
		@Mapping(source="courseName", target="courseName"),
		@Mapping(source="golf", target="golf", ignore=true)
	})
	GolfCourse golfCourseDAOtoGolfCourseDTO(GolfCourseDao courseDao);
	
	@Mappings({
		@Mapping(source="golfCourseId", target="golfCourseId"),
		@Mapping(source="courseName", target="courseName"),
	})
	GolfCourseDao golfCourseDTOtoGolfCourseDAO(GolfCourse course);
	
	List<GolfCourse> golfCourseDAOListToGolfCourseDTOList(List<GolfCourseDao> courseDao);
	
	List<GolfCourseDao> golfCourseDTOListToGolfCourseDAOList(List<GolfCourse> courseDao);

}
