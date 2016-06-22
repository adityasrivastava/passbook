package com.mds.passbook.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mds.passbook.data.repository.security.dao.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{
	
	UserProfile findByEmail(String email);

}
