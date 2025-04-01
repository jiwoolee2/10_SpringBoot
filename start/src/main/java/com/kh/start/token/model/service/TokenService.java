package com.kh.start.token.model.service;

import java.util.Map;

public interface TokenService {

	// 1. AccessToken 만들기
	//String accessToken = jwtUtil.getAccessToken(user.getUsername());
	
	// 2. RefreshToken 만들기
	//String refreshToken = jwtUtil.getRefreshToken(user.getUsername());
	
	// 여기서 username = memberId;
	Map<String, String> generateToken(String username);
	
	

	//Map<String, String> createToken(String username);
	// 3. RefreshToken Table에 INSERT하기
	
	// 4. 만료기간이 끝난 refreshToken DELETE하기
	
	// 5. 사용자가 RefreshToken을 가지고 증명하려 할 때 DB가서 조회해오기
	
}
