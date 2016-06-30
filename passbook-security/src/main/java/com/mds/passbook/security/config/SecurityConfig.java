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
//		http.
//		csrf()
//		.ignoringAntMatchers("/api/**","/v1/**","/logout")
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.permitAll()
//		.and()
//		.authorizeRequests()
//		.antMatchers("/**").hasAnyRole("ANONYMOUS","USER")
//		.and()
//		.apply(new SpringSocialConfigurer());
		
		
		http.
		csrf()
		.ignoringAntMatchers("/api/**","/v1/**","/logout")
		.and()
//		.disable().
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.authorizeRequests()
		.antMatchers("/login").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/signup").hasAnyRole("ANONYMOUS","USER")
		.antMatchers("/api/**").permitAll()
		.antMatchers("/v1/**").permitAll()
		.antMatchers("/app/**").permitAll()
		.antMatchers("/registerForm").permitAll()
		.antMatchers("/logout").permitAll()
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