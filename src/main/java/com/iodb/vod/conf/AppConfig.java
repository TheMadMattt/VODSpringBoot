package com.iodb.vod.conf;

import com.iodb.vod.app.repository.MovieRepository;
import com.iodb.vod.app.repository.RegistrationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class AppConfig {

    @Bean
    public RegistrationRepository registrationRepository() {
	return new RegistrationRepository();
    }

    @Bean
    public UserDetailsServiceImpl userDetailsServiceImpl() {
	return new UserDetailsServiceImpl();
    }

    @Bean
    public MovieRepository movieRepository() {
	return new MovieRepository();
    }
}
