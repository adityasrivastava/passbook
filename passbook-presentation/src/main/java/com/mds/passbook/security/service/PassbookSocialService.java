package com.mds.passbook.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.data.repository.dao.UserProfile;

public class PassbookSocialService implements SocialUserDetailsService {

	private UserProfileRepository userRepo;

	@Autowired
	public PassbookSocialService(UserProfileRepository userProfileRepository) {
		this.userRepo = userProfileRepository;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {

		UserProfile user = userRepo.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username not found " + username);
		}

		return buildUser(user);
	}

	public SocialUser buildUser(UserProfile user) {

		SocialUser securityUser = new SocialUser(user.getEmail(), user.getPassword(), buildGrantedAuthority(user));

		return securityUser;
	}

	public Collection<GrantedAuthority> buildGrantedAuthority(UserProfile user) {
		Collection<GrantedAuthority> authorityList = new ArrayList<GrantedAuthority>();

		authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));
		return authorityList;
	}

}
