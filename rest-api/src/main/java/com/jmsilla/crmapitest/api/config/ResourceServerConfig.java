package com.jmsilla.crmapitest.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.jmsilla.crmapitest.api.auth.*;

@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private OAuthProperties oAuthProperties;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().anyRequest().hasRole("USER");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(oAuthProperties.getClientId());
    }
    
    @Bean
    public ResourceServerTokenServices tokenServices(OAuthProperties oAuthProperties, AccessTokenValidator tokenValidator) {
        GoogleTokenServices googleTokenServices = new GoogleTokenServices(tokenValidator);
        googleTokenServices.setUserInfoUrl(oAuthProperties.getUserInfoUrl());
        return googleTokenServices;
    }

    @Bean
    public AccessTokenValidator tokenValidator(OAuthProperties oAuthProperties) {
        GoogleAccessTokenValidator accessTokenValidator = new GoogleAccessTokenValidator();
        accessTokenValidator.setClientId(oAuthProperties.getClientId());
        accessTokenValidator.setCheckTokenUrl(oAuthProperties.getCheckTokenUrl());
        return accessTokenValidator;
    }
}
