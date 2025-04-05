package com.example.demo.board.model.service;

import java.util.List;

import com.example.demo.board.model.dto.BoardDTO;

public interface BoardService {

	
	void insertBoard(BoardDTO board);

	List<BoardDTO> selectBoard();
}
