package com.example.demo.configuration;

import java.util.Arrays;

import com.example.demo.configuration.filter.JwtFilter;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration // 메서드 Bean으로 등록
public class SecurityConfigure {

    private final JwtFilter jwtFilter;

   
	// 로그인,회원가입 등 관리하는데 사용
	// security와 관련된 빈들을 등록하고 관리하기위해서 이거 만듦
	// 필요없는 필터들 안쓰려면 SecurityFilterChain이라는 모양으로 만들어야함
	// => securityFilterChain을 반환하는 메서드를 만들어야함
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      
   
	return httpSecurity.formLogin(AbstractHttpConfigurer::disable) //=> formLogin 비활성화
					   .httpBasic(AbstractHttpConfigurer::disable)
					   .csrf(AbstractHttpConfigurer::disable)
					   .cors(Customizer.withDefaults())
					   .authorizeHttpRequests(requests->
					   {
						   requests.requestMatchers(HttpMethod.POST,"/log-in","/members").permitAll();
						   requests.requestMatchers(HttpMethod.GET,"/productinfo/**","boards/**").permitAll();
						   requests.requestMatchers(HttpMethod.POST, "/like", "/my-page","/boards/**").authenticated();
						   requests.requestMatchers("/admin/**").hasRole("ADMIN");
						   
					   })
					   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
					   .build(); 
		            
	}
	
	 @Bean
	   public CorsConfigurationSource corsConfigurationSource() {
	      CorsConfiguration configuration = new CorsConfiguration();
	      configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
	      configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
	      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	      configuration.setAllowCredentials(true);
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      source.registerCorsConfiguration("/**", configuration);
	      
	      return source;
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
