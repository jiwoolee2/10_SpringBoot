package com.example.demo.mypage.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.like.model.dto.LikeDTO;
import com.example.demo.member.model.dto.MemberDTO;
import com.example.demo.mypage.model.dao.MyPageMapper;
import com.example.demo.productInfo.model.dto.ProInfoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

	private final MyPageMapper myPageMapper;
	
	@Override
	public List<ProInfoDTO> selectLikeInfo(LikeDTO member) {
		
		log.info("aaaa: {}",member.getLikesMember());
		return myPageMapper.selectLikeInfo(member);
	}

	

}
