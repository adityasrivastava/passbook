package com.mds.passbook.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

import com.mds.passbook.data.repository.UserProfileRepository;
import com.mds.passbook.security.service.PassbookSecurityService;
import com.mds.passbook.security.service.PassbookSocialService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserProfileRepository userProfileRepo;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.
			userDetailsService(userDetailsService());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		csrf()
		.disable().
		formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/login").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/signup").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/golfDetails").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/user").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/holes").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/tee").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/score").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/pass").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/v1/**").permitAll()
		.antMatchers("/golfCourse").hasAnyRole("ANONYMOUS","USER")
		.and()
		.authorizeRequests()
		.antMatchers("/**")
		.hasRole("USER")
		.and()
		.rememberMe()
//		.authenticated()
		.and()
         .apply(new SpringSocialConfigurer());

	}
	
	@Bean
	public PassbookSocialService socialService(){
		return new PassbookSocialService(userProfileRepo);
	}
	
	@Override
	@Bean
	public PassbookSecurityService userDetailsService(){
		return new PassbookSecurityService(userProfileRepo);
	}

}