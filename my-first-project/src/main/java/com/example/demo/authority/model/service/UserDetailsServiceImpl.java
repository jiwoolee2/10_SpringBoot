package com.example.demo.authority.model.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.authority.model.vo.MyUserDetails;
import com.example.demo.member.model.dao.MemberMapper;
import com.example.demo.member.model.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

	private final MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// 아이디가 있는경우 user에 담음
		MemberDTO user = memberMapper.checkIdByMemberId(username);
		// 아이디가 없는경우 예외처리
		if(user == null) {
			throw new UsernameNotFoundException("아이디를 잘못 입력하셨습니다.");
		}
	
		
		System.out.println(user);
		
		 MyUserDetails myUserDetails = MyUserDetails.builder().username(user.getMemberId())
												   .password(user.getMemberPw())
												   .memberName(user.getMemberName())
												   .memberPhone(user.getMemberPhone())
												   .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole())))
												   .build();
							   System.out.println("나없음" + myUserDetails);
		 return myUserDetails;
				
	}
}
