package com.example.demo.like.model.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.like.model.dto.LikeDTO;

@Mapper
public interface LikeMapper {

	@Select("SELECT COUNT(*) FROM LIKES WHERE LIKES_MEMBER = #{likesMember} AND LIKES_PRODUCT = #{likesProduct}")
	int likeCheck(LikeDTO likes);

	@Insert("INSERT INTO LIKES VALUES(#{likesMember},#{likesProduct})")
	void insertLikes(LikeDTO likes);

//	@Delete("DELETE FROM LIKES WHERE LIKES_MEMBER = #{likesMember} AND LIKES_PRODUCT = #{likesProduct}")
//	void deleteLikes(LikeDTO likes);


	
}
