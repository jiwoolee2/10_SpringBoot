package com.kh.start.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
	      
	      //return httpSecurity.formLogin().disable().build();
			/*
			 * return httpSecurity.formLogin(new
			 * Customizer<FormLoginConfigurer<HttpSecurity>>() {
			 * 
			 * @Override public void customize(FormLoginConfigurer<HttpSecurity> t) {
			 * 
			 * t.disable(); } }).build();  --> 너무 김
			 * 
			 * Cross Site Request Forgery => jsp면 필요하지만 앞단을 react가 담당해서 없어도 됨.
			 * 
			 * <img src="http://우리도메인/logout"/>
			 * 
			 * <form action="http://우리도메인/logout" action="post">
			 * 	<input type="hidden" value="admin" name="userId"/>
			 * 	<button>호호호 눌러보세요~~</button>
			 * </form>
			 */
		// spring filter chain을 만드는 것임
		return httpSecurity.formLogin(AbstractHttpConfigurer::disable) //=> formLogin 비활성화
						   .httpBasic(AbstractHttpConfigurer::disable)
						   .csrf(AbstractHttpConfigurer::disable)
						   .authorizeHttpRequests(requests ->{
							   requests.requestMatchers(HttpMethod.POST,"/auth/login","/members").permitAll(); // => 인가절차 없이 바로 수행될 수 있게함
							   requests.requestMatchers("/admin/**").hasRole("ADMIN"); // admin으로 들어오는 요청은 Role이 ADMIN일 때만 수행
							   requests.requestMatchers(HttpMethod.PUT,"/members").authenticated(); // => 인가를받지못하면(권한x) 수행 x
						   })
						   .build(); 
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
}
