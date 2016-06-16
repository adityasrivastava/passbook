package com.mds.passbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mds.passbook.bean.GolfCourse;
import com.mds.passbook.data.repository.GolfCourseRepository;
import com.mds.passbook.data.repository.GolfHolesRepository;
import com.mds.passbook.data.repository.GolfPassRepository;
import com.mds.passbook.data.repository.GolfRepository;
import com.mds.passbook.data.repository.GolfScoreRepository;
import com.mds.passbook.data.repository.GolfTeeRepository;
import com.mds.passbook.data.repository.GolfUserRepository;
import com.mds.passbook.data.repository.dao.GolfCourseDao;
import com.mds.passbook.data.repository.dao.GolfDao;
import com.mds.passbook.data.repository.dao.GolfHolesDao;
import com.mds.passbook.data.repository.dao.GolfPassDao;
import com.mds.passbook.data.repository.dao.GolfScoreDao;
import com.mds.passbook.data.repository.dao.GolfTeeDao;
import com.mds.passbook.data.repository.dao.GolfTeeDetailsDao;
import com.mds.passbook.data.repository.dao.GolfUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class RepositoryTest {
	
	@Autowired
	GolfRepository golfRepo;
	
	@Autowired
	GolfCourseRepository golfCourseRepo;
	
	@Autowired
	GolfHolesRepository golfHolesRepo;
	
	@Autowired
	GolfPassRepository golfPassRepo;
	
	@Autowired
	GolfUserRepository golfUserRepo;
	
	@Autowired
	GolfScoreRepository golfScoreRepo;
	
	@Autowired
	GolfTeeRepository golfTeeRepo;
	
	@org.junit.Test
	
	public void mapperTest(){
	
		
	}
	
	
	@org.junit.Test
	public void getAllTest(){
		Iterable<GolfDao> it = golfRepo.findAll();
		for(GolfDao g:it){
			System.out.println("Golf "+g.getId()+">>>>>>>>>>>>>>>>>>>>");
			System.out.println(g.toString());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
		}
	}
	
	@org.junit.Test
	public void findOneTest(){
		GolfDao g = golfRepo.findOne(new Integer(1));
		System.out.println("Golf "+g.getId()+">>>>>>>>>>>>>>>>>>>>FIND ONE BY ID>>>>>>>>>>>>>>>>>>>>");
		System.out.println(g.toString());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	@org.junit.Test
	public void insertGolfCourseTest(){
		// Golf Course Added
		GolfCourseDao gCourse = new GolfCourseDao();
		gCourse.setCourseName("GolfCourse 1");
		
		golfCourseRepo.save(gCourse);
		
	}
	
	@org.junit.Test
	public void insertGolfHolesTest(){
		// Golf holes Added
		GolfHolesDao gHoles = new GolfHolesDao();
		gHoles.setHoles(9);
		golfHolesRepo.save(gHoles);
		
	}
	
	@org.junit.Test
	public void insertGolfPassTest(){
		// Golf Pass Added
		GolfPassDao gPass = new GolfPassDao();
		gPass.setPassAdded(true);
		gPass.setToken("XYZ");
		golfPassRepo.save(gPass);

	}
	
	@org.junit.Test
	public void insertGolfUserTest(){
	
		GolfUserDao gUser = new GolfUserDao();
		gUser.setAge(1);
		gUser.setGender("MALE");
		gUser.setHandicap(20);
		gUser.setName("Aditya");
		
		golfUserRepo.save(gUser);

	}
	
	@org.junit.Test
	public void insertGolfScoreTest(){
		GolfScoreDao gScore = new GolfScoreDao();
		gScore.setScore(10);
		
		golfScoreRepo.save(gScore);
	}
	
	@org.junit.Test
	public void insertGolfTeeTest(){
		
		GolfTeeDao gTee = new GolfTeeDao();
		gTee.setColor("Red");
		
		GolfTeeDetailsDao teeDetails = new GolfTeeDetailsDao();
		teeDetails.setPar(1);
		teeDetails.setStroke(1);
		teeDetails.setYards(1);
		teeDetails.setGolfTee(gTee);
		
		GolfTeeDetailsDao teeDetails2 = new GolfTeeDetailsDao();
		teeDetails2.setPar(1);
		teeDetails2.setStroke(1);
		teeDetails2.setYards(1);
		teeDetails2.setGolfTee(gTee);
		
		gTee.getTeeDetails().add(teeDetails);
		gTee.getTeeDetails().add(teeDetails2);
	
		golfTeeRepo.save(gTee);
	}
	
	@org.junit.Test
	public void mainTest(){
		// Create Golf Course
		GolfCourseDao gCourse = new GolfCourseDao();
		gCourse.setCourseName("GolfCourse 1");
		golfCourseRepo.save(gCourse);
		
		GolfCourseDao gCourse1 = new GolfCourseDao();
		gCourse1.setCourseName("GolfCourse 2");
		golfCourseRepo.save(gCourse1);
		
		
		// Create golf holes
		
		GolfHolesDao gHoles = new GolfHolesDao();
		gHoles.setHoles(9);
		
		GolfHolesDao gHoles1 = new GolfHolesDao();
		gHoles1.setHoles(18);
		
		golfHolesRepo.save(gHoles);
		golfHolesRepo.save(gHoles1);
		
		// Create pass 
		
		GolfPassDao pass = new GolfPassDao();
		pass.setPassAdded(true);
		pass.setToken("ABC=XYZ");
		
		GolfPassDao pass2 = new GolfPassDao();
		pass2.setPassAdded(true);
		pass2.setToken("ABC=XYZ1");
	
		// Create golf User
		GolfUserDao gUser = new GolfUserDao();
		gUser.setAge(1);
		gUser.setGender("MALE");
		gUser.setHandicap(20);
		gUser.setName("Aditya");
		gUser.setPass(pass);
		
		GolfUserDao gUser1 = new GolfUserDao();
		gUser1.setAge(1);
		gUser1.setGender("MALE");
		gUser1.setHandicap(10);
		gUser1.setName("Aditya2");
		gUser1.setPass(pass2);
		
		golfUserRepo.save(gUser1);
		golfUserRepo.save(gUser);
		
		// Create golf tee
		
		GolfTeeDao gTee = new GolfTeeDao();
		gTee.setColor("Red");
		
		GolfTeeDetailsDao teeDetails = new GolfTeeDetailsDao();
		teeDetails.setPar(1);
		teeDetails.setStroke(1);
		teeDetails.setYards(1);
		teeDetails.setGolfTee(gTee);
		teeDetails.setHoleNumber(1);
		
		GolfTeeDetailsDao teeDetails2 = new GolfTeeDetailsDao();
		teeDetails2.setPar(1);
		teeDetails2.setStroke(1);
		teeDetails2.setYards(1);
		teeDetails2.setGolfTee(gTee);
		teeDetails2.setHoleNumber(2);
		
		GolfTeeDetailsDao teeDetails3 = new GolfTeeDetailsDao();
		teeDetails3.setPar(1);
		teeDetails3.setStroke(1);
		teeDetails3.setYards(1);
		teeDetails3.setGolfTee(gTee);
		teeDetails3.setHoleNumber(3);
		
		gTee.getTeeDetails().add(teeDetails);
		gTee.getTeeDetails().add(teeDetails2);
		gTee.getTeeDetails().add(teeDetails3);
	
		golfTeeRepo.save(gTee);
	

		
		// Default Score
		GolfDao addGolf = new GolfDao();
		
		List<GolfScoreDao> score = new ArrayList<>();
		GolfScoreDao gScore = new GolfScoreDao();

		gScore.setScore(10);
		gScore.setGolf(addGolf);
//		gScore.setGolfTeeDetailsId(teeDetails);
		
		GolfScoreDao gScore1 = new GolfScoreDao();
	
		gScore1.setScore(10);
		gScore1.setGolf(addGolf);
//		gScore1.setGolfTeeDetailsId(teeDetails2);
		
		GolfScoreDao gScore2 = new GolfScoreDao();

		gScore2.setScore(10);
		gScore2.setGolf(addGolf);
//		gScore2.setGolfTeeDetailsId(teeDetails3);
		
		score.add(gScore);
		score.add(gScore1);
		score.add(gScore2);
		
		// Save golf

		addGolf.setHoleTypesId(gHoles);
		addGolf.setGolfCoursesId(gCourse1);
		addGolf.setUsersId(gUser);
		addGolf.setTeeTypesId(gTee);
		addGolf.setScoresId(score);
		golfRepo.save(addGolf);
		
		// Read Golf
		GolfDao newGolf = new GolfDao();
		GolfCourseDao g = new GolfCourseDao();
		g.setGolfCourseId(2);
		GolfDao find = golfRepo.findByGolfCoursesId(g);
		
		
		// Read Golf score 
		GolfDao gc = new GolfDao();
		gc.setId(1);
		List<GolfScoreDao> readScore = golfScoreRepo.findByGolf(gc);
		
		for(GolfScoreDao gm: readScore){
			System.out.println("SCore: "+gm);
		}
	
		
		System.out.println(find.getId()+" - "+find);
//		
//		Golf golf1 = new Golf();
//		golf1.setId(1);
//		golfRepo.delete(1);
	}
	
}
