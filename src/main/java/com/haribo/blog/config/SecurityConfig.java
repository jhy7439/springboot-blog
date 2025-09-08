package com.haribo.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean // IoC가 된다
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
					.csrf(csrf -> csrf.disable()) // CSRF 토큰 비활성화
		            .authorizeHttpRequests((auth) -> auth
							
							.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
							
							.requestMatchers("/","/auth/**","/js/**","/css/**","/image/**").permitAll()
							.anyRequest().authenticated())
					.formLogin((login) -> login
							.loginPage("/auth/loginForm").permitAll()
					);
					

		return http.build();
	}
}
