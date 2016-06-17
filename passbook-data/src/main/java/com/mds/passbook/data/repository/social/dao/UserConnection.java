package com.mds.passbook.data.repository.social.dao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "UserConnection", indexes=@Index(
		columnList="userId,providerId,rank", name="UserConnectionRank", unique=true))
//@Table(name = "UserConnection", uniqueConstraints = @UniqueConstraint(columnNames = { "userId", "providerId",
//		"rank" }, name = "UserConnectionRank") )
public class UserConnection {

	@EmbeddedId
	UserConnectionCompositeId id;

	private int rank;

	private String displayName;

	private String profileUrl;

	private String imageUrl;

	private String accessToken;

	private String secret;

	private String refreshToken;

	private long expireTime;

	public UserConnectionCompositeId getId() {
		return id;
	}

	public void setId(UserConnectionCompositeId id) {
		this.id = id;
	}

	@Column(name = "rank", nullable = false)
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Column(name = "displayName", length = 255)
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "profileUrl", length = 512)
	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	@Column(name = "imageUrl", length = 512)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "accessToken", nullable = false, length = 512)
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name = "secret", length = 512)
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Column(name = "refreshToken", length = 512)
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	@Column(name = "expireTime")
	public long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(long expireTime) {
		this.expireTime = expireTime;
	}

}
