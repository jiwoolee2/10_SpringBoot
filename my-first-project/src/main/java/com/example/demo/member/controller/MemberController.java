package com.example.demo.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.member.model.dto.MemberDTO;
import com.example.demo.member.model.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("members")  // 복수형을 쓰는 것이 원칙
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	/*
	 * 회원가입 자원요청을 받으면
	 * 아이디.비밀번호.이름을 받아서 가공해서 서비스로 넘김
	 * 
	 * 그리고 서비스에 받아서 JSON형태로 상태코드와 함꼐 앞단으로 넘김
	 * 
	 * /members로 오는 요청을 처리함
	 * GET 					--> 조회요청 (SELECT)
	 * GET(/members/멤버번호) --> 멤버번호로 조건을 걸어서 단일 조회 요청(SELECT) 
	 * POST      			--> 데이터 생성 요청(INSERT)
	 * PUT       			--> 데이터 갱신 요청(UPDATE)
	 * DELETE    			--> 데이터 삭제 요청(DELETE)
	 * 
	 * 계층구조로 식별할 때 /자원/PK
	 * 요청 시 전달값이 많을 때 /자원?키=값&키=값&키=값
	 */
	
	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody @Valid MemberDTO member){
		log.info("member : {}",member);
		memberService.signUp(member);
		return ResponseEntity.status(201).build(); // 회원가입 완료
	}
	
}
