package com.mds.passbook.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mds.passbook.bean.GolfUser;
import com.mds.passbook.bean.RegisterForm;
import com.mds.passbook.data.repository.GolfUserRepository;
import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.dao.GolfUserDao;
import com.mds.passbook.data.repository.dao.Role;
import com.mds.passbook.data.repository.dao.UserProfile;

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
	public String update(Principal principal, Model model) {
		
		UserProfile profile = userProfileService.findByEmail(principal.getName());
		
		
		PassbookStatus.getInstance();
		PassbookStatus.setUpdateStatus(false);
		if(profile != null){
			GolfUserDao user = new GolfUserDao();
			
			user = profile.getUserId();
			
			model.addAttribute("user", user);

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

		System.out.println("NAME " + connection.getDisplayName());

		RegisterForm form = createRegistrationDTO(connection);

		model.addAttribute("register", form);

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

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String registerAndRedirect(@ModelAttribute("register") RegisterForm userAccountData, BindingResult result,
			WebRequest request) {

		ProviderSignInUtils util = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);

		GolfUserDao golfUser = new GolfUserDao();

		golfUser.setName(userAccountData.getFirstName());

		UserProfile profile = new UserProfile();
		profile.setEmail(userAccountData.getEmail());
		profile.setFirstName(userAccountData.getFirstName());
		profile.setLastName(userAccountData.getLastName());
		profile.setPassword(userAccountData.getPassword());
		profile.setUserId(golfUser);
		profile.setRole(Role.ROLE_USER);

		profile = userProfileService.save(profile);

		util.doPostSignUp(userAccountData.getEmail(), request);
	

		return "redirect:/home?id="+profile.getUserId().getUserId();

	}

}
