package com.mds.passbook.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.security.dao.UserProfile;

public class PassbookSecurityService implements UserDetailsService {

	private UserProfileRepository userRepo;

	@Autowired
	public PassbookSecurityService(UserProfileRepository userProfileRepository) {
		this.userRepo = userProfileRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserProfile profile = userRepo.findByEmail(username);

		if (profile == null) {
			throw new UsernameNotFoundException("Username not found!");
		}

		return buildUser(profile);
	}

	public User buildUser(UserProfile user) {
		
		User securityUser = null;

		if(user.getPassword() == null){
			user.setPassword("");
		}
		
		securityUser = new User(user.getEmail(), user.getPassword(), buildGrantedAuthority(user));

		return securityUser;
	}

	public Collection<GrantedAuthority> buildGrantedAuthority(UserProfile user) {
		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

		authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return authorityList;
	}

}
