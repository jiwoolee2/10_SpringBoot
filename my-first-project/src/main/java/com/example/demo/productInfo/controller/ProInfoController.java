package com.example.demo.productInfo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.productInfo.model.dto.ProInfoDTO;
import com.example.demo.productInfo.model.service.ProInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("productinfo")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProInfoController {

	private final ProInfoService proInfoService;
	
	@GetMapping("/{category}")
	public ResponseEntity<List<ProInfoDTO>> selectProduct(
			@PathVariable(name="category") String category){
		
		// 아직 proInfoDTO에 값 안 넣음
		//List<ProInfoDTO> productInfo = proInfoService.selectProduct(proInfoDTO);
		return null;
	}
}
