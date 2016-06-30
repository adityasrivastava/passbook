package com.mds.passbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mds.passbook.bean.golf.Golf;
import com.mds.passbook.bean.golf.GolfCourse;
import com.mds.passbook.bean.golf.GolfHoles;
import com.mds.passbook.bean.golf.GolfScore;
import com.mds.passbook.bean.golf.GolfTee;
import com.mds.passbook.bean.golf.GolfTeeDetails;
import com.mds.passbook.bean.golf.GolfUser;
import com.mds.passbook.bean.pass.PassRegistrations;
import com.mds.passbook.bean.pass.UserPass;
import com.mds.passbook.data.repository.GolfCourseRepository;
import com.mds.passbook.data.repository.GolfHolesRepository;
import com.mds.passbook.data.repository.GolfPassRegistrationsRepository;
import com.mds.passbook.data.repository.GolfPassRepository;
import com.mds.passbook.data.repository.GolfRepository;
import com.mds.passbook.data.repository.GolfScoreRepository;
import com.mds.passbook.data.repository.GolfTeeDetailsRepository;
import com.mds.passbook.data.repository.GolfTeeRepository;
import com.mds.passbook.data.repository.GolfUserRepository;
import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.golf.dao.GolfCourseDao;
import com.mds.passbook.data.repository.golf.dao.GolfDao;
import com.mds.passbook.data.repository.golf.dao.GolfHolesDao;
import com.mds.passbook.data.repository.golf.dao.GolfScoreDao;
import com.mds.passbook.data.repository.golf.dao.GolfTeeDao;
import com.mds.passbook.data.repository.golf.dao.GolfTeeDetailsDao;
import com.mds.passbook.data.repository.golf.dao.GolfUserDao;
import com.mds.passbook.data.repository.security.dao.UserProfile;
import com.mds.passbook.data.repository.user.dao.PassRegistrationsDao;
import com.mds.passbook.data.repository.user.dao.UserPassDao;
import com.mds.passbook.mapper.GolfMapper;

@Component
@Transactional
public class GolfServiceBean implements GolfService {

	@Autowired
	Environment env;

	@Autowired
	GolfRepository golfRepo;

	@Autowired
	GolfCourseRepository golfCourseRepo;

	@Autowired
	GolfHolesRepository golfHolesRepo;

	@Autowired
	GolfPassRepository golfPassRepo;

	@Autowired
	GolfPassRegistrationsRepository golfPassRegisterRepo;

	@Autowired
	GolfUserRepository golfUserRepo;

	@Autowired
	GolfScoreRepository golfScoreRepo;

	@Autowired
	GolfTeeRepository golfTeeRepo;

	@Autowired
	GolfTeeDetailsRepository golfTeeDetailsRepo;
	
	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	public GolfUser addUser(GolfUser user) {

		GolfUserDao userDao;

		userDao = new GolfUserDao();

		userDao = GolfMapper.INSTANCE.GolfUserDTOtoGolfUserDAO(user);

		userDao = golfUserRepo.save(userDao);
		user.setUserId(userDao.getUserId());

		return user;
	}

	@Override
	public void deleteUser(GolfUser user) {
		GolfUserDao userDao = new GolfUserDao();
		golfUserRepo.delete(userDao);
	}

	@Override
	public GolfUser updateUser(GolfUser user) {
		GolfUserDao userDao = new GolfUserDao();

		userDao = GolfMapper.INSTANCE.GolfUserDTOtoGolfUserDAO(user);

		userDao = golfUserRepo.save(userDao);

		user = GolfMapper.INSTANCE.GolfUserDAOtoGolfUserDTO(userDao);

		return user;
	}

	@Override
	public Iterable<GolfUser> getAllUser() {

		GolfUser user = new GolfUser();

		return null;
	}

	@Override
	public GolfUser getUserById(Long id) {
		GolfUserDao userDao = new GolfUserDao();
		return null;
	}

	@Override
	public Iterable<GolfDao> getAllGolf() {
		return golfRepo.findAll();
	}

	@Override
	public Golf getGolfById(Long id) {

		GolfDao golfDao;
		Golf golf;

		golfDao = golfRepo.findOne(id);

		golf = GolfMapper.INSTANCE.golfDAOtoGolfDTO(golfDao);

		return golf;
	}

	@Override
	public void addGame(GolfDao golf) {
		golfRepo.save(golf);
	}

	@Override
	public void deleteGame(GolfDao golf) {
		golfRepo.delete(golf);
	}

	@Override
	public void updateGame(GolfDao golf) {
		golfRepo.save(golf);
	}

	@Override
	public GolfScoreDao getScoreById(Long id) {
		return golfScoreRepo.findOne(id);
	}

