package com.mds.passbook.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mds.passbook.data.repository.social.dao.UserConnection;
import com.mds.passbook.data.repository.social.dao.UserConnectionCompositeId;

public interface UserConnectionRepository extends JpaRepository<UserConnection, UserConnectionCompositeId>{

}
