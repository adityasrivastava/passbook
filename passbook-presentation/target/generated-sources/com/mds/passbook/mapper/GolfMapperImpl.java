package com.mds.passbook.mapper;

import com.mds.passbook.bean.Golf;
import com.mds.passbook.bean.GolfCourse;
import com.mds.passbook.bean.GolfHoles;
import com.mds.passbook.bean.GolfPass;
import com.mds.passbook.bean.GolfScore;
import com.mds.passbook.bean.GolfTee;
import com.mds.passbook.bean.GolfTeeDetails;
import com.mds.passbook.bean.GolfUser;
import com.mds.passbook.bean.PassRegistrations;
import com.mds.passbook.data.repository.dao.GolfCourseDao;
import com.mds.passbook.data.repository.dao.GolfDao;
import com.mds.passbook.data.repository.dao.GolfHolesDao;
import com.mds.passbook.data.repository.dao.GolfPassDao;
import com.mds.passbook.data.repository.dao.GolfScoreDao;
import com.mds.passbook.data.repository.dao.GolfTeeDao;
import com.mds.passbook.data.repository.dao.GolfTeeDetailsDao;
import com.mds.passbook.data.repository.dao.GolfUserDao;
import com.mds.passbook.data.repository.dao.PassRegistrationsDao;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2016-06-16T17:00:51+0530",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_71 (Oracle Corporation)"
)
public class GolfMapperImpl implements GolfMapper {

    @Override
    public PassRegistrations PassRegistrationsDAOtoPassRegistrationsDTO(PassRegistrationsDao passRegistrations) {
        if ( passRegistrations == null ) {
            return null;
        }

        PassRegistrations passRegistrations_ = new PassRegistrations();

        passRegistrations_.setPassTypeId( passRegistrations.getPassTypeId() );
        passRegistrations_.setSerialNumber( passRegistrations.getSerialNumber() );
        passRegistrations_.setRegisterPassId( passRegistrations.getRegisterPassId() );
        passRegistrations_.setPass( GolfPassDAOtoGolfPassDTO( passRegistrations.getPass() ) );

        return passRegistrations_;
    }

    @Override
    public PassRegistrationsDao PassRegistrationsDTOtoPassRegistrationsDAO(PassRegistrations passRegistrations) {
        if ( passRegistrations == null ) {
            return null;
        }

        PassRegistrationsDao passRegistrationsDao = new PassRegistrationsDao();

        passRegistrationsDao.setPassTypeId( passRegistrations.getPassTypeId() );
        passRegistrationsDao.setSerialNumber( passRegistrations.getSerialNumber() );
        passRegistrationsDao.setRegisterPassId( passRegistrations.getRegisterPassId() );
        passRegistrationsDao.setPass( GolfPassDTOtoGolfPassDAO( passRegistrations.getPass() ) );

        return passRegistrationsDao;
    }

    @Override
    public List<PassRegistrations> PassRegistrationsDAOListToPassRegistrationsDTOList(List<PassRegistrationsDao> passRegistrations) {
        if ( passRegistrations == null ) {
            return null;
        }

        List<PassRegistrations> list = new ArrayList<PassRegistrations>();
        for ( PassRegistrationsDao passRegistrationsDao : passRegistrations ) {
            list.add( PassRegistrationsDAOtoPassRegistrationsDTO( passRegistrationsDao ) );
        }

        return list;
    }

    @Override
    public List<PassRegistrationsDao> PassRegistrationsDTOListToPassRegistrationsDAOList(List<PassRegistrations> passRegistrations) {
        if ( passRegistrations == null ) {
            return null;
        }

        List<PassRegistrationsDao> list = new ArrayList<PassRegistrationsDao>();
        for ( PassRegistrations passRegistrations_ : passRegistrations ) {
            list.add( PassRegistrationsDTOtoPassRegistrationsDAO( passRegistrations_ ) );
        }

        return list;
    }