	@Override
	public GolfScoreDao updateScore(GolfScore score) {

		GolfScoreDao scoreDao;
		GolfDao golfDao;

		golfDao = golfRepo.findOne(score.getGolf().getId());

		scoreDao = golfScoreRepo.findByGolfAndHoleNumber(golfDao, score.getHoleNumber());

		scoreDao.setScore(score.getScore());

		scoreDao = golfScoreRepo.save(scoreDao);

		score = GolfMapper.INSTANCE.GolfScoreDAOtoGolfScoreDTO(scoreDao);

		return scoreDao;
	}

	@Override
	public Iterable<GolfTeeDao> getAllTee() {
		return golfTeeRepo.findAll();
	}

	@Override
	public GolfTeeDao getTeeById(Long id) {
		return golfTeeRepo.findOne(id);
	}

	@Override
	public void addTee(GolfTeeDao tee) {
		golfTeeRepo.save(tee);
	}

	@Override
	public void deleteTee(GolfTeeDao tee) {
		golfTeeRepo.delete(tee);
	}

	@Override
	public void updateTee(GolfTeeDao tee) {
		golfTeeRepo.save(tee);
	}

	@Override
	public void createGame(GolfUser user, String golfCourseId, String holeTypeId, String teeTypeId) {

		UserPass golfPass;

		GolfDao golfDao;
		GolfUserDao userDao;
		UserPassDao passDao;

		golfPass = user.getPass();

		passDao = new UserPassDao(golfPass.getToken(), golfPass.isPassAdded());
		userDao = new GolfUserDao(user.getName(), user.getAge(), user.getGender(), user.getHandicap(), passDao);

		golfDao = new GolfDao(userDao, new GolfCourseDao(Long.valueOf(golfCourseId)),
				new GolfHolesDao(Long.valueOf(holeTypeId)), new GolfTeeDao(Long.valueOf(teeTypeId)));

		golfRepo.save(golfDao);
	}

	@Override
	public void addGolfCourse(GolfCourse course) {
		GolfCourseDao courseDao;

		courseDao = GolfMapper.INSTANCE.golfCourseDTOtoGolfCourseDAO(course);
		
//		courseDao = new GolfCourseDao();
//		courseDao.setCourseName(course.getCourseName());
		golfCourseRepo.save(courseDao);

	}

	@Override
	public void deleteGolfCourse(GolfCourse course) {
		golfCourseRepo.delete(course.getGolfCourseId());
	}

	@Override
	public void updateGolfCourse(GolfCourse course) {

		GolfCourseDao courseDao;

		courseDao = new GolfCourseDao();
		courseDao.setGolfCourseId(course.getGolfCourseId());
		courseDao.setCourseName(course.getCourseName());

		golfCourseRepo.save(courseDao);
	}

	@Override
	public void addGolfHole(GolfHoles hole) {
		GolfHolesDao holesDao;

		holesDao = new GolfHolesDao();
		holesDao.setHoles(hole.getHoles());
		golfHolesRepo.save(holesDao);
	}

	@Override
	public void deleteGoldHole(GolfHoles hole) {
		golfHolesRepo.delete(hole.getHoleTypeId());
	}

	@Override
	public void updateGolfHole(GolfHoles hole) {
		GolfHolesDao holeDao;

		holeDao = new GolfHolesDao();
		holeDao.setHoleTypeId(hole.getHoleTypeId());
		holeDao.setHoles(hole.getHoles());

		golfHolesRepo.save(holeDao);
	}

	@Override
	public void addGolfTee(GolfTee tee) {
		GolfTeeDao teeDao;

		List<GolfTeeDetailsDao> detailsDaoList;

		detailsDaoList = new ArrayList<GolfTeeDetailsDao>();
		teeDao = new GolfTeeDao();

		for (GolfTeeDetails teeDetails : tee.getTeeDetails()) {
			GolfTeeDetailsDao detailsDao = new GolfTeeDetailsDao();
			detailsDao.setGolfTee(teeDao);
			detailsDao.setHoleNumber(teeDetails.getHoleNumber());
			detailsDao.setPar(teeDetails.getPar());
			detailsDao.setStroke(teeDetails.getStroke());
			detailsDao.setYards(teeDetails.getYards());
			detailsDaoList.add(detailsDao);
		}

		teeDao.setTeeDetails(detailsDaoList);
		teeDao.setColor(tee.getColor());

		golfTeeRepo.save(teeDao);

	}

	@Override
	public void deleteGolfTee(GolfTee tee) {
		golfTeeRepo.delete(tee.getTeeId());
	}

