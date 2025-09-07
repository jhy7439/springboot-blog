package com.haribo.blog.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.core.annotation.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.FormLoginBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
					.authorizeHttpRequests((auth) -> auth
							
							.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
							
							.requestMatchers("/auth/**").permitAll()
							.anyRequest().authenticated())
					.formLogin((login) -> login
							.loginPage("/auth/loginForm").permitAll()
					);
					

		return http.build();
	}
}
