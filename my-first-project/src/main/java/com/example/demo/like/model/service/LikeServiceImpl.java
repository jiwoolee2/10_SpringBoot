package com.example.demo.like.model.service;

import org.springframework.stereotype.Service;

import com.example.demo.exception.DuplicatedLikeException;
import com.example.demo.like.model.dao.LikeMapper;
import com.example.demo.like.model.dto.LikeDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

	
	private final LikeMapper likeMapper;
	// table에 likesMember,likesProduct가 있는지 확인
	// => 없으면 insert, 있으면 delete
	@Override
	public void likeCheck(LikeDTO likes) {
		log.info("aaa: {} ,{}",likes.getLikesMember(),likes.getLikesProduct());
		if(likeMapper.likeCheck(likes) == 0) {
			insertLikes(likes);
		}else {
			throw new DuplicatedLikeException("이미 위시리스트에 저장된 상품입니다.");
		}
	}

	@Override
	public void insertLikes(LikeDTO likes) {
		likeMapper.insertLikes(likes);
	}

//	@Override
//	public void deleteLikes(LikeDTO likes) {
//		likeMapper.deleteLikes(likes);	
//	}

}
