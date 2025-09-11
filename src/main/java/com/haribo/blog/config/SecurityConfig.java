package com.haribo.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.haribo.blog.config.auth.PrincipalDetailService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // IoC가 된다
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인해주는데 password를 가로채기를 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Bean
	public AuthenticationProvider authenticationProvider(
					PrincipalDetailService principalDetailService,
					PasswordEncoder passwordEncoder
			) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(principalDetailService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
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
							.loginPage("/auth/loginForm")
							.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 로그인을 가로채서 대신 로그인 해준다.
							.defaultSuccessUrl("/")
					);
					
		return http.build();
	}
}
