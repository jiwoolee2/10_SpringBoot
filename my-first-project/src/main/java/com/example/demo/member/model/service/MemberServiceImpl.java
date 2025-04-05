package com.example.demo.member.model.service;

import java.lang.module.ModuleDescriptor.Builder;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ExistIdException;
import com.example.demo.member.model.dao.MemberMapper;
import com.example.demo.member.model.dto.MemberDTO;
import com.example.demo.member.model.vo.VoMember;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	// 1.회원가입!!
	@Override
	public void signUp(MemberDTO member) {
		
		// 아이디,비밀번호,이름,전화번호 유효성검사 => DTO에서 이미 완료
		// 1. 아이디가 존재하는지 아닌지 확인해서 존재하면 예외 발생시켜야함. => 로그인 시에도 써야함
		// 2. 존재하지 않는다면 비밀번호 암호화 주기
		// 3. 존재하지 않는다면 Insert
		
		
		MemberDTO memberDTO = memberMapper.checkIdByMemberId(member.getMemberId());
		if(memberDTO != null) {
			throw new ExistIdException("존재하는 아이디 입니다."); 		
		} 
		
		
		log.info("{}",member);
		// 비밀번호 암호화
		VoMember voMember = VoMember.builder().memberId(member.getMemberId())
											  .memberPw(passwordEncoder.encode(member.getMemberPw()))
											  .memberName(member.getMemberName())
											  .memberPhone(member.getMemberPhone())
											  .role("ROLE_USER")
											  .build();
		log.info("ssss{}",voMember);	
		memberMapper.signUp(voMember);

	}

}











