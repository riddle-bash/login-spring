package com.riddlebash.login.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.riddlebash.login.services.userdetails.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf(c -> c.disable())
		
		.authorizeHttpRequests(request -> request.requestMatchers("/admin-page")
				.permitAll().requestMatchers("/user-page").permitAll()
				.requestMatchers("/registration", "/styles/**").permitAll()
				.anyRequest().authenticated())
		.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
				.defaultSuccessUrl("/").permitAll())
		.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll());
		
		return httpSecurity.build();
	}
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auBuilder) throws Exception {
		auBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
}
