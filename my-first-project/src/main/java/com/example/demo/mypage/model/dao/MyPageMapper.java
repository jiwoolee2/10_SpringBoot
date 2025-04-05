package com.example.demo.mypage.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.like.model.dto.LikeDTO;
import com.example.demo.productInfo.model.dto.ProInfoDTO;

@Mapper
public interface MyPageMapper {

	List<ProInfoDTO> selectLikeInfo(LikeDTO member);
}
