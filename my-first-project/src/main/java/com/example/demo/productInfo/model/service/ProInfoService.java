package com.example.demo.productInfo.model.service;

import java.util.List;

import com.example.demo.productInfo.model.dto.ProInfoDTO;

public interface ProInfoService {

	List<ProInfoDTO> selectProduct(ProInfoDTO proInfoDTO);
}
