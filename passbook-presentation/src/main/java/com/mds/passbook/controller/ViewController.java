package com.mds.passbook.controller;

import java.security.Principal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mds.passbook.bean.golf.GolfUser;
import com.mds.passbook.bean.http.RegisterForm;
import com.mds.passbook.data.repository.GolfUserRepository;
import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.golf.dao.GolfUserDao;
import com.mds.passbook.data.repository.security.dao.Role;
import com.mds.passbook.data.repository.security.dao.UserProfile;
import com.mds.passbook.service.PassbookService;

@Controller
public class ViewController {

	@Autowired
	UserProfileRepository userProfileService;
	
	@Autowired
	GolfUserRepository golfUserService;

	@Autowired
	ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	UsersConnectionRepository connectionRepository;
	
	@Autowired
	PassbookService passbookService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(@RequestParam(name="id", required=false) String userId,Model model, Principal principal){
		
		if(userId != null){
			GolfUserDao user = new GolfUserDao();
			
			
			Integer golfUserId = Integer.parseInt(userId);
			
			user = golfUserService.findOne(Long.valueOf(userId));
			
			model.addAttribute("user", user);

		}
		return "index";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam Map<String, String> requestParams, Principal principal, Model model) {
		
		UserProfile profile = userProfileService.findByEmail(principal.getName());

		if(profile != null){
			GolfUserDao user = new GolfUserDao();
			user = profile.getUserId();

			model.addAttribute("user", user);
			
			if(requestParams.isEmpty() 
					|| requestParams.containsKey("golf_course") == false 
					|| requestParams.containsKey("hole_type") == false
					|| requestParams.containsKey("tee_type") == false) {
				
				model.addAttribute("passbookUrl", null);
			}else{
				Long gameId = passbookService.persistPassbook(requestParams, profile.getUserId());
				String createPassbookUrl = "/createPassbook?gameId="+gameId;
				
				model.addAttribute("passbookUrl", createPassbookUrl);
				model.addAttribute("game_id", gameId);
			}

		}
		return "update";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpPage(WebRequest request, Model model) {

		ProviderSignInUtils util = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

		Connection<?> connection = util.getConnectionFromSession(request);

		RegisterForm form = createRegistrationDTO(connection);
		
		List<AbstractMap.SimpleEntry<String,String>> genders = new ArrayList<AbstractMap.SimpleEntry<String,String>>();
		
		genders.add(new AbstractMap.SimpleEntry<String,String>("value","Male"));
		genders.add(new AbstractMap.SimpleEntry<String,String>("value","Female"));
		
		String[] a = new String[2];
		a[0] = "Male";
		a[1] = "Female";

		String jsonForm = null;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonForm = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		model.addAttribute("register", jsonForm);
		model.addAttribute("genders", a);

		return "register";
	}

	public RegisterForm createRegistrationDTO(Connection<?> connection) {

		RegisterForm form = new RegisterForm();

		if (connection != null) {
			form.setEmail(connection.getDisplayName());
			form.setFirstName(connection.getDisplayName());
			form.setLastName(connection.getDisplayName());
		}

		return form;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String registerAndRedirect(@RequestBody RegisterForm userAccountData, BindingResult result,
			WebRequest request) {

		ProviderSignInUtils util = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

		GolfUserDao golfUser = new GolfUserDao();

		golfUser.setName(userAccountData.getFirstName());
		golfUser.setAge(userAccountData.getAge());
		golfUser.setGender(userAccountData.getGender());

		UserProfile profile = new UserProfile();
		profile.setEmail(userAccountData.getEmail());
		profile.setFirstName(userAccountData.getFirstName());
		profile.setLastName(userAccountData.getLastName());
		profile.setPassword(userAccountData.getPassword());
		profile.setUserId(golfUser);
		profile.setRole(Role.ROLE_USER);

		profile = userProfileService.save(profile);

		util.doPostSignUp(userAccountData.getEmail(), request);
	

		return "/home?id="+profile.getUserId().getUserId();

	}

}
