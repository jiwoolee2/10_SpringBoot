package com.example.demo.mypage.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.like.model.dto.LikeDTO;
import com.example.demo.mypage.model.service.MyPageService;
import com.example.demo.productInfo.model.dto.ProInfoDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class MyPageController {

	
	private final MyPageService myPageService;
	@PostMapping("my-page")
	public ResponseEntity<?> getLikedProducts(@RequestBody LikeDTO member) {
		log.info("memberID : {}",member);
	    List<ProInfoDTO> likedList = myPageService.selectLikeInfo(member);
	    return ResponseEntity.ok(likedList);
	}
	
}