    @Override
    public GolfUser GolfUserDAOtoGolfUserDTO(GolfUserDao user) {
        if ( user == null ) {
            return null;
        }

        GolfUser golfUser = new GolfUser();

        golfUser.setGender( user.getGender() );
        golfUser.setPass( GolfPassDAOtoGolfPassDTO( user.getPass() ) );
        golfUser.setHandicap( user.getHandicap() );
        golfUser.setName( user.getName() );
        golfUser.setUserId( user.getUserId() );
        golfUser.setAge( user.getAge() );

        return golfUser;
    }

    @Override
    public GolfUserDao GolfUserDTOtoGolfUserDAO(GolfUser user) {
        if ( user == null ) {
            return null;
        }

        GolfUserDao golfUserDao = new GolfUserDao();

        golfUserDao.setGolf( golfDTOListToGolfDAOList( user.getGolf() ) );
        golfUserDao.setGender( user.getGender() );
        golfUserDao.setPass( GolfPassDTOtoGolfPassDAO( user.getPass() ) );
        golfUserDao.setHandicap( user.getHandicap() );
        golfUserDao.setName( user.getName() );
        golfUserDao.setUserId( user.getUserId() );
        golfUserDao.setAge( user.getAge() );

        return golfUserDao;
    }

    @Override
    public List<GolfUser> GolfUserDAOListToGolfUserDTOList(List<GolfUserDao> user) {
        if ( user == null ) {
            return null;
        }

        List<GolfUser> list = new ArrayList<GolfUser>();
        for ( GolfUserDao golfUserDao : user ) {
            list.add( GolfUserDAOtoGolfUserDTO( golfUserDao ) );
        }

        return list;
    }

    @Override
    public List<GolfUserDao> GolfUserDTOListToGolfUserDAOList(List<GolfUser> user) {
        if ( user == null ) {
            return null;
        }

        List<GolfUserDao> list = new ArrayList<GolfUserDao>();
        for ( GolfUser golfUser : user ) {
            list.add( GolfUserDTOtoGolfUserDAO( golfUser ) );
        }

        return list;
    }

    @Override
    public GolfTee GolfTeeDAOtoGolfTeeDTO(GolfTeeDao tee) {
        if ( tee == null ) {
            return null;
        }

        GolfTee golfTee = new GolfTee();

        golfTee.setTeeDetails( GolfTeeDetailsDAOListToGolfTeeDetailsDTOList( tee.getTeeDetails() ) );
        golfTee.setColor( tee.getColor() );
        golfTee.setTeeId( tee.getTeeId() );

        return golfTee;
    }

    @Override
    public GolfTeeDao GolfTeeDTOtoGolfTeeDAO(GolfTee tee) {
        if ( tee == null ) {
            return null;
        }

        GolfTeeDao golfTeeDao = new GolfTeeDao();

        golfTeeDao.setTeeDetails( GolfTeeDetailsDTOListToGolfTeeDetailsDAOList( tee.getTeeDetails() ) );
        golfTeeDao.setColor( tee.getColor() );
        golfTeeDao.setTeeId( tee.getTeeId() );

        return golfTeeDao;
    }

    @Override
    public List<GolfTee> GolfTeeDAOListToGolfTeeDTOList(List<GolfTeeDao> tee) {
        if ( tee == null ) {
            return null;
        }

        List<GolfTee> list = new ArrayList<GolfTee>();
        for ( GolfTeeDao golfTeeDao : tee ) {
            list.add( GolfTeeDAOtoGolfTeeDTO( golfTeeDao ) );
        }

        return list;
    }

    @Override
    public List<GolfTeeDao> GolfTeeDTOListToGolfTeeDAOList(List<GolfTee> tee) {
        if ( tee == null ) {
            return null;
        }

        List<GolfTeeDao> list = new ArrayList<GolfTeeDao>();
        for ( GolfTee golfTee : tee ) {
            list.add( GolfTeeDTOtoGolfTeeDAO( golfTee ) );
        }

        return list;
    }

