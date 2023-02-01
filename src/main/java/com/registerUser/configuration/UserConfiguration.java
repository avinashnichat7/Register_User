package com.registerUser.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
//		//http.csrf().disable()
		http
		.csrf().disable()
				// csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				.authorizeRequests().
				//antMatchers("/login/**").hasRole("ADMIN").
				antMatchers("login/**").permitAll()
			.antMatchers("/api/users**").hasRole("ADMIN")
				// .antMatchers("/login/**").hasRole("User").
				.anyRequest().
				authenticated().
				and().
				formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("avinash").password(this.passwordEncoder().
				encode("12345"))
				.roles("ADMIN");

		auth.inMemoryAuthentication().withUser("nichat").password(this.passwordEncoder().encode("12345"))
		.roles("USER");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder(10);
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/public/**").hasRole("Normal")
//		.anyRequest().
//		authenticated().
//		and()
//		.httpBasic();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication().withUser("avinash").password(passwordEncoder().encode("nichat")).roles("Admin");
//		auth.inMemoryAuthentication().withUser("nichat").password(passwordEncoder().encode("11111")).roles("Normal");
//		
//	}
//	
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}


//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.
//		authorizeRequests().
//		anyRequest().
//		authenticated().
//		and().
//		httpBasic();
//
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.inMemoryAuthentication().withUser("Avinash").password("12345").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("Nichat").password("12121").roles("USER");
//
//	}
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		
//		return NoOpPasswordEncoder.getInstance();
//	}


}
