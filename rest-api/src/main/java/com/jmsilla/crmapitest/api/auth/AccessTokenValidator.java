package com.jmsilla.crmapitest.api.auth;

public interface AccessTokenValidator {
    AccessTokenValidationResult validate(String accessToken);
}
