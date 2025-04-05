package com.example.demo.like.model.service;

import com.example.demo.like.model.dto.LikeDTO;

public interface LikeService {

	void likeCheck(LikeDTO likes);

	
	void insertLikes(LikeDTO likes);
	
	
}
