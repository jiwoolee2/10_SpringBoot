package com.example.demo.authority.model.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.example.demo.authority.model.vo.MyUserDetails;
import com.example.demo.exception.WrongIdPasswordException;
import com.example.demo.member.model.dto.MemberDTO;
import com.example.demo.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Override
	public Map<String, String> logIn(MemberDTO member) {
		
	Authentication authentication = null;
	log.info("{}", member);
	// 아이디 비밀번호 검증 => 검증해서 userDetails객체형태로 principal에 저장
	try {
		authentication = authenticationManager.authenticate
		(new UsernamePasswordAuthenticationToken(member.getMemberId(),
												 member.getMemberPw()));
	} catch(AuthenticationException e) {
		throw new WrongIdPasswordException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
	}
	
	// 아이디 비밀번호가 존재한다면 user에 담기
	MyUserDetails user = (MyUserDetails)authentication.getPrincipal();
	
	// 토큰 생성해서 주기
	Map<String, String> mytokens = tokenService.MyToken(user.getUsername());
	
	
	return mytokens;
		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
