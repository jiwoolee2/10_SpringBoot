package com.example.demo.like.controller;

import java.net.ResponseCache;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.like.model.dto.LikeDTO;
import com.example.demo.like.model.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class LikeController {
	
	private final LikeService likeService;
	
	@PostMapping("like")
	public ResponseEntity<?> likeCheck(@RequestBody LikeDTO likes) {
		
		log.info("{},{}",likes.getLikesMember(),likes.getLikesProduct());
		
		likeService.likeCheck(likes);
		
		return null;
	}
}
