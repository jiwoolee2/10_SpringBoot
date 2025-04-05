package com.example.demo.mypage.model.service;

import java.util.List;

import com.example.demo.like.model.dto.LikeDTO;
import com.example.demo.productInfo.model.dto.ProInfoDTO;

public interface MyPageService {

	List<ProInfoDTO> selectLikeInfo(LikeDTO member);

}
