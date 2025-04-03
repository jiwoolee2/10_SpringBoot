package com.kh.start.comments.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.auth.service.AuthService;
import com.kh.start.board.model.service.BoardService;
import com.kh.start.comments.model.dao.CommentMapper;
import com.kh.start.comments.model.dto.CommentDTO;
import com.kh.start.comments.model.vo.Comment;
import com.kh.start.exception.InvalidUserRequestException;

import lombok.RequiredArgsConstructor;
import oracle.net.ano.AuthenticationService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentMapper mapper;
	private final BoardService boardService;
	private final AuthService authService;
	
	@Override
	public void insertComment(CommentDTO comment) {
		// 게시글 있는건지 확인?? 
		boardService.findById(comment.getRefBoardNo());

		// 댓글작성 요청한 사용자랑 토큰 소유주랑 같은지 확인하기
		authService.getUserDetails();
		String tokenMemberNo = String.valueOf(((CustomUserDetails)authService.getUserDetails()).getMemberNo());
		
		if(tokenMemberNo.equals(comment.getCommentWriter())) {
			throw new InvalidUserRequestException("너 이름이 뭐니?????????");
		}
		
		Comment requestData = Comment.builder()   
									 .commentWriter(Long.parseLong(tokenMemberNo))
									 .commentContent(comment.getCommentContent())
									 .refBoardNo(comment.getRefBoardNo())
									 .build();
		
		mapper.insertComment(requestData);
		
		
	}

	@Override
	public List<CommentDTO> selectCommentList(Long boardNo) {
		boardService.findById(boardNo);
		
		return mapper.selectCommentList(boardNo);
	}

}
