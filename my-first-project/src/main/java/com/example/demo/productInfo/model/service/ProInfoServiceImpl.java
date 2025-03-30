package com.example.demo.productInfo.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.productInfo.model.dao.ProInfoMapper;
import com.example.demo.productInfo.model.dto.ProInfoDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProInfoServiceImpl implements ProInfoService {
	
	private final ProInfoMapper proInfoMapper;
	
	public List<ProInfoDTO> selectProduct(ProInfoDTO proInfoDTO){
		
		
		return proInfoMapper.selectProduct(proInfoDTO);
	}
}
