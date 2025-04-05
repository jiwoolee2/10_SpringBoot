package com.example.demo.board.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {

	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Date createDate;

}
