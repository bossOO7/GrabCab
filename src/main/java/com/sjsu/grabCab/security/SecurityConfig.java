package com.sjsu.grabCab.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private PassengerAuthenticationProvider passengerAuthenticationProvider;
	
	@Autowired
	private DriverAuthenticationProvider driverAuthenticationProvider;
	
	@Autowired
	private AdminAuthenticationProvider adminAuthenticationProvider;

	@Autowired
	private MySavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(passengerDAO)
//		.passwordEncoder(passwordEncoder());
		auth.authenticationProvider(passengerAuthenticationProvider);
		auth.authenticationProvider(driverAuthenticationProvider);
		auth.authenticationProvider(adminAuthenticationProvider);
	}

	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.authorizeRequests().antMatchers("/").permitAll().antMatchers("/resources/**").permitAll()
				.antMatchers(HttpMethod.POST, "/passenger").permitAll()
				.antMatchers(HttpMethod.POST, "/driver").permitAll()
				.antMatchers(HttpMethod.POST, "/admin").permitAll()
				.anyRequest().authenticated().and().formLogin()
				.successHandler(authenticationSuccessHandler)
				.failureHandler(new SimpleUrlAuthenticationFailureHandler()).loginPage("/").and().logout();
	}
	

	@Bean
	public MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
		return new MySavedRequestAwareAuthenticationSuccessHandler();
	}

	@Bean
	public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
}
