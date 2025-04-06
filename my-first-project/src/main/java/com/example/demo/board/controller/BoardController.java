package com.example.demo.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	
	@GetMapping("/page/{page}")
	private ResponseEntity<?> boardList(@PathVariable("page") int page) {

		List<BoardDTO> boardList = boardService.boardList(page);
		int totalBoardCount = boardService.boardCount(); // 전체 게시글 개수
		
		
	    int size = 10; // 1~5, 6~10까지 다섯개씩 페이지 버튼 띄움
	    int totalPageCount = (int)((totalBoardCount+9)/size);
	    
	    Map<String, Object> result = new HashMap();
	    result.put("boardList", boardList);
	    result.put("totalPageCount", totalPageCount);

	    /*
	     * 페이지 개수, 처음에보여질 10개 내용
	     */
	    return ResponseEntity.ok(result);
	}

	
	@GetMapping("{boardNo}")
	private ResponseEntity<?> boardDetail(@PathVariable(name="boardNo") Long boardNo){
		
		BoardDTO result = boardService.boardDetail(boardNo);
		return ResponseEntity.ok(result);
	}
	
	
	
}
