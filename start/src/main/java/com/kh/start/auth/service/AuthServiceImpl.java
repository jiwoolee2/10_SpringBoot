package com.kh.start.auth.service;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.exception.CustomAuthenticationException;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.token.model.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	//private final JwtUtil jwtUtil;
	
	@Override
	public Map<String, String> login(MemberDTO member) {
		
		/*
		 * 1. 유효성 검증(아이디/비밀벊값이 입력되었는지,영어/숫자인지, 글자수가 괜찮은지
		 * 
		 * 2. 아이디가 MEMBER_ID컬럼에 존재하는 아이디?
		 * 3. 비밉번호가 컬러멩 존재하는 암호문이 사용자가 입력한 평문으로 만들어진 것이 맞는가?
		 * 
		 * -> SpringSecurity로 이거 진행 == 인증
		 * -> 인증에 성공했을 때 사용자 정보 반환가능 == AuthenticationManager 사용
		 */
		/*
		 * 1. 로그인 후 서비스로 MemberDTO에 담겨 service로 넘어옴
		 * 2. 값을 UsernamePasswordAuthenticationToken 객체에 담아서
		 * 	   authenticationManager가 authenticate메서드를 호출
		 * 		-> UserDetailService에서
		 *  	저장된 아이디, 비밀번호인지 체크 
		 * 		-> authentication객체에 값이 반환되어 나옴
		 * 3. .getPrincipal을 통해 userDetail을 꺼냄
		 * 4. JWT발급
		 * 
		 */
		// 사용자 인증
		Authentication authentication = null;
		try {
		authentication = authenticationManager.authenticate  // = authenticate메서드가 UserDetailService를 호출함!!!!!!
		(new UsernamePasswordAuthenticationToken(member.getMemberId(), 
												 member.getMemberPw()));
		
		} catch(AuthenticationException e) {
			throw new CustomAuthenticationException("아이디 또는 비밀번호를 잘못 입력하셨습니다.");
		}
		
		CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();
		
		//log.info("로그인 성공!");
		//log.info("인증에성공한로그인정보 {}",user);
		//String accessToken = jwtUtil.getAccessToken(user.getUsername());  
		//String refreshToken = jwtUtil.getRefreshToken(user.getUsername());
		//log.info(" accessTokne값 \n refreshTokon값 {}, : {}",accessToken,refreshToken);
		
		Map<String, String> loginResponse = tokenService.generateToken(user.getUsername(),
																	   user.getMemberNo());
		loginResponse.put("memberId", user.getUsername());
		loginResponse.put("memberName", user.getMemberName());
		loginResponse.put("memberNo", String.valueOf(user.getMemberNo()));
		
		return loginResponse;
	}
	
	// 로그인 인증이 성공하면 발급된 토큰에서 정보를 뺴내는 역할을 하는 메서드
	@Override
	public CustomUserDetails getUserDetails() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		return user;
	}

	
	
	
	
	
	
	
	
	
}