	@Override
	public void updateGolfTee(GolfTee tee) {
		GolfTeeDao teeDao;

		List<GolfTeeDetailsDao> detailsDaoList;

		detailsDaoList = new ArrayList<GolfTeeDetailsDao>();
		teeDao = new GolfTeeDao();

		for (GolfTeeDetails teeDetails : tee.getTeeDetails()) {
			GolfTeeDetailsDao detailsDao = new GolfTeeDetailsDao();
			detailsDao.setTeeTypeId(teeDetails.getTeeTypeId());

			detailsDao.setHoleNumber(teeDetails.getHoleNumber());
			detailsDao.setPar(teeDetails.getPar());
			detailsDao.setStroke(teeDetails.getStroke());
			detailsDao.setYards(teeDetails.getYards());
			detailsDaoList.add(detailsDao);
		}

		// teeDao.setTeeDetails(detailsDaoList);
		teeDao.setColor(tee.getColor());

		golfTeeRepo.save(teeDao);

	}

	@Override
	public void addGolfPass(UserPass pass) {
		UserPassDao passDao;

		passDao = new UserPassDao();

		passDao.setPassAdded(pass.isPassAdded());
		passDao.setToken(pass.getToken());

		golfPassRepo.save(passDao);
	}

	@Override
	public void deleteGolfPass(UserPass pass) {
		golfPassRepo.delete(pass.getPassId());
	}

	@Override
	public UserPass updateGolfPass(UserPass pass) {
		UserPassDao passDao;
		passDao = GolfMapper.INSTANCE.GolfPassDTOtoGolfPassDAO(pass);

		passDao = golfPassRepo.save(passDao);

		pass = GolfMapper.INSTANCE.GolfPassDAOtoGolfPassDTO(passDao);

		return pass;
	}

	@Override
	public List<GolfScoreDao> addGolf(Golf golf) {

		GolfDao golfDao;
		GolfUserDao userDao;
		GolfTeeDao teeDao;
		GolfTeeDetailsDao teeDetailsDao;
		GolfCourseDao courseDao;
		GolfHolesDao holesDao;
		UserPassDao passDao;
		List<GolfScoreDao> scoreDaoList;

		scoreDaoList = new ArrayList<GolfScoreDao>();
		courseDao = golfCourseRepo.findOne(golf.getGolfCoursesId().getGolfCourseId());
		userDao = golfUserRepo.findOne(golf.getUsersId().getUserId());
		teeDao = golfTeeRepo.findOne(golf.getTeeTypesId().getTeeId());
		holesDao = golfHolesRepo.findOne(golf.getHoleTypesId().getHoleTypeId());

		golfDao = new GolfDao();

		golfDao.setGolfCoursesId(courseDao);
		golfDao.setHoleTypesId(holesDao);
		golfDao.setTeeTypesId(teeDao);
		golfDao.setUsersId(userDao);

		GolfDao resultDao = golfRepo.save(golfDao);

		// Mapping Tee Details with score
		for (int holeNumber = 1; holeNumber <= holesDao.getHoles(); holeNumber++) {
			teeDetailsDao = golfTeeDetailsRepo.findByGolfTeeAndHoleNumber(teeDao, holeNumber);
			scoreDaoList.add(new GolfScoreDao(0, holeNumber, resultDao, teeDetailsDao));
		}

		PassRegistrationsDao passRegisterDao = new PassRegistrationsDao();
		passRegisterDao.setPass(userDao.getPass());
		passRegisterDao.setPassTypeId(env.getProperty("PASS.PASS_TYPE_IDENTIFIER"));
		passRegisterDao.setGolf(golfDao);
		passRegisterDao.setSerialNumber("" + golfDao.getId());
		golfPassRegisterRepo.save(passRegisterDao);

		golfScoreRepo.save(scoreDaoList);

		List<GolfScoreDao> scoreDao = golfScoreRepo.findByGolf(resultDao);

		return scoreDao;

	}

	@Override
	public void deleteGolf(Long id) {
		golfRepo.delete(id);
	}

	@Override
	public void addGolfScore(GolfScore score) {

		GolfScoreDao scoreDao;

		scoreDao = new GolfScoreDao();

		scoreDao.setGolf(new GolfDao(score.getScoreId()));

		golfScoreRepo.save(scoreDao);
	}

	@Override
	public List<GolfScoreDao> getScoresById(Long id) {
		GolfScoreDao scoreDao;
		GolfDao golfDao;

		golfDao = golfRepo.findOne(id);

		return golfScoreRepo.findByGolf(golfDao);
	}

	@Override
	public void updatePassRegistrations(PassRegistrations passRegister) {

	}

	@Override
	public void deletePassRegistrations(PassRegistrations passRegister) {
		PassRegistrationsDao registrationsDao;

		registrationsDao = golfPassRegisterRepo.findBySerialNumberAndPassTypeId(passRegister.getSerialNumber(),
				passRegister.getPassTypeId());

		if (registrationsDao != null) {
			golfPassRegisterRepo.delete(registrationsDao);
		}

	}