    @Override
    public GolfTeeDetails GolfTeeDetailsDAOtoGolfTeeDetailsDTO(GolfTeeDetailsDao teeDetails) {
        if ( teeDetails == null ) {
            return null;
        }

        GolfTeeDetails golfTeeDetails = new GolfTeeDetails();

        golfTeeDetails.setPar( teeDetails.getPar() );
        golfTeeDetails.setTeeTypeId( teeDetails.getTeeTypeId() );
        golfTeeDetails.setHoleNumber( teeDetails.getHoleNumber() );
        golfTeeDetails.setStroke( teeDetails.getStroke() );
        golfTeeDetails.setYards( teeDetails.getYards() );

        return golfTeeDetails;
    }

    @Override
    public GolfTeeDetailsDao GolfTeeDetailsDTOtoGolfTeeDetailsDAO(GolfTeeDetails teeDetails) {
        if ( teeDetails == null ) {
            return null;
        }

        GolfTeeDetailsDao golfTeeDetailsDao = new GolfTeeDetailsDao();

        golfTeeDetailsDao.setPar( teeDetails.getPar() );
        golfTeeDetailsDao.setTeeTypeId( teeDetails.getTeeTypeId() );
        golfTeeDetailsDao.setHoleNumber( teeDetails.getHoleNumber() );
        golfTeeDetailsDao.setStroke( teeDetails.getStroke() );
        golfTeeDetailsDao.setYards( teeDetails.getYards() );
        golfTeeDetailsDao.setGolfTee( GolfTeeDTOtoGolfTeeDAO( teeDetails.getGolfTee() ) );

        return golfTeeDetailsDao;
    }

    @Override
    public List<GolfTeeDetails> GolfTeeDetailsDAOListToGolfTeeDetailsDTOList(List<GolfTeeDetailsDao> teeDetails) {
        if ( teeDetails == null ) {
            return null;
        }

        List<GolfTeeDetails> list_ = new ArrayList<GolfTeeDetails>();
        for ( GolfTeeDetailsDao golfTeeDetailsDao : teeDetails ) {
            list_.add( GolfTeeDetailsDAOtoGolfTeeDetailsDTO( golfTeeDetailsDao ) );
        }

        return list_;
    }

    @Override
    public List<GolfTeeDetailsDao> GolfTeeDetailsDTOListToGolfTeeDetailsDAOList(List<GolfTeeDetails> teeDetails) {
        if ( teeDetails == null ) {
            return null;
        }

        List<GolfTeeDetailsDao> list_ = new ArrayList<GolfTeeDetailsDao>();
        for ( GolfTeeDetails golfTeeDetails : teeDetails ) {
            list_.add( GolfTeeDetailsDTOtoGolfTeeDetailsDAO( golfTeeDetails ) );
        }

        return list_;
    }

    @Override
    public GolfScore GolfScoreDAOtoGolfScoreDTO(GolfScoreDao score) {
        if ( score == null ) {
            return null;
        }

        GolfScore golfScore = new GolfScore();

        golfScore.setScoreId( score.getScoreId() );
        golfScore.setScore( score.getScore() );
        golfScore.setHoleNumber( score.getHoleNumber() );
        golfScore.setId( score.getId() );

        return golfScore;
    }

    @Override
    public GolfScoreDao GolfScoreDTOtoGolfScoreDAO(GolfScore score) {
        if ( score == null ) {
            return null;
        }

        GolfScoreDao golfScoreDao = new GolfScoreDao();

        golfScoreDao.setScoreId( score.getScoreId() );
        golfScoreDao.setScore( score.getScore() );
        golfScoreDao.setHoleNumber( score.getHoleNumber() );
        golfScoreDao.setId( score.getId() );

        return golfScoreDao;
    }

    @Override
    public List<GolfScore> GolfScoreDAOListToGolfScoreDTOList(List<GolfScoreDao> score) {
        if ( score == null ) {
            return null;
        }

        List<GolfScore> list = new ArrayList<GolfScore>();
        for ( GolfScoreDao golfScoreDao : score ) {
            list.add( GolfScoreDAOtoGolfScoreDTO( golfScoreDao ) );
        }

        return list;
    }

    @Override
    public List<GolfScoreDao> GolfScoreDTOListToGolfScoreDAOList(List<GolfScore> score) {
        if ( score == null ) {
            return null;
        }

        List<GolfScoreDao> list = new ArrayList<GolfScoreDao>();
        for ( GolfScore golfScore : score ) {
            list.add( GolfScoreDTOtoGolfScoreDAO( golfScore ) );
        }

        return list;
    }

