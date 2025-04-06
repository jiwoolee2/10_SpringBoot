package com.example.demo.authority.model.service;

import java.util.Map;


import com.example.demo.member.model.dto.MemberDTO;


public interface AuthService {

	Map<String, String> logIn(MemberDTO member);
	
}
