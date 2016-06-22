package com.mds.passbook.controller;

import java.security.Principal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mds.passbook.bean.golf.Golf;
import com.mds.passbook.bean.golf.GolfCourse;
import com.mds.passbook.bean.golf.GolfHoles;
import com.mds.passbook.bean.golf.GolfScore;
import com.mds.passbook.bean.golf.GolfTee;
import com.mds.passbook.bean.golf.GolfUser;
import com.mds.passbook.bean.pass.UserPass;
import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.golf.dao.GolfUserDao;
import com.mds.passbook.data.repository.security.dao.UserProfile;
import com.mds.passbook.service.GolfService;


@RestController
public class GolfController {
	
	@Autowired
	GolfService service;
	
	@Autowired
	UserProfileRepository userProfileService;
	
	@Autowired
	Environment env;
	
	@RequestMapping(value="/golfDetails", method=RequestMethod.GET)
	public @ResponseBody HashMap<String, List<?>> getGolfViewDetails(Principal principal){
		 
		List<Object> response = new ArrayList<Object>();
		
		HashMap<String, List<?>> responseMap = new HashMap<>();

		UserProfile userProfile = userProfileService.findByEmail(principal.getName());
		
		GolfUserDao golfUser = userProfile.getUserId();
		
		List<Golf> golf = service.getAllGolf(golfUser);
		List<GolfHoles> holes = service.findAllGolfHoles();
		List<GolfTee> tees = service.findAllGolfTee();
		List<GolfCourse> courses = service.findAllGolfCourses();
		
		List<AbstractMap.SimpleEntry<String,String>> genders = new ArrayList<AbstractMap.SimpleEntry<String,String>>();
		
		genders.add(new AbstractMap.SimpleEntry<String,String>("value","Male"));
		genders.add(new AbstractMap.SimpleEntry<String,String>("value","Female"));
		
		responseMap.put("golf", golf);
		responseMap.put("hole_type_list", holes);
		responseMap.put("golf_course_list", courses);
		responseMap.put("genders", genders);
		responseMap.put("tee_type_list", tees);
		
		return responseMap;
		
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public String user(@RequestBody(required=true) GolfUser user, @RequestParam(name="target", required=true) String target){
		String response;
		
		switch(target.toLowerCase()){
			case "add":
				service.addUser(user);
				response = "ADD";
				break;
			case "remove":
				service.deleteUser(user);
				response = "DELETE";
				break;
			case "update":
				service.updateUser(user);
				response = "UPDATE";
				break;
			default:
				response = "-1";
				break;
		}
		
		return response;
		
	}
	
	@RequestMapping(value="/golfCourse", method=RequestMethod.POST)
	public String golfCourse(@RequestBody GolfCourse course, @RequestParam(name="target") String target){
		String response;
		
		switch(target.toLowerCase()){
			case "add":
				service.addGolfCourse(course);
				response = "ADD";
				break;
			case "remove":
				service.deleteGolfCourse(course);
				response = "DELETE";
				break;
			case "update":
				service.updateGolfCourse(course);
				response = "UPDATE";
				break;
			default:
				response = "-1";
				break;
		}
		
		return response;
	}
	
	@RequestMapping(value="/holes", method=RequestMethod.POST)
	public String holes(@RequestBody(required=true) GolfHoles holes, @RequestParam(name="target") String target){
		String response;
		
		
		switch(target.toLowerCase()){
			case "add":
				service.addGolfHole(holes);
				response = "ADD";
				break;
			case "remove":
				service.deleteGoldHole(holes);
				response = "DELETE";
				break;
			case "update":
				service.updateGolfHole(holes);
				response = "UPDATE";
				break;
			default:
				response = "-1";
				break;
		}
		
		return response;
	}
	
	@RequestMapping(value="/tee", method=RequestMethod.POST)
	public String golf(@RequestBody(required=true) List<GolfTee> teeList, @RequestParam(name="target") String target){
		
		String response = "";
		
		for(GolfTee tee: teeList){

			switch(target.toLowerCase()){
				case "add":
					service.addGolfTee(tee);
					response = "ADD";
					break;
				case "remove":
					service.deleteGolfTee(tee);
					response = "DELETE";
					break;
				case "update":
					service.updateGolfTee(tee);
					response = "UPDATE";
					break;
				default:
					response = "-1";
					break;
			}
			
		}
		return response;
		
	}
	

	@RequestMapping(value="/score", method=RequestMethod.POST)
	public String score(@RequestBody(required=true) GolfScore score, @RequestParam(name="target") String target){
		String response;
		
		switch(target.toLowerCase()){
		case "add":
			service.addGolfScore(score);
			response = "ADD";
			break;
		case "remove":
			service.deleteGolfScore(score);
			response = "DELETE";
			break;
		case "update":
			service.updateGolfScore(score);
			response = "UPDATE";
			break;
		default:
			response = "-1";
			break;
		}
		
		return response;
	}
	
	@RequestMapping(value="/pass", method=RequestMethod.POST)
	public String pass(@RequestBody(required=true) UserPass pass, @RequestParam(name="target") String target){
		String response;
		
		switch(target.toLowerCase()){
		case "add":
			service.addGolfPass(pass);
			response = "ADD";
			break;
		case "remove":
			service.deleteGolfPass(pass);
			response = "DELETE";
			break;
		case "update":
			service.updateGolfPass(pass);
			response = "UPDATE";
			break;
		default:
			response = "-1";
			break;
	}
		
		return response;
	}
}
