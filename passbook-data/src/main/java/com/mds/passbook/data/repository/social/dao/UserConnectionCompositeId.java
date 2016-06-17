package com.mds.passbook.data.repository.social.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserConnectionCompositeId implements Serializable {

	private String userId;

	private String providerId;

	private String providerUserId;

	@Column(name = "userId", nullable = false, length = 255)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "providerId", nullable = false, length = 255)
	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@Column(name = "providerUserId", length = 255)
	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

}
