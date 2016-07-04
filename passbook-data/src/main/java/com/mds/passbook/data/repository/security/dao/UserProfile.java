package com.mds.passbook.data.repository.security.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mds.passbook.data.repository.golf.dao.BaseEntity;
import com.mds.passbook.data.repository.golf.dao.GolfUserDao;


@Entity
@Table(name="USER_PROFILE")
public class UserProfile extends BaseEntity<Long>{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;
    
    @Column(name= "USERNAME", length = 100, nullable=false, unique = true)
    private String username;

    @Column(name = "FIRST_NAME", length = 100,nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", length = 100, nullable = false)
    private String lastName;

    @Column(name = "PASSWORD", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "SIGN_IN_PROVIDER", length = 20)
    private SocialMediaService signInProvider;
    
    @OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="GOLF_USER")
    private GolfUserDao userId;

	public GolfUserDao getUserId() {
		return userId;
	}

	public void setUserId(GolfUserDao userId) {
		this.userId = userId;
	}

	public SocialMediaService getSignInProvider() {
		return signInProvider;
	}

	public void setSignInProvider(SocialMediaService signInProvider) {
		this.signInProvider = signInProvider;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", role=" + role + ", signInProvider=" + signInProvider + ", userId="
				+ userId + "]";
	}


}
