package com.kh.start.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.auth.file.service.FileService;
import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.auth.service.AuthService;
import com.kh.start.board.model.dao.BoardMapper;
import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.vo.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	private final AuthService authService;
	private final FileService fileService;
	
	// 전체 게시글 목록 조회
	@Override
	public void save(BoardDTO board, MultipartFile file) {
		
		// baord에는 게시글 정보
		// file 파일이 존재할 경우 파일의 정보
		
		
		// board에 writer를 memberNo에서 reference해서 memberNo를 가져와야함
		// SecurityContextHolder에 authentication에 principal에 저장된 정보 뺴내야함
		// => 단일책임원칙
		CustomUserDetails user = authService.getUserDetails();
		Long memberNo = user.getMemberNo();
		//board.setBoardWriter(String.valueOf(memberNo));
		Board requestData = null;
		
		
		
		// 첨부파일에 관련된 값
		if(file != null && !file.isEmpty()) { //파일이 있으면
			
			// 이름 바꾸기 , 원본파일명 뽑기 , 저장위치, 파일올리는 메서드 호출
			// => 단일책임원칙 위배  => 이거 다 파일서비스에서함
			
			String filePath = fileService.store(file);
			
			// 파일이 있으면 vo객체에 파일까지 담음
			requestData =Board.builder()   
							 .boardTitle(board.getBoardTitle())
							 .boardContent(board.getBoardContent())
							 .boardWriter(String.valueOf(memberNo))
							 .boardFileUrl(filePath)
							 .build();
			} else { // 없으면 파일 안담음
			requestData =Board.builder()
						 .boardTitle(board.getBoardTitle())
						 .boardContent(board.getBoardContent())
						 .boardWriter(String.valueOf(memberNo))
						 .build();
			}
		
		boardMapper.save(requestData);
	}
	

	// 페이지를 받아서 그 페이지에 해당하는 게시글 목록 띄우기
	@Override
	public List<BoardDTO> findAll(int pageNo) {
		int size = 5;
		RowBounds rowBounds = new RowBounds(pageNo*size,size);
		return boardMapper.findAll(rowBounds);
	}

	
	// 아이디를 이용해서 게시글 상세조회
	@Override
	public BoardDTO findById(Long boardNo) {
		return getBoardOrThrow(boardNo);
	}
	
	
	private BoardDTO getBoardOrThrow(Long boardNo) {
		BoardDTO board = boardMapper.findById(boardNo);	
	
		if(board == null) {
			throw new RuntimeException("존재하지 않는 게시글 요청입니다.");
		}
		return board;
	}
	
	

	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		
		if(file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			board.setBoardFileUrl(filePath);
		}
		boardMapper.update(board);
		return board; 
	}

	
	@Override
	public void deleteById(Long boardNo) {
		boardMapper.deleteById(boardNo);

	}

}