	@Override
	public List<PassRegistrations> findUpdatedPass(String passTypeId, String deviceId) {

		UserPassDao passDao;
		List<PassRegistrationsDao> registrationsDao;
		List<PassRegistrations> registrations;

		passDao = golfPassRepo.findByDeviceId(deviceId);
		registrationsDao = golfPassRegisterRepo.findByPassTypeId(passTypeId);
		registrations = GolfMapper.INSTANCE.PassRegistrationsDAOListToPassRegistrationsDTOList(registrationsDao);
		return registrations;

	}

	@Override
	public List<GolfCourse> findAllGolfCourses() {

		Iterable<GolfCourseDao> courseDaoItr = golfCourseRepo.findAll();
		List<GolfCourseDao> courseDaoList = new ArrayList<GolfCourseDao>();
		List<GolfCourse> courseList = new ArrayList<GolfCourse>();

		courseDaoItr.forEach(courseDaoList::add);

		courseList = GolfMapper.INSTANCE.golfCourseDAOListToGolfCourseDTOList(courseDaoList);

		return courseList;
	}

	@Override
	public List<GolfHoles> findAllGolfHoles() {
		Iterable<GolfHolesDao> holesDaoItr = golfHolesRepo.findAll();
		List<GolfHolesDao> holesDaoList = new ArrayList<GolfHolesDao>();
		List<GolfHoles> holesList = new ArrayList<GolfHoles>();

		holesDaoItr.forEach(holesDaoList::add);

		holesList = GolfMapper.INSTANCE.golfHolesDAOListToGolfHolesDTOList(holesDaoList);

		return holesList;
	}

	@Override
	public List<GolfTee> findAllGolfTee() {
		Iterable<GolfTeeDao> teeDaoItr = golfTeeRepo.findAll();
		List<GolfTeeDao> teeDaoList = new ArrayList<GolfTeeDao>();

		List<GolfTee> teeList = new ArrayList<GolfTee>();

		teeDaoItr.forEach(teeDaoList::add);

		teeList = GolfMapper.INSTANCE.GolfTeeDAOListToGolfTeeDTOList(teeDaoList);

		return teeList;
	}

	@Override
	public List<Golf> findAllGolf() {
		Iterable<GolfDao> golfDaoItr = golfRepo.findAll();
		List<GolfDao> golfDaoList = new ArrayList<GolfDao>();

		List<Golf> golfList = new ArrayList<Golf>();

		golfDaoItr.forEach(golfDaoList::add);

		golfList = GolfMapper.INSTANCE.golfDAOListToGolfDTOList(golfDaoList);

		return golfList;
	}

	@Override
	public PassRegistrations getPassRegisteredBySerialNumberAndPassTypeId(String serialNumber, String passTypeId) {

		PassRegistrationsDao passRegiDao;
		PassRegistrations passRegi;
		passRegiDao = golfPassRegisterRepo.findBySerialNumberAndPassTypeId(serialNumber, passTypeId);
		
		
		// Patch for infinite loop
		UserPassDao pass = passRegiDao.getPass();
		pass.setRegisteredPass(null);
		
		passRegi = GolfMapper.INSTANCE.PassRegistrationsDAOtoPassRegistrationsDTO(passRegiDao);
		UserPass userPass = GolfMapper.INSTANCE.GolfPassDAOtoGolfPassDTO(pass);
		
		passRegi.setPass(userPass);
		
		return passRegi;
	}

	@Override
	public UserPass findGolfPassById(Long id) {
		UserPass golfPass;
		UserPassDao golfPassDao;

		golfPassDao = golfPassRepo.findOne(id);

		golfPass = GolfMapper.INSTANCE.GolfPassDAOtoGolfPassDTO(golfPassDao);

		return golfPass;
	}

	@Override
	public List<Golf> getAllGolf(GolfUser user) {
		
		GolfUserDao userDao = GolfMapper.INSTANCE.GolfUserDTOtoGolfUserDAO(user);

		List<GolfDao> golfList = golfRepo.findByUsersId(userDao);

		List<Golf> golf = GolfMapper.INSTANCE.golfDAOListToGolfDTOList(golfList);

		return golf;
	}

	@Override
	public void deleteGolfScore(GolfScore score) {
		
	}

	@Override
	public void updateGolfScore(GolfScore score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GolfUser userProfileByEmailId(UserProfile profile) {
		
		GolfUserDao userDoa = profile.getUserId();
		
		GolfUser user = GolfMapper.INSTANCE.GolfUserDAOtoGolfUserDTO(userDoa);
		
		return user;
	}

}
