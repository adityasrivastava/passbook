package com.mds.passbook.controller;

import java.security.Principal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
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

	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(@RequestParam(name = "id", required = false) String userId, Model model,
			Principal principal) {

		if (userId != null) {
			GolfUserDao user = new GolfUserDao();

			user = golfUserService.findOne(Long.valueOf(userId));

			model.addAttribute("user", user);

		}
		return "index";
	}
	
	@RequestMapping(value= "/createPass", method = RequestMethod.GET)
	public String createPass(@RequestParam Map<String, String> requestParams, Principal principal, Model model, RedirectAttributes redirectAtr){
		
		UserProfile profile = userProfileService.findByUsername(principal.getName());
		
		Long gameId = passbookService.persistPassbook(requestParams, profile.getUserId());
		
		requestParams.put("game_id", Long.toString(gameId));
		redirectAtr.addAllAttributes(requestParams);
		
		return "redirect:/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam Map<String, String> requestParams, 
			Principal principal, 
			Model model) {

		UserProfile profile = userProfileService.findByUsername(principal.getName());

		if (profile != null) {
			GolfUserDao user = new GolfUserDao();
			user = profile.getUserId();

			model.addAttribute("user", user);

			if (requestParams.isEmpty() || requestParams.containsKey("golf_course") == false
					|| requestParams.containsKey("hole_type") == false
					|| requestParams.containsKey("tee_type") == false) {

				model.addAttribute("passbookUrl", null);
				model.addAttribute("cookie_token", System.currentTimeMillis() / 1000L);
				
				if(requestParams.containsKey("game_id") == true){
					model.addAttribute("game_id", requestParams.get("game_id"));
				}
	
			} else {

				String createPassbookUrl = "/createPassbook?gameId=" + requestParams.get("game_id");

				model.addAttribute("passbookUrl", createPassbookUrl);
				model.addAttribute("game_id", requestParams.get("game_id"));
				model.addAttribute("cookie_token", System.currentTimeMillis() / 1000L);

			}

		}
		return "update";
	}

	@RequestMapping("/login")
	public String loginPage(HttpServletRequest request, HttpServletResponse response) {
		
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout"; 
	}
	
	@RequestMapping(value="/updateForm", method = RequestMethod.GET)
	public String updateUser(Principal principal, Model model){
		
		UserProfile profile = userProfileService.findByUsername(principal.getName());
		
		RegisterForm form = new RegisterForm();
		
		form.setAge(profile.getUserId().getAge());
		form.setEmail(profile.getEmail());
		form.setFirstName(profile.getFirstName());
		form.setLastName(profile.getLastName());
		form.setGender(profile.getUserId().getGender());
		form.setPassword(profile.getPassword());
		form.setHandicap(profile.getUserId().getHandicap());
		

		String jsonForm = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonForm = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("register", jsonForm);
		model.addAttribute("updateFormRedirect",true);
		
		return "updateForm";
			
	}
	
	
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String registerUser(@ModelAttribute("register") RegisterForm registerForm, Model model, Principal principal){
		
		String jsonForm = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonForm = mapper.writeValueAsString(registerForm);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		model.addAttribute("register", jsonForm);
		model.addAttribute("updateFormRedirect",false);
		
		
		return "register";
	}

	/**
	 * Register with facebook login and persist to Userprofile and Userconnection if incomplete fields
	 * redirect to register form 
	 * @param webRequest
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpPage(WebRequest webRequest, HttpServletRequest request, Model model) {
		
		RegisterForm form = new RegisterForm();

		ProviderSignInUtils util = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

		Connection<?> connection = util.getConnectionFromSession(webRequest);

		Facebook facebook = (Facebook) connection.getApi();

		User user = facebook.userOperations().getUserProfile();

		form = createRegistrationDTO(user);

		if (user.getEmail() != null && user.getBirthday() != null) {
			
			form.setUsername(form.getEmail());
			
			UserProfile profile = persistUserRegistrationDetails(form);
			
			util.doPostSignUp(form.getEmail(), webRequest);

			authenticateUserAndSetSession(user.getEmail(), "", request);

			return "redirect:/home?id=" + profile.getUserId().getUserId();
		}
		
		String jsonForm = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonForm = mapper.writeValueAsString(form);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		model.addAttribute("register", jsonForm);
		model.addAttribute("updateFormRedirect",false);
		
		util.doPostSignUp(form.getEmail(), webRequest);

		return "register";
	}

	public RegisterForm createRegistrationDTO(User connection) {

		RegisterForm form = new RegisterForm();

		if (connection != null) {
			form.setEmail(connection.getEmail());
			form.setUsername(connection.getEmail());
			form.setFirstName(connection.getFirstName());
			form.setLastName(connection.getLastName());
			form.setGender(connection.getGender());
			form.setHandicap(0);
		}

		return form;
	}
	
	/*
	 * When user updates the User profile field
	 * 
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String updateForm(@RequestBody RegisterForm userAccountData, BindingResult result,
			WebRequest request, Principal principal) {
		
		boolean userEmailOrPasswordChanged = false;
		
		UserProfile profileExistCheck = userProfileService.findByUsername(principal.getName());
		
		if(userAccountData.getPassword() != null && (userAccountData.getPassword().compareTo(profileExistCheck.getPassword()) != 0)){
			userEmailOrPasswordChanged = true;
		}
		
		profileExistCheck = updateUserRegistrationDetails(userAccountData, profileExistCheck);
		
		if(userEmailOrPasswordChanged == true){
			return "/logout";
		}

		return "/home?id=" + profileExistCheck.getUserId().getUserId();

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String registerAndRedirect(@RequestBody RegisterForm userAccountData, BindingResult result,
			WebRequest webRequest, HttpServletRequest request) {
		
		UserProfile profileExistCheck = userProfileService.findByUsername(userAccountData.getUsername());
		UserProfile profileExistCheckByEmail = userProfileService.findByEmail(userAccountData.getEmail());
		
		if(profileExistCheck != null){
			return "false";
		}
		
		UserProfile profile = persistUserRegistrationDetails(userAccountData);
		
		ProviderSignInUtils util = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

		util.doPostSignUp(userAccountData.getEmail(), webRequest);
		
		authenticateUserAndSetSession(profile.getEmail(), userAccountData.getPassword(), request);

		return "/home?id=" + profile.getUserId().getUserId();

	}
	
	public UserProfile updateUserRegistrationDetails(RegisterForm userAccountData, UserProfile profile) {

		GolfUserDao golfUser = new GolfUserDao();
		golfUser = profile.getUserId();
		
		golfUser.setHandicap(userAccountData.getHandicap());
		golfUser.setName(userAccountData.getFirstName());
		golfUser.setAge(userAccountData.getAge());
		golfUser.setGender(userAccountData.getGender());

		profile.setEmail(userAccountData.getEmail());
		profile.setFirstName(userAccountData.getFirstName());
		profile.setLastName(userAccountData.getLastName());
		profile.setPassword(userAccountData.getPassword());
		profile.setUserId(golfUser);

		profile = userProfileService.save(profile);

		return profile;
	}

	public UserProfile persistUserRegistrationDetails(RegisterForm userAccountData) {

		GolfUserDao golfUser = new GolfUserDao();

		golfUser.setName(userAccountData.getFirstName());
		golfUser.setAge(userAccountData.getAge());
		golfUser.setGender(StringUtils.capitalize(userAccountData.getGender()));
		golfUser.setHandicap(userAccountData.getHandicap());

		UserProfile profile = new UserProfile();
		profile.setEmail(userAccountData.getEmail());
		profile.setUsername(userAccountData.getUsername());
		profile.setFirstName(userAccountData.getFirstName());
		profile.setLastName(userAccountData.getLastName());
		profile.setPassword(userAccountData.getPassword());
		profile.setUserId(golfUser);
		profile.setRole(Role.ROLE_USER);

		profile = userProfileService.save(profile);

		return profile;
	}

	private void authenticateUserAndSetSession(String username, String password, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

		request.getSession();

		token.setDetails(new WebAuthenticationDetails(request));
		org.springframework.security.core.Authentication authenticatedUser = authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}

}
