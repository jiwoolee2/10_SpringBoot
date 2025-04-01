package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 메서드 Bean으로 등록
public class SecurityConfigure {
	// 로그인,회원가입 등 관리하는데 사용
	// security와 관련된 빈들을 등록하고 관리하기위해서 이거 만듦
	// 필요없는 필터들 안쓰려면 SecurityFilterChain이라는 모양으로 만들어야함
	// => securityFilterChain을 반환하는 메서드를 만들어야함
	
	@Bean
	   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	      
	   
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable) //=> formLogin 비활성화
						   .httpBasic(AbstractHttpConfigurer::disable)
						   .csrf(AbstractHttpConfigurer::disable)
							.build(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
