package com.kh.start.comments.model.service;

import java.util.List;

import com.kh.start.comments.model.dto.CommentDTO;

public interface CommentService {

	void insertComment(CommentDTO commit);
	
	List<CommentDTO> selectCommentList(Long boardNo);
	
}
