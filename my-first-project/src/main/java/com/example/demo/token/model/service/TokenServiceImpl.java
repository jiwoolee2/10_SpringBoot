package com.example.demo.token.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.token.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

	private final JwtUtil tokenUtil;
   
	@Override
	public Map<String, String> MyToken(String username) {
		
	
		String accessToken = tokenUtil.getAccessToken(username);
		String refreshToken = tokenUtil.getRefreshToken(username);
		
		Map<String, String> mytokens = new HashMap();
		mytokens.put("accessToken", accessToken);
		mytokens.put("refreshToken", refreshToken);
	
		return mytokens;
	}
	

}
	
