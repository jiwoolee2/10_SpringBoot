package com.example.demo.productInfo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.productInfo.model.dto.ProInfoDTO;
import com.example.demo.productInfo.model.service.ProInfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("productinfo")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProInfoController {

	private final ProInfoService proInfoService;
	
	@GetMapping("/{category}/{type}")
	public ResponseEntity<List<ProInfoDTO>> selectProduct(@PathVariable(name="category") String category,
														  @PathVariable(name="type") String type){
		
		ProInfoDTO proInfoDTO = new ProInfoDTO();
		proInfoDTO.setProductCategory(category);
		proInfoDTO.setProductType(type);
		
		List<ProInfoDTO> productInfo = proInfoService.selectProduct(proInfoDTO);
		return ResponseEntity.ok(productInfo);
	}
}

	