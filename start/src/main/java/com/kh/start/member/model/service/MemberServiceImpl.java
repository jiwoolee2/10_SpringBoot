package com.kh.start.member.model.service;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.exception.MemberIdDuplicateException;
import com.kh.start.member.model.dao.MemberMapper;
import com.kh.start.member.model.dto.ChangePasswordDTO;
import com.kh.start.member.model.dto.MemberDTO;
import com.kh.start.member.model.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberMapper mapper;
	private final PasswordEncoder passwordEncoder;
	@Override
	public void signUp(MemberDTO member) {
		
		MemberDTO searchedMember = mapper.getMemberByMemberId(member.getMemberId());
		
		
		if(searchedMember != null) {
			throw new MemberIdDuplicateException("이미 존재하는 아이디");
		}
		
		// 할 일 두개
		// 첫 번째 : 비밀번호 암호화
		// 두 번째 : Role주기
		
		Member memberValue = Member.builder()
								   .memberId(member.getMemberId())
								   .memberPw(passwordEncoder.encode(member.getMemberPw()))
								   .memberName(member.getMemberName())
								   .role("ROLE_USER")		// role필드 값으로는 "ROLE_어쩌고" 이런식으로 들어가야함
								   .build();
		
		mapper.signUp(memberValue);
		log.info("등록 성공 : {}", memberValue);
		
	}
	@Override
	public void changePassword(ChangePasswordDTO passwordEntity) {
		
		
		Long memberNo = passwordMatches(passwordEntity.getCurrentPassword());
		
		String encodedPassword = passwordEncoder.encode(passwordEntity.getCurrentPassword());
		
		Map<String, Object> changeRequest = new HashMap();
		changeRequest.put("memberNo", memberNo);
		changeRequest.put("encodedPassword", encodedPassword);
		
		// mapper에가서 UPDATE
		// UPDATE TB_BOOT_MEMBER MEMBER_PW = ? WHERE MEMBER_NO/ID = 현재요청한 사용자의 식별값
		mapper.changePassword(changeRequest);
		log.info("mapper실행 완료?");
	}
	@Override
	public void deleteByPassword(String password) {
		
		// 사용자가 입력한 비밀번호와 DB에 저장된 비밀번호가 둘이 일치하는지
		
		// Mapper가서 -> DELETE
		Long memberNo = passwordMatches(password);
		
		mapper.deleteByPassword(memberNo);
		
	}
	
	private Long passwordMatches(String password) {
		
		// 현재 비밀번호를 맞게 입력했는지 검증
		
		// 맞다면 새로운 비밀번호를 암호화
		
		// SecurityContextHolder에서 사용자 정보 받아오기
				
		// -> PasswordEncoder => matches()
		// 첫번째 인자 : 평문, 두번째 인자 : 암호문
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // SecurityContextHolder 어디서 온것인가!?
		CustomUserDetails user = (CustomUserDetails)auth.getPrincipal();
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		
		return user.getMemberNo();
	}
}