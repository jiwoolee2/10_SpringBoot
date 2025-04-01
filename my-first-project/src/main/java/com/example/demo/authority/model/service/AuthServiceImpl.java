package com.example.demo.authority.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.authority.model.vo.MyUserDetails;
import com.example.demo.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	
	@Override
	public Map<String, String> logIn(MemberDTO member) {
		
		
	Authentication authentication = authenticationManager.authenticate
		(new UsernamePasswordAuthenticationToken(member.getMemberId(),
												 member.getMemberPw()));
	
	
	
	
	
	
	MyUserDetails user = (MyUserDetails)authentication.getPrincipal();
		
		return null;
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
