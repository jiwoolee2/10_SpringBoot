package com.example.demo.member.model.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.member.model.dto.MemberDTO;
import com.example.demo.member.model.vo.VoMember;

@Mapper
public interface MemberMapper {

	// 존재하는 아이디인지 검사
	@Select("SELECT MEMBER_ID memberId,MEMBER_PW memberPw,MEMBER_NAME memberName,MEMBER_PHONE memberPhone, role  FROM UNIKLO_MEMBER WHERE MEMBER_ID =#{memberId}")
	MemberDTO checkIdByMemberId(String memberId);
	
	// table에 값 넣기 == 회원가입
	@Insert("INSERT INTO UNIKLO_MEMBER VALUES(#{memberId},#{memberPw},#{memberName},#{memberPhone},#{role},SYSDATE)")
	void signUp(VoMember member);
	
}
