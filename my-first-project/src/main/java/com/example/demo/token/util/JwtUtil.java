package com.example.demo.token.util;

import java.awt.RenderingHints.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Slf4j
@Component
public class JwtUtil {

	///// 토큰 생성!!!!!!

	
	
	

	// application파일에 jwt.secret의 값을 seceretkey 변수에 담기
	@Value("${jwt.secret}") //
	private String secretKey;
	
	private SecretKey key;
	
	//yml파일에 있는 값 읽어오기
		@PostConstruct
		public void init() {
			
			// secretKey에 들어있는 문자열을 바이트 배열로 바꿈
			byte[] keyArr = Base64.getDecoder().decode(secretKey); 
			
			// 암호화 알고리즘(HMAC)이 이해할 수 있는 형식으로 바꿔줘야한다
			// -> 그래야 key를 암호화할 수 있음
			this.key = Keys.hmacShaKeyFor(keyArr); }
		
		
		
		// JWT는 보통 header + payload + signature 3파트로 구성됨
		
		public String getAccessToken(String username) {
			// 토큰 만들기
			return Jwts.builder().subject(username)  //사용자이름
								  .issuedAt(new Date()) // 발급일
								  .expiration(new Date(System.currentTimeMillis() + 3600000*24)) // 만료일
								  .signWith(key) // 서명
								  .compact(); // 최종 Jwt문자열 생성
		}
		
		public String getRefreshToken(String username) {
			return Jwts.builder().subject(username)  //사용자이름
								  .issuedAt(new Date()) // 발급일
								  .expiration(new Date(System.currentTimeMillis() + 3600000*24 * 3)) // 만료일
								  .signWith(key) // 서명
								  .compact();
		}
	
	
		// 얘가 알아서 토큰 검증 - 유효일 안지났는지, 우리가발급한게 맞는지
		public Claims parseJwt(String token) {
			
			return Jwts.parser()
					   .verifyWith(key)
					   .build()
					   .parseSignedClaims(token)
					   .getPayload();
		}	
	
	
	
	
	
	
	
	
	
	
	
}
