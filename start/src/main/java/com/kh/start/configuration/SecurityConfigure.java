package com.kh.start.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.kh.start.configuration.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration // 메서드 Bean으로 등록
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfigure {

    private final JwtFilter filter;

  
	
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
							   requests.requestMatchers(HttpMethod.POST,"/auth/login","/auth/refresh","/members").permitAll(); // => 인가절차 없이 바로 수행될 수 있게함
							   requests.requestMatchers("/admin/**").hasRole("ADMIN"); // admin으로 들어오는 요청은 Role이 ADMIN일 때만 수행
							   requests.requestMatchers(HttpMethod.GET,"/uploads/**","/boards/**","/comments/**","/comments").permitAll();
							   requests.requestMatchers(HttpMethod.PUT,"/members","/boards/**").authenticated(); // => 인가를받지못하면(권한x) 수행 x
							   requests.requestMatchers(HttpMethod.DELETE,"/members","/boards/**").authenticated(); // => 인가를받지못하면(권한x) 수행 x
							   requests.requestMatchers(HttpMethod.POST,"/boards","/comments").authenticated(); // => 인가를받지못하면(권한x) 수행 x
						   })
						   /*
						    * sessionManagement : 세션을 어떻게 관리할 것인지를 지정할 수 있음
						    * sessionCreationPolicy : 세션 사용 정책을 설정
						    */
						   .sessionManagement(manager ->
								   				manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						   .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
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
