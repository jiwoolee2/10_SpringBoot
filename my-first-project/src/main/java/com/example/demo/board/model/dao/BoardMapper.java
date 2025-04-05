package com.example.demo.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.board.model.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	void insertBoard(BoardDTO board);

	List<BoardDTO> selectBoard();
	
}
