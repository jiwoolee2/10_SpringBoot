package com.example.demo.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.example.demo.board.model.dao.BoardMapper;
import com.example.demo.board.model.dto.BoardDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;
	

	@Override
	public void insertBoard(BoardDTO board) {
		
		boardMapper.insertBoard(board);
	}


	@Override
	public List<BoardDTO> boardList(int page) {
		int boardsize = 10;
		/*
		 * RowBounds(offsest,limit);
		 * offset : 건너뛸 게시글 개수
		 * limit : 한 페이지에 보여질 게시글 수 
		 * ==> 몇 개의 게시글을 건너뛰고 몇개를 보여줄지
		 * page=2인경우 20개를 건너뛰고 그 다음 10개를 보여줌 
		 * LIMIT 20 OFFSET 10
		 * MyBatis가 RowBounds를 자동으로 SQL에 적용시켜서 이렇게 동작시켜줌
		 */
		RowBounds rowBounds = new RowBounds((page-1)*boardsize,boardsize);
		return boardMapper.boardList(rowBounds);
	}
	
	@Override
	public int boardCount() {
		return boardMapper.boardCount();
	}



	@Override
	public BoardDTO boardDetail(Long boardNo) {
		return boardMapper.boardDetail(boardNo);
	}



}
