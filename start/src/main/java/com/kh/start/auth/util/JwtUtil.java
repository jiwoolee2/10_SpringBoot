package com.kh.start.auth.util;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.security.KeysBridge;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

// 여기서 토큰 만듦
@Slf4j
@Component
public class JwtUtil {

	// 애플리케이션 설정파일 (application.properties, application.yml)에 정의됨
	@Value("${jwt.secret}")
	private String secretKey;
	private SecretKey key;
	
	//yml파일에 있는 값 읽어오기
	@PostConstruct
	public void init() {
		log.info("{}",secretKey);
		byte[] keyArr = Base64.getDecoder().decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyArr); 	}
	
	
	
	
	public String getAccessToken(String username) {
		// 토큰 만들기
		return Jwts.builder().subject(username)  //사용자이름
							  .issuedAt(new Date()) // 발급일
							  .expiration(new Date(System.currentTimeMillis() + 3600000*24)) // 만료일
							  .signWith(key) // 서명
							  .compact();
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




















