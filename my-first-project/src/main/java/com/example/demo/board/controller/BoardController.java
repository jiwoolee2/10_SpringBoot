package com.example.demo.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.board.model.dto.BoardDTO;
import com.example.demo.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BoardController {

	private final BoardService boardService;
	
	@PostMapping("/insert")
	private ResponseEntity<?> insertBoard(@RequestBody BoardDTO board){

		log.info("board : {}",board);
		boardService.insertBoard(board);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("게시글이 등록되었습니다.");
	}

	
	@GetMapping("/select")
	private ResponseEntity<?> selectBoard(){
		
		
		List<BoardDTO> result = boardService.selectBoard();
		return ResponseEntity.ok(result);
	}
	
	
	
}