    @Override
    public GolfPass GolfPassDAOtoGolfPassDTO(GolfPassDao pass) {
        if ( pass == null ) {
            return null;
        }

        GolfPass golfPass__ = new GolfPass();

        golfPass__.setPassAdded( pass.isPassAdded() );
        golfPass__.setDeviceId( pass.getDeviceId() );
        golfPass__.setPassId( pass.getPassId() );
        golfPass__.setToken( pass.getToken() );

        return golfPass__;
    }

    @Override
    public GolfPassDao GolfPassDTOtoGolfPassDAO(GolfPass pass) {
        if ( pass == null ) {
            return null;
        }

        GolfPassDao golfPassDao__ = new GolfPassDao();

        golfPassDao__.setPassAdded( pass.isPassAdded() );
        golfPassDao__.setRegisteredPass( PassRegistrationsDTOListToPassRegistrationsDAOList( pass.getRegisteredPass() ) );
        golfPassDao__.setDeviceId( pass.getDeviceId() );
        golfPassDao__.setPassId( pass.getPassId() );
        golfPassDao__.setToken( pass.getToken() );

        return golfPassDao__;
    }

    @Override
    public List<GolfPass> GolfPassDAOListToGolfPassDTOList(List<GolfPassDao> pass) {
        if ( pass == null ) {
            return null;
        }

        List<GolfPass> list = new ArrayList<GolfPass>();
        for ( GolfPassDao golfPassDao : pass ) {
            list.add( GolfPassDAOtoGolfPassDTO( golfPassDao ) );
        }

        return list;
    }

    @Override
    public List<GolfPassDao> GolfPassDTOListToGolfPassDAOList(List<GolfPass> pass) {
        if ( pass == null ) {
            return null;
        }

        List<GolfPassDao> list = new ArrayList<GolfPassDao>();
        for ( GolfPass golfPass : pass ) {
            list.add( GolfPassDTOtoGolfPassDAO( golfPass ) );
        }

        return list;
    }

    @Override
    public GolfHoles golfHolesDAOtoGolfHolesDTO(GolfHolesDao holes) {
        if ( holes == null ) {
            return null;
        }

        GolfHoles golfHoles = new GolfHoles();

        golfHoles.setHoleTypeId( holes.getHoleTypeId() );
        golfHoles.setHoles( holes.getHoles() );

        return golfHoles;
    }

    @Override
    public GolfHolesDao golfHolesDTOtoGolfHolesDAO(GolfHoles holes) {
        if ( holes == null ) {
            return null;
        }

        GolfHolesDao golfHolesDao = new GolfHolesDao();

        golfHolesDao.setGolf( golfDTOListToGolfDAOList( holes.getGolf() ) );
        golfHolesDao.setHoleTypeId( holes.getHoleTypeId() );
        golfHolesDao.setHoles( holes.getHoles() );

        return golfHolesDao;
    }

    @Override
    public List<GolfHoles> golfHolesDAOListToGolfHolesDTOList(List<GolfHolesDao> holes) {
        if ( holes == null ) {
            return null;
        }

        List<GolfHoles> list = new ArrayList<GolfHoles>();
        for ( GolfHolesDao golfHolesDao : holes ) {
            list.add( golfHolesDAOtoGolfHolesDTO( golfHolesDao ) );
        }

        return list;
    }

    @Override
    public List<GolfHolesDao> golfHolesDTOListToGolfHolesDAOList(List<GolfHoles> holes) {
        if ( holes == null ) {
            return null;
        }

        List<GolfHolesDao> list = new ArrayList<GolfHolesDao>();
        for ( GolfHoles golfHoles : holes ) {
            list.add( golfHolesDTOtoGolfHolesDAO( golfHoles ) );
        }

        return list;
    }

