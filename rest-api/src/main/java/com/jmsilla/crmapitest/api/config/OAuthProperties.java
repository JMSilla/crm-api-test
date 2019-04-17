package com.jmsilla.crmapitest.api.config;

import org.springframework.beans.factory.annotation.Value;

public class OAuthProperties {
    private String clientId;
    private String clientSecret;
    @Value("${oauth.checkTokenUrl}")
    private String checkTokenUrl;
    @Value("${oauth.userInfoUrl}")
    private String userInfoUrl;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getCheckTokenUrl() {
        return checkTokenUrl;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
}
