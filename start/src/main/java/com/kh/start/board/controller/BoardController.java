package com.kh.start.board.controller;



import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.kh.start.auth.controller.AuthController;
import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.service.BoardService;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated // controller에서 validanotion써서 체크 가능
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/boards")
@RestController
public class BoardController {

    private final AuthController authController;

	private final BoardService boardService;


    
	
	// 게시글 목록 insert하기 !!
	@PostMapping
	public ResponseEntity<?> save(BoardDTO board, 
			@RequestParam(name="file",required=false) MultipartFile file){
		
		log.info("게시글 정보 : {} , 파일 정보 : {}",board,file.getOriginalFilename());
		
		boardService.save(board, file);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	// 특정 페이지의 게시글목록 조회하기
	@GetMapping
	public ResponseEntity<List<BoardDTO>> findAll(@RequestParam(name = "page",defaultValue="0") int page){
		
		return ResponseEntity.ok(boardService.findAll(page));
	}
	
	
	// id를 이용해서 게시글목록에서 게시글얻어오기
	@GetMapping("/{id}")
	public ResponseEntity<BoardDTO> findById(@PathVariable (name="id") 
											 @Min(value=1,message="넘작아요") Long boardNo){
		return ResponseEntity.ok(boardService.findById(boardNo));
	}
	
	
	// 
	@PutMapping("/{id}")
	public ResponseEntity<BoardDTO> update(@PathVariable(name="id") Long boardNo,
											BoardDTO board,
											@RequestParam(name="file",required=false) MultipartFile file){
		board.setBoardNo(boardNo);
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(boardService.update(board, file));
		
	}
	
	// 게시글 삭제하기
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(name="id") Long boardNo){
		boardService.deleteById(boardNo);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
 