    @Override
    public GolfCourse golfCourseDAOtoGolfCourseDTO(GolfCourseDao courseDao) {
        if ( courseDao == null ) {
            return null;
        }

        GolfCourse golfCourse = new GolfCourse();

        golfCourse.setGolfCourseId( courseDao.getGolfCourseId() );
        golfCourse.setCourseName( courseDao.getCourseName() );

        return golfCourse;
    }

    @Override
    public GolfCourseDao golfCourseDTOtoGolfCourseDAO(GolfCourse course) {
        if ( course == null ) {
            return null;
        }

        GolfCourseDao golfCourseDao = new GolfCourseDao();

        golfCourseDao.setGolfCourseId( course.getGolfCourseId() );
        golfCourseDao.setCourseName( course.getCourseName() );
        golfCourseDao.setGolf( golfDTOListToGolfDAOList( course.getGolf() ) );

        return golfCourseDao;
    }

    @Override
    public List<GolfCourse> golfCourseDAOListToGolfCourseDTOList(List<GolfCourseDao> courseDao) {
        if ( courseDao == null ) {
            return null;
        }

        List<GolfCourse> list = new ArrayList<GolfCourse>();
        for ( GolfCourseDao golfCourseDao : courseDao ) {
            list.add( golfCourseDAOtoGolfCourseDTO( golfCourseDao ) );
        }

        return list;
    }

    @Override
    public List<GolfCourseDao> golfCourseDTOListToGolfCourseDAOList(List<GolfCourse> courseDao) {
        if ( courseDao == null ) {
            return null;
        }

        List<GolfCourseDao> list = new ArrayList<GolfCourseDao>();
        for ( GolfCourse golfCourse : courseDao ) {
            list.add( golfCourseDTOtoGolfCourseDAO( golfCourse ) );
        }

        return list;
    }

    @Override
    public Golf golfDAOtoGolfDTO(GolfDao golf) {
        if ( golf == null ) {
            return null;
        }

        Golf golf_ = new Golf();

        golf_.setUsersId( GolfUserDAOtoGolfUserDTO( golf.getUsersId() ) );
        golf_.setHoleTypesId( golfHolesDAOtoGolfHolesDTO( golf.getHoleTypesId() ) );
        golf_.setScoresId( GolfScoreDAOListToGolfScoreDTOList( golf.getScoresId() ) );
        golf_.setTeeTypesId( GolfTeeDAOtoGolfTeeDTO( golf.getTeeTypesId() ) );
        golf_.setGolfCoursesId( golfCourseDAOtoGolfCourseDTO( golf.getGolfCoursesId() ) );
        golf_.setId( golf.getId() );

        return golf_;
    }

    @Override
    public GolfDao golfDTOtoGolfDAO(Golf golf) {
        if ( golf == null ) {
            return null;
        }

        GolfDao golfDao = new GolfDao();

        golfDao.setUsersId( GolfUserDTOtoGolfUserDAO( golf.getUsersId() ) );
        golfDao.setHoleTypesId( golfHolesDTOtoGolfHolesDAO( golf.getHoleTypesId() ) );
        golfDao.setScoresId( GolfScoreDTOListToGolfScoreDAOList( golf.getScoresId() ) );
        golfDao.setTeeTypesId( GolfTeeDTOtoGolfTeeDAO( golf.getTeeTypesId() ) );
        golfDao.setGolfCoursesId( golfCourseDTOtoGolfCourseDAO( golf.getGolfCoursesId() ) );
        golfDao.setId( golf.getId() );

        return golfDao;
    }

    @Override
    public List<Golf> golfDAOListToGolfDTOList(List<GolfDao> golf) {
        if ( golf == null ) {
            return null;
        }

        List<Golf> list = new ArrayList<Golf>();
        for ( GolfDao golfDao : golf ) {
            list.add( golfDAOtoGolfDTO( golfDao ) );
        }

        return list;
    }

    @Override
    public List<GolfDao> golfDTOListToGolfDAOList(List<Golf> golf) {
        if ( golf == null ) {
            return null;
        }

        List<GolfDao> list___ = new ArrayList<GolfDao>();
        for ( Golf golf_ : golf ) {
            list___.add( golfDTOtoGolfDAO( golf_ ) );
        }

        return list___;
    }
}
