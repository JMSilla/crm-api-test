package com.jmsilla.crmapitest.api.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "com.jmsilla.crmapitest.api.controller")
@Import({ResourceServerConfig.class})
public class Config {
	@Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("app.yml"));
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }

    @Bean
    public OAuthProperties oAuthProperties() {
        return new OAuthProperties();
    }
}
