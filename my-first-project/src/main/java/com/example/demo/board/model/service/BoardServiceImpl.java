package com.example.demo.board.model.service;

import java.util.List;

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
	public List<BoardDTO> selectBoard() {
		
		return boardMapper.selectBoard();
	}

}